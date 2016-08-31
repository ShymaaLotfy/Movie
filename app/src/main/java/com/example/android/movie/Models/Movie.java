package com.example.android.movie.Models;

import com.example.android.movie.Networking.Urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Shimaa on 8/17/2016.
 */
public class Movie implements Serializable{

    public String title ;
    public  String originalTitle ;
    public String posterPath , posterUrl;
    public int id;
    public double rate;
    public String overview,mBackdropUrl,mStatus,releaseDate,mTagLine,mDescription, mBudget,mRunTime,mRevenue,mHomePage , backdropUrl;
    public ArrayList<String> genere,productionCompany,productionCountries;

    public Movie(int id,String title, String  originalTitle, String posterUrl ){
        this.id = id;
        this.title = title ;
        this.originalTitle = originalTitle ;
        this.posterPath = posterUrl;
    }

    public Movie(JSONObject movieObject) throws JSONException {
        id = movieObject.getInt("id");
        title = movieObject.getString("title");
        overview = movieObject.getString("overview");
        posterPath = movieObject.getString("poster_path");
        releaseDate = movieObject.getString("release_date");
        posterUrl = thumUrl();
     //   mStatus = movieObject.getString("status");
   //     mTagLine = movieObject.getString("tagline");

 //      backdropUrl = "http://image.tmdb.org/t/p/w185/" + movieObject.getString("backdrop_path");
//
//        JSONArray GenereArray = movieObject.getJSONArray("genres");
//        genere = new ArrayList<String>();
//        for (int i=0 ; i<GenereArray.length() ;i++)
//        {
//            genere.add(GenereArray.getJSONObject(i).getString("name"));
//        }

//        productionCompany = new ArrayList<String>();
//        JSONArray prodCompArray = movieObject.getJSONArray("production_companies");
//        for (int i=0 ; i < prodCompArray.length();i++){
//            productionCompany.add(prodCompArray.getJSONObject(i).getString("name"));
//        }
//        productionCountries = new ArrayList<String>() ;
//        JSONArray prodCountArray = movieObject.getJSONArray("production_countries");
//        for (int i=0 ; i < prodCountArray.length();i++){
//            productionCountries.add(prodCountArray.getJSONObject(i).getString("name"));
//        }
//
//       // mBudget = movieObject.getString("budget");
//        //mRunTime = movieObject.getString("runtime");
//        mRevenue = movieObject.getString("revenue");
//        mHomePage = movieObject.getString("homepage");
    }

    public String thumUrl() {
        return Urls.IMAGE_BASE_URL + "w92" + posterPath;
    }

}
