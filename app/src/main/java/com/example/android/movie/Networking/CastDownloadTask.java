package com.example.android.movie.Networking;

import android.os.AsyncTask;

import com.example.android.movie.Models.Actors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shimaa on 8/30/2016.
 */
public class CastDownloadTask extends DownloadTask<Actors>{
    @Override
    public Actors objectFromJson(JSONObject object) throws JSONException {
        return new Actors(object);
    }

    @Override
    protected List<Actors> doInBackground(String... params) {
        List<Actors> resultList = new ArrayList<>();
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(params[0]).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                String jsonString = readResponse(connection.getInputStream());
                JSONObject object = new JSONObject(jsonString);
                JSONArray results = object.getJSONArray("cast");
                for(int i=0 ; i<results.length() ; i++){
                    Actors v = objectFromJson(results.getJSONObject(i)) ;
                    resultList.add(v);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return resultList;
    }


}
