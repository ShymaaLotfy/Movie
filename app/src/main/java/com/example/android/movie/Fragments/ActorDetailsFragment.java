package com.example.android.movie.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.movie.Models.Actors;
import com.example.android.movie.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Shimaa on 8/31/2016.
 */
public class ActorDetailsFragment extends Fragment{

    Actors actor;
    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        actor = (Actors) args.getSerializable("Actor");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View content = inflater.inflate(R.layout.actor_details_fragment, null);

        TextView name = (TextView)content.findViewById(R.id.name);
        TextView popularity = (TextView)content.findViewById(R.id.popularity);

        final ImageView poster = (ImageView)content.findViewById(R.id.profile);

        name.setText(actor.name);
        popularity.setText("Popularity : " + actor.popularity);


        Picasso.with(getContext())
                .load(actor.thumUrl())
                .into(poster);
        return content;

    }

}
