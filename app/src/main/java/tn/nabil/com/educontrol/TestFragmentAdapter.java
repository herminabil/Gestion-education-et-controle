package tn.nabil.com.educontrol;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.viewpagerindicator.IconPagerAdapter;
/**
 * Created by asus on 14/04/2016.
 */
public class TestFragmentAdapter extends FragmentPagerAdapter implements IconPagerAdapter{
    protected static final String[] CONTENT = new String[] {
            "Lundi", "Mardi", "Mercredi", "Jeudi","Vendredi", "Samedi"
    };

    private int mCount = CONTENT.length;

    public TestFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getIconResId(int index) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new Fragment1();
        switch(position){
            case 0:
                fragment = new Fragment1();
                break;
            case 1:
                fragment = new Fragment2();
                break;
            case 2:
                fragment = new Fragment3();
                break;
            case 3:
                fragment = new Fragment4();
                break;
            case 4:
                fragment = new Fragment5();
                break;
            case 5:
                fragment = new Fragment6();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public CharSequence getPageTitle(int position){
        String title = "";
        switch(position){
            case 0:
                title = "Lundi";
                break;
            case 1:
                title = "Mardi";
                break;
            case 2:
                title = "Mercredi";
                break;
            case 3:
                title = "Jeudi";
                break;
            case 4:
                title = "Vendredi";
                break;
            case 5:
                title = "Samedi";
                break;
        }

        return title;
    }

    public void setCount(int count){
        if (count > 0 && count < 10){
            mCount = count;
            notifyDataSetChanged();
        }
    }


}

