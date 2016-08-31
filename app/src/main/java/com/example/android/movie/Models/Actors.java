package com.example.android.movie.Models;

import com.example.android.movie.Networking.Urls;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Shimaa on 8/30/2016.
 */
public class Actors {

    public final  String name ;
    public final  String character ;
    public final String picturePath ;
    public final String popularity;

    public Actors(JSONObject JsonActor) throws JSONException {

        name = JsonActor.getString("name");
        character = JsonActor.getString("character");
        picturePath = JsonActor.getString("profile_path");
        popularity = null;

    }

    public Actors(JSONObject jsonActor , boolean a) throws JSONException {

       name = jsonActor.getString("name");
        picturePath = jsonActor.getString("profile_path");
        popularity = jsonActor.getString("popularity");
        character = null;
    }

    public String thumUrl() {
        return Urls.IMAGE_BASE_URL + "w92" + picturePath;
    }

}
