package com.example.android.movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Shimaa on 8/17/2016.
 */
public class MovieAdapter extends ArrayAdapter<Movie> {

    //constructor
    public MovieAdapter(Context context, ArrayList<Movie> objects) {
        super(context,0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_list_items, parent, false);

        }

        Movie currentMovie  = getItem(position);

        TextView title = (TextView)convertView.findViewById(R.id.movieTitle);
        title.setText(currentMovie.getMtitle());

        TextView originalTitle = (TextView)convertView.findViewById(R.id.originalMovieTitle);
        title.setText(currentMovie.getmOriginalTitle());

        return convertView;
    }
}
