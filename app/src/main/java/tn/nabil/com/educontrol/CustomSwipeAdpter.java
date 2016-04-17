package tn.nabil.com.educontrol;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

/**
 * Created by asus on 12/04/2016.
 */
public class CustomSwipeAdpter extends PagerAdapter{
private int[] image_resources = {R.drawable.a,R.drawable.b,R.drawable.c};
    private Context ctx;
    private LayoutInflater layoutInflater;


    public CustomSwipeAdpter(Context ctx)
    {
        this.ctx = ctx;


    }

    @Override
    public int getCount() {

        return image_resources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return (view==(LinearLayout)o);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //Toast.makeText(getApplicationcontext, "aaaaa", Toast.LENGTH_SHORT).show();
        System.out.println("aaaaaa");
        layoutInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.swipe_layout,container,false);
        ImageView imageView= (ImageView)item_view.findViewById(R.id.image_view);
        TextView textView = (TextView)item_view.findViewById(R.id.image_count);
        imageView.setImageResource(image_resources[position]);
        textView.setText("image : " + position);
        container.addView(item_view);
        return item_view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object o) {
        container.removeView((LinearLayout)o);

    }
}
