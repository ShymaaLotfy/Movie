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
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    MovieAdapter popularadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Start the AsyncTask to fetch the popular data to the main activity
        QueryAsyncTask task = new QueryAsyncTask();
        ArrayList<Movie> result=null;

        try {
            result =task.execute("http://api.themoviedb.org/3/movie/popular?api_key=9d439968a128f2c3596337f5aaa636cc").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //set the data adapter
        ListView movieListView = (ListView) findViewById(R.id.outerPopularList);
        popularadapter = new MovieAdapter(this,new ArrayList<Movie>());
        movieListView.setAdapter(popularadapter);

        // Navigation Drawer list
        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout)  findViewById(R.id.drawer_layout);
        mDrawerList = (ListView)  findViewById(R.id.nvDrawerList);
        // Set the adapter for the list view
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mNavigationDrawerItemTitles);
        mDrawerList.setAdapter(adapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment f ;
                switch (position) {
                    case 0:
                        f = new PopularFragment();
                        break;
                    case 1 :
                        f = new TopRatedFragment();
                        break;
                    case 2 :
                        f = new PopularFragment();
                        break;
                    case 3 :
                        f = new UpcomingFragment();
                        break;
                    default:
                        f = new NowPlayingFragment();
                        break;
                }
                if (f != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, f).commit();

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

    private class QueryAsyncTask extends AsyncTask<String, Void, ArrayList<Movie>> {

        /**
         * This method runs on a background thread and performs the network request.
         * We should not update the UI from a background thread, so we return a list of
         * {@link Movie}s as the result.
         */
        @Override
        protected ArrayList<Movie> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            Query query = new Query() ;
            ArrayList<Movie> result = query.fetchEarthquakeData(urls[0]);
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
        protected void onPostExecute(ArrayList<Movie> data) {
            // Clear the adapter of previous movie data

            popularadapter.clear();

            // If there is a valid list of {@link movie}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                popularadapter.addAll(data);
            }
        }
    }

}
