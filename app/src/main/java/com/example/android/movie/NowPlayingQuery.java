package com.example.android.movie;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shimaa on 8/17/2016.
 */
public class NowPlayingQuery extends Query {

    public NowPlayingQuery()
    {
        super();
    }


    /**
     * Return a list of {@link Movie} objects that has been built up from
     * parsing the given JSON response.
     */
    @Override
    public ArrayList<Movie> extractFeatureFromJson(String movieJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(movieJSON)) {
            return null;
        }
        // Create an empty ArrayList that we can start adding Movies to
        ArrayList<Movie> mMovie = new ArrayList<Movie>() ;

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(movieJSON);

            // Extract the JSONArray associated with the key called "results",
            // which represents a list of results.
            JSONArray MovieArray = baseJsonResponse.getJSONArray("results");

            // For each movie in the MovieArray, create an {@link Movie} object
            for (int i = 0; i < 20; i++) {

                JSONObject currentElement = MovieArray.getJSONObject(i);

                int movieId = currentElement.getInt("id");
                String movieTitle = currentElement.getString("title");

                mMovie.add(new Movie(movieTitle, movieId));
            }

        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        return  mMovie;
    }
}
