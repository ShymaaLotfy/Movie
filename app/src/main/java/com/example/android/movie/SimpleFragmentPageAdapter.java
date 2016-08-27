package com.example.android.movie;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Shimaa on 8/20/2016.
 */
public class SimpleFragmentPageAdapter extends FragmentPagerAdapter {
    public SimpleFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new MovieFragment();
        } else if (position == 1){
            return new CastFragment();
        } else  {
            return new SimilarMoviesFragment();
        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}
