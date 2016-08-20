package com.example.android.movie;

import java.util.ArrayList;

/**
 * Created by Shimaa on 8/20/2016.
 */
public interface QueryInterface {
   public abstract ArrayList<Movie> extractFeatureFromJson(String movieJSON);
}
