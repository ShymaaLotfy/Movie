package com.example.android.movie;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Shimaa on 8/27/2016.
 */
public class TopRatedFragment extends Fragment {
    public MovieAdapter adapter = null ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.movie_list, container, false);

        // Start the AsyncTask to fetch the earthquake data
        QueryAsyncTask task = new QueryAsyncTask();
        ArrayList<Movie> result=null;

        try {
            result =task.execute("http://api.themoviedb.org/3/movie/top_rated?api_key=9d439968a128f2c3596337f5aaa636cc").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //data adapter
        ListView movieListView = (ListView) rootView.findViewById(R.id.list);
        adapter = new MovieAdapter(this.getActivity(),new ArrayList<Movie>());
        movieListView.setAdapter(adapter);

        return rootView;

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

            adapter.clear();

            // If there is a valid list of {@link movie}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                adapter.addAll(data);
            }
        }
    }
}
