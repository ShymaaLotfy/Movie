package com.example.android.movie.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.movie.Fragments.ActorDetailsFragment;
import com.example.android.movie.Fragments.CastFragment;
import com.example.android.movie.Fragments.MovieDetailsFragment;
import com.example.android.movie.Fragments.MovieFragment;
import com.example.android.movie.Models.Actors;
import com.example.android.movie.Models.Movie;
import com.example.android.movie.Networking.Urls;
import com.example.android.movie.R;

/**
 * Created by Shimaa on 8/31/2016.
 */
public class ActorClickActivity extends AppCompatActivity {

    public Actors clickedActor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_click_activity);

        //Get the clicked Movie
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        clickedActor = (Actors) bundle.getSerializable("clickedItem");

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById( R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        SimpleFragmentPageAdapter adapter = new SimpleFragmentPageAdapter(getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);


    }

    public class SimpleFragmentPageAdapter extends FragmentPagerAdapter {
        public SimpleFragmentPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f = null;
            String title = null ;
            if (position == 0) {
                f = new ActorDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("Actor",clickedActor);
                f.setArguments(bundle);
            }


            return f;

        }

        @Override
        public int getCount() {
            return 1;
        }


    }
}
