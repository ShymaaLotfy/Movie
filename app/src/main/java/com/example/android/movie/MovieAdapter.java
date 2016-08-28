package com.example.android.movie;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.support.v4.app.LoaderManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

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
        originalTitle.setText(currentMovie.getmOriginalTitle());

        ImageView poster = (ImageView)convertView.findViewById(R.id.moviePoster);
        ImageAsyncTask getImageBitmap = new ImageAsyncTask() ;
        try {
            Bitmap bitmap = getImageBitmap.execute(currentMovie.getmPosterUrl()).get();
            poster.setImageBitmap(bitmap);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return convertView;
    }

}
