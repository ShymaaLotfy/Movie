package com.example.android.movie;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MovieAdapter adapter = null ;
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start the AsyncTask to fetch the earthquake data
        QueryAsyncTask task = new QueryAsyncTask();
        task.execute("http://api.themoviedb.org/3/movie/now_playing?api_key=9d439968a128f2c3596337f5aaa636cc");

       // ListView movieListView = (ListView) findViewById(R.id.list);
        //adapter = new MovieAdapter(this, R.layout.movie_list);
        //movieListView.setAdapter(adapter);

        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.nvdrawer);
        // Set the adapter for the list view
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mNavigationDrawerItemTitles);
        mDrawerList.setAdapter(adapter);

        //DrawerItemClickListener clickListener = new DrawerItemClickListener();
        //clickListener.onItemClick(adapter,);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Fragment f = null;
                switch (position) {
                    case 0:
                        f = new NowPlayingFragment();
                        break;

                    default:
                        break;
                }

                if (f != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.flContent, f).commit();

                    mDrawerList.setItemChecked(position, true);
                    mDrawerList.setSelection(position);
                    // getActionBar().setTitle(mNavigationDrawerItemTitles[position]);
                    mDrawerLayout.closeDrawer(mDrawerList);

                } else {
                    Log.e("MainActivity", "Error in creating fragment");
                }

            }
        });



    }


    private class QueryAsyncTask extends AsyncTask<String, Void, List<Movie>> {

        /**
         * This method runs on a background thread and performs the network request.
         * We should not update the UI from a background thread, so we return a list of
         * {@link Movie}s as the result.
         */
        @Override
        protected List<Movie> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            Query query = new NowPlayingQuery() ;
            List<Movie> result = query.fetchEarthquakeData(urls[0]);
            return result;
        }

        /**
         * This method runs on the main UI thread after the background work has been
         * completed. This method receives as input, the return value from the doInBackground()
         * method. First we clear out the adapter, to get rid of moviedata from a previous
         * query. Then we update the adapter with the new list of movies,
         * which will trigger the ListView to re-populate its list items.
         */
        @Override
        protected void onPostExecute(List<Movie> data) {
            // Clear the adapter of previous movie data
           adapter.clear();

            // If there is a valid list of {@link movie}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                adapter.addAll(data);
            }
        }
    }
}
