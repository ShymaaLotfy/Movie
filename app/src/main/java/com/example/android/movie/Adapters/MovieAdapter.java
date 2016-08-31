package com.example.android.movie.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.movie.Models.Movie;
import com.example.android.movie.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shimaa on 8/17/2016.
 */
public class MovieAdapter extends BaseAdapter {

    List<Movie> list;

    public MovieAdapter(){
       list = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).id;
    }

    public void addMovies(List<Movie> movies){
        this.list.addAll(movies);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_items,parent, false);

        }

        Movie currentMovie  = list.get(position);

        TextView title = (TextView)convertView.findViewById(R.id.movieTitle);
        title.setText(currentMovie.title);


        final  ImageView poster = (ImageView)convertView.findViewById(R.id.moviePoster);

        Picasso.with(convertView.getContext())
                .load(currentMovie.thumUrl())
                .into(poster);


        return convertView;
    }
}
