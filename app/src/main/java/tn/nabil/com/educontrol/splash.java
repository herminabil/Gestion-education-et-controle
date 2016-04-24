package tn.nabil.com.educontrol;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.flaviofaria.kenburnsview.Transition;


public class splash extends AppCompatActivity {

    private KenBurnsView kenBurnsView;
    private boolean isPlay = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        kenBurnsView = (KenBurnsView) findViewById(R.id.image);

        AccelerateDecelerateInterpolator ACCELERATE_DECELERATE = new AccelerateDecelerateInterpolator();
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        RandomTransitionGenerator generator = new RandomTransitionGenerator(3000, linearInterpolator);
        kenBurnsView.setTransitionGenerator(generator); //set new transition on kenburns view
        //SystemClock.sleep(500);

        kenBurnsView.setTransitionListener(onTransittionListener());

    }

    private KenBurnsView.TransitionListener onTransittionListener() {
        return new KenBurnsView.TransitionListener() {

            @Override
            public void onTransitionStart(Transition transition) {
                //Toast.makeText(MainActivity.this, "start", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                // Toast.makeText(MainActivity.this, "end", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.play) {
            if (isPlay) {
                kenBurnsView.pause();
                //item.setIcon(R.mipmap.play1);
                isPlay = false;
            } else {
                kenBurnsView.resume();
                //item.setIcon(R.mipmap.pause1);
                isPlay = true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
