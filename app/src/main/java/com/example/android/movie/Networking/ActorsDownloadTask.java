package com.example.android.movie.Networking;

import com.example.android.movie.Models.Actors;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Shimaa on 8/30/2016.
 */
public class ActorsDownloadTask extends DownloadTask<Actors> {
    @Override
    public Actors objectFromJson(JSONObject object) throws JSONException {
        return new Actors(object,true);
    }
}
