package com.example.android.movie.Networking;

import com.example.android.movie.Models.Movie;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Shimaa on 8/29/2016.
 */
public class MovieDownloadTask extends DownloadTask<Movie> {
    @Override
    public Movie objectFromJson(JSONObject object) throws JSONException {
        return new Movie(object);
    }

}
