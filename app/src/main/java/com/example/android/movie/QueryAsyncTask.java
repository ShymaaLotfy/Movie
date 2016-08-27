package com.example.android.movie;


import java.util.ArrayList;
import android.os.AsyncTask;
import java.util.ArrayList;

/**
 * Created by Shimaa on 8/27/2016.
 */
public class QueryAsyncTask extends AsyncTask<String, Void, ArrayList<Movie>> {

    /**
     * This method runs on a background thread and performs the network request.
     * We should not update the UI from a background thread, so we return a list of
     * {@link Movie}s as the result.
     */
    private MovieAdapter adapter ;
    @Override
    protected ArrayList<Movie> doInBackground(String... urls) {
        // Don't perform the request if there are no URLs, or the first URL is null.
        if (urls.length < 1 ||urls[0] == null) {
            return null;
        }

        Query query = new Query() ;
        ArrayList<Movie> result = query.fetchEarthquakeData(urls[0]);
        return result;
    }


}
