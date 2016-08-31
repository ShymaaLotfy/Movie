package com.example.android.movie.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.movie.Models.Movie;
import com.example.android.movie.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Shimaa on 8/30/2016.
 */
public class MovieDetailsFragment extends Fragment {

    Movie movie;
    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        movie = (Movie) args.getSerializable("Movie");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View content = inflater.inflate(R.layout.movie_details_fragment, null);

        TextView title = (TextView)content.findViewById(R.id.movieName);
        TextView overView = (TextView)content.findViewById(R.id.descriptionTextView);
        TextView releaseDate = (TextView)content.findViewById(R.id.releaseDateTextView);
        final ImageView poster = (ImageView)content.findViewById(R.id.movieFirstPic);

        title.setText(movie.title);
        overView.setText(movie.overview);
        releaseDate.setText(movie.releaseDate);
        Picasso.with(getContext())
                .load(movie.thumUrl())
                .into(poster);


        return content;

    }

}
