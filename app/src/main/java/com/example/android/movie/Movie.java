package com.example.android.movie;

/**
 * Created by Shimaa on 8/17/2016.
 */
public class Movie {

    private String mtitle ;
    private  String mOriginalTitle ;
    private String mPosterUrl;
    private int mId;

    public Movie(int id,String title, String  originalTitle, String posterUrl  ){
        mId = id;
        mtitle = title ;
        mOriginalTitle = originalTitle ;
        mPosterUrl = posterUrl;
    }

    public int getmId(){return mId;}
    public String getMtitle(){return  mtitle;}
    public String getmOriginalTitle(){return mOriginalTitle;}
    public String getmPosterUrl(){return mPosterUrl;}
}
