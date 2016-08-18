package com.example.android.movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Shimaa on 8/17/2016.
 */
public class MovieAdapter extends ArrayAdapter<Movie> {

    //constructor
    public MovieAdapter(Context context, int resource) {
        super(context,0, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_list, parent, false);

        }

        Movie currentMovie  = getItem(position);

        TextView title = (TextView)convertView.findViewById(R.id.movieTitle);
        title.setText(currentMovie.getMtitle());

        TextView id = (TextView)convertView.findViewById(R.id.movieId);
        //id.setText(currentMovie.getmId());

        return convertView;
    }
}
