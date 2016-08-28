package com.example.android.movie;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Shimaa on 8/28/2016.
 */
public class GetMovieListQuery extends Query {

    @Override
    public  ArrayList<Movie> extractFeatureFromJson(String movieJSON) {
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
                String originalMovieTitle = currentElement.getString("original_title");
                String posterUrl = "http://image.tmdb.org/t/p/w185/";
                posterUrl += currentElement.getString("poster_path");

                mMovie.add(new Movie(movieId, movieTitle, originalMovieTitle, posterUrl));
            }

        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        return  mMovie;
    }
}
