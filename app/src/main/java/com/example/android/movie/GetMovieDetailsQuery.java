package com.example.android.movie;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Shimaa on 8/28/2016.
 */
public class GetMovieDetailsQuery extends Query {
    @Override
    public ArrayList<String> extractFeatureFromJson(int id) {
        // If the JSON string is empty or null, then return early.
       String movieUrl = "http://api.themoviedb.org/3/movie/297761?api_key=9d439968a128f2c3596337f5aaa636cc&id="
               + id ;
        // Create an empty ArrayList that we can start adding Movies to
        ArrayList<String> mMovieDetails = new ArrayList<String>() ;

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(movieUrl);

            String backdropPath = baseJsonResponse.getString("backdrop_path");
            mMovieDetails.add(backdropPath);

            JSONArray GenereArray = baseJsonResponse.getJSONArray("genres");
            for (int i=0 ; i<GenereArray.length() ;i++)
            {
             mMovieDetails.add(GenereArray.getJSONObject(i).getString("name"));
            }

            String status = baseJsonResponse.getString("status");
            mMovieDetails.add(status);




        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        return  mMovieDetails;
    }
}
