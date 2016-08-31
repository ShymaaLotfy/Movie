package com.example.android.movie.Models;

import com.example.android.movie.Networking.Urls;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Shimaa on 8/30/2016.
 */
public class Actors implements Serializable {

    public final  String name ;
    public final  String character ;
    public final String picturePath ;
    public final String popularity;
    public final int id;

    public Actors(JSONObject JsonActor) throws JSONException {

        name = JsonActor.getString("name");
        character = JsonActor.getString("character");
        picturePath = JsonActor.getString("profile_path");
        popularity = null;
        id = JsonActor.getInt("id");

    }

    public Actors(JSONObject jsonActor , boolean a) throws JSONException {

       name = jsonActor.getString("name");
        picturePath = jsonActor.getString("profile_path");
        popularity = jsonActor.getString("popularity");
        character = null;
        id = jsonActor.getInt("id");
    }

    public String thumUrl() {
        return Urls.IMAGE_BASE_URL + "w92" + picturePath;
    }

}
