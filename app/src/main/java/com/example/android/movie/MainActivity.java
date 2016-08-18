package com.example.android.movie;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MovieAdapter adapter = null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //to solve mainThread exception
        // if (android.os.Build.VERSION.SDK_INT > 9) {
         //   StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
           // StrictMode.setThreadPolicy(policy);
        //}


        //Query nowPlayingQuery = new NowPlayingQuery();
        //List<Movie> movie = nowPlayingQuery.fetchEarthquakeData("http://api.themoviedb.org/3/movie/now_playing?api_key=9d439968a128f2c3596337f5aaa636cc");


        // Start the AsyncTask to fetch the earthquake data
        QueryAsyncTask task = new QueryAsyncTask();
        task.execute("http://api.themoviedb.org/3/movie/now_playing?api_key=9d439968a128f2c3596337f5aaa636cc");

        ListView movieListView = (ListView) findViewById(R.id.list);
        adapter = new MovieAdapter(this, R.layout.movie_list);
        movieListView.setAdapter(adapter);



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
         * method. First we clear out the adapter, to get rid of earthquake data from a previous
         * query to USGS. Then we update the adapter with the new list of earthquakes,
         * which will trigger the ListView to re-populate its list items.
         */
        @Override
        protected void onPostExecute(List<Movie> data) {
            // Clear the adapter of previous earthquake data
            adapter.clear();

            // If there is a valid list of {@link movie}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                adapter.addAll(data);
            }
        }
    }
}
