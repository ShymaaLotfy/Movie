package com.example.android.movie.Adapters;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.movie.Models.Actors;
import com.example.android.movie.Networking.ImageAsyncTask;
import com.example.android.movie.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Shimaa on 8/31/2016.
 */
public class ActorsAdapter extends BaseAdapter {

    List<Actors> list;

    public ActorsAdapter(){
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
        return 0;
    }

    public void addActors(List<Actors> actors){
        this.list.addAll(actors);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.actors_list_item,parent, false);

        }

        Actors currentActor  = list.get(position);

        TextView title = (TextView)convertView.findViewById(R.id.name);
        title.setText(currentActor.name);

        TextView popularity = (TextView)convertView.findViewById(R.id.popularity);
        popularity.setText("Popularity : " + currentActor.popularity);

        final  ImageView poster = (ImageView)convertView.findViewById(R.id.profile);
        ImageAsyncTask getImageBitmap = new ImageAsyncTask() {
            @Override
            protected void onPostExecute(Bitmap bitmap) {
                poster.setImageBitmap(bitmap);
            }
        };

        getImageBitmap.execute(currentActor.thumUrl());

        return convertView;
    }
}
