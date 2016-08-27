package com.example.android.movie;

/**
 * Created by Shimaa on 8/17/2016.
 */
public class Movie {

    private String mtitle ;
    private  String mOriginalTitle ;
    private int mPosterResourceId;

    public Movie(String title, String  originalTitle  ){
        mtitle = title ;
        mOriginalTitle = originalTitle ;
       // mPosterResourceId = posterResourceId;
    }

    public String getMtitle(){return  mtitle;}
    public String getmOriginalTitle(){return mOriginalTitle;}
    public int getmPosterResourceId(){return mPosterResourceId;}
}
