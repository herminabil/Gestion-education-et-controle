package tn.nabil.com.educontrol;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Parent extends AppCompatActivity {

    RequestQueue requestQueue;
    String showUrl = "http://192.168.56.1/PhpAndroid/showStudents.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
        TextView tvWelcome = (TextView)findViewById(R.id.tvWelcome);
        final String messagerecu = getIntent().getExtras().getString("message");
        tvWelcome.setText("BienVenue "+messagerecu);
        final TextView tvnom =(TextView) findViewById(R.id.tvnom);
        //final TextView tvabsence =(TextView)findViewById(R.id.tvabsence);
        final TextView tvprenom =(TextView) findViewById(R.id.tvprenom);
        final TextView tvdate =(TextView) findViewById(R.id.tvdate);
        final TextView tvclasse =(TextView) findViewById(R.id.tvclasse);
        final TableLayout tl1 = (TableLayout) findViewById(R.id.tl1);

        final Button bEmploi = (Button) findViewById(R.id.bEmploi);
        Button show = (Button) findViewById(R.id.show);
        final Button bAbsence = (Button) findViewById(R.id.bAbsence);
       // final TextView result = (TextView) findViewById(R.id.tvresult);

        bEmploi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent intent = new Intent(Parent.this, EmploiActivity.class);
               // intent.putExtra("message1",messagerecu);
                Parent.this.startActivity(intent);
/*              Intent intent = new Intent(LoginActivity.this, Parent.class);
                intent.putExtra("message",jsonObject.getString("success"));
                LoginActivity.this.startActivity(intent);
                */
            }
        });

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //System.out.println("ww");
                tl1.setVisibility(bEmploi.getVisibility());
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                        showUrl, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                        try {
                            JSONArray students = response.getJSONArray("students");
                            //result.append("firstname    lastname    classe "+ " \n");
                            for (int i = 0; i < students.length(); i++) {
                                JSONObject student = students.getJSONObject(i);
                                 if (student.getString("parent").equals(messagerecu)) {

                                    String firstname = student.getString("nom");
                                    String lastname = student.getString("prenom");
                                    String date = student.getString("date naissance");
                                    String classe = student.getString("classe");

                                     TextView textView6 = (TextView)findViewById(R.id.textView6);
                                     textView6.setText("naissance : ");

                                     TextView textView7 = (TextView)findViewById(R.id.textView7);
                                     textView7.setText("- Classe :");

                                     tvnom.setText("");
                                     tvprenom.setText("");
                                     tvdate.setText("");
                                     tvclasse.setText("");

                                     tvnom.append(firstname);
                                     tvprenom.append(lastname);
                                     tvdate.append(date);
                                     tvclasse.append(classe);

                                    //result.append(firstname + "||" + lastname + " ||" + classe + " \n");
                                }
                            }
                           // result.append("============\n");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.append(error.getMessage());

                    }
                });
                requestQueue.add(jsonObjectRequest);
            }
        });


        bAbsence.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                System.out.println("ww");

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                        showUrl, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                        try {
                            JSONArray students = response.getJSONArray("students");
                            //result.append("firstname    lastname    classe "+ " \n");

                            for (int i = 0; i < students.length(); i++) {
                                JSONObject student = students.getJSONObject(i);
                                if (student.getString("parent").equals(messagerecu))  {
                                    if (student.getString("absence").equals("oui")) {
                                        tl1.setVisibility(bEmploi.getVisibility());
                                        //Toast.makeText(l(), "L'eleve est " + student.getString("motif"), Toast.LENGTH_LONG).show();

                                        TextView textView6 = (TextView)findViewById(R.id.textView6);
                                        textView6.setText("absence : ");

                                        TextView textView7 = (TextView)findViewById(R.id.textView7);
                                        textView7.setText("- Motif :");

                                        String firstname = student.getString("nom");
                                        String date1 = student.getString("date absence");
                                        String motif = student.getString("motif");

                                        tvnom.setText("");
                                        tvdate.setText("");
                                        tvclasse.setText("");

                                        tvnom.append(firstname);
                                        tvdate.append(date1);
                                        tvclasse.append(motif);



                                        //result.append(firstname + "||" + lastname + " ||" + classe + " \n");
                                    }
                                    else {
                                        if (tl1.getVisibility()== bEmploi.getVisibility())
                                            tl1.setVisibility(View.INVISIBLE);
                                        Context context = getApplicationContext();
                                        CharSequence text = "L'eleve " + student.getString("nom") + " n'a pas d'absence";
                                        int duration = Toast.LENGTH_SHORT;
                                        Toast toast = Toast.makeText(context, text, duration);
                                        toast.setGravity(7, 2, 2);
                                        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                                        toast.getView().setBackgroundColor(Color.BLUE);
                                        v.setTextColor(Color.RED);
                                        toast.show();

                                           // Toast.makeText(getApplicationContext(), "L'eleve " + student.getString("nom") + " n'a pas d'absence", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                            // result.append("============\n");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.append(error.getMessage());

                    }
                });
                requestQueue.add(jsonObjectRequest);
            }
        });


    }


}
