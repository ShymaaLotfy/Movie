package com.example.android.movie.Networking;

import android.os.AsyncTask;

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
 * Created by Shimaa on 8/29/2016.
 */
public abstract class DownloadTask<V> extends AsyncTask<String,Void,List<V>> {
    @Override
    protected List<V> doInBackground(String... params) {

        List<V> resultList = new ArrayList<>();
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(params[0]).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                String jsonString = readResponse(connection.getInputStream());
                JSONObject object = new JSONObject(jsonString);
                JSONArray results = object.getJSONArray("results");
                for(int i=0 ; i<20; i++){
                    V v = objectFromJson(results.getJSONObject(i)) ;
                    resultList.add(v);
                }
                return resultList;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public abstract V objectFromJson(JSONObject object) throws JSONException;

    protected String readResponse(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = reader.readLine();
        StringBuilder builder = new StringBuilder();
        while(true) {
            if(line == null) break;
            builder.append(line);
            line = reader.readLine();
        }
        return builder.toString();
    }
}
