package com.example.android.movie.Activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.android.movie.Fragments.CastFragment;
import com.example.android.movie.Fragments.MovieDetailsFragment;
import com.example.android.movie.Fragments.MovieFragment;
import com.example.android.movie.Models.Movie;
import com.example.android.movie.Networking.Urls;
import com.example.android.movie.R;

public class MovieClickActivity extends AppCompatActivity {

    public Movie clickedMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_click_activity);

        //Get the clicked Movie
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        clickedMovie = (Movie) bundle.getSerializable("clickedItem");

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById( R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        SimpleFragmentPageAdapter adapter = new SimpleFragmentPageAdapter(getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);
        // Find the tab layout that shows the tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        // Connect the tab layout with the view pager. This will
        //   1. Update the tab layout when the view pager is swiped
        //   2. Update the view pager when a tab is selected
        //   3. Set the tab layout's tab names with the view pager's adapter's titles
        //      by calling onPageTitle()
        tabLayout.setupWithViewPager(viewPager);

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
              f = new MovieDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("Movie",clickedMovie );
                f.setArguments(bundle);
            }
            else if (position == 1){
                f = new CastFragment();
                Bundle bundle = new Bundle();
                String url = Urls.CAST_BASE_URL + clickedMovie.id + Urls.CAST_REM_URL;
                bundle.putSerializable("Movie",clickedMovie );
                bundle.putString("url",url);
                f.setArguments(bundle);
            }
            else if(position == 2)
            {
                f = new MovieFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("Movie",clickedMovie );
                bundle.putString(MovieFragment.KEY_URL, Urls.SIMILAR_MOVIE_BASE_URL + clickedMovie.id + Urls.SIMILAR_MOVIE_REM_URL);
                f.setArguments(bundle);
            }



            return f;

        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // CHANGE STARTS HERE
            if (position == 0) {
                return "Movie";
            } else if (position == 1) {
                return "Cast";
            } else {
                return "Similar Movies";
            }
        }
    }
}
