package com.example.android.movie;

/**
 * Created by Shimaa on 8/17/2016.
 */
public class Movie {

    private String mtitle ;
    private  int mId ;

    public Movie(String title, int id){
        mtitle = title ;
        mId = id ;
    }

    public String getMtitle(){return  mtitle;}
    public int getmId(){return mId;}
}
