package com.example.android.movie.Fragments;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.style.IconMarginSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.movie.Models.Movie;
import com.example.android.movie.Networking.ImageAsyncTask;
import com.example.android.movie.R;

import java.util.concurrent.ExecutionException;

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
        ImageView poster = (ImageView)content.findViewById(R.id.movieFirstPic);

        title.setText(movie.title);
        overView.setText(movie.overview);
        releaseDate.setText(movie.releaseDate);

        ImageAsyncTask getImageBitmap = new ImageAsyncTask() ;
        try {
            Bitmap bitmap = getImageBitmap.execute(movie.thumUrl()).get();
            poster.setImageBitmap(bitmap);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return content;

    }

}
