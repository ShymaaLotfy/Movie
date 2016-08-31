package com.example.android.movie.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;

import com.example.android.movie.Adapters.ActorsAdapter;
import com.example.android.movie.Adapters.CastAdapter;
import com.example.android.movie.Models.Actors;
import com.example.android.movie.Models.Movie;
import com.example.android.movie.Networking.ActorsDownloadTask;
import com.example.android.movie.R;

import java.util.List;

/**
 * Created by Shimaa on 8/31/2016.
 */
public class ActorsFragment extends Fragment {

    Movie movie;
    String url ;
    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        movie = (Movie) args.getSerializable("Movie");
        url = args.getString("url");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View content = inflater.inflate(R.layout.movie_list, null);
        ListView moviesList = (ListView) content.findViewById(R.id.list);
        final ActorsAdapter adapter = new ActorsAdapter();
        moviesList.setAdapter(adapter);
        final ActorsDownloadTask task = new ActorsDownloadTask() {
            @Override
            protected void onPostExecute(List<Actors> actors) {
                adapter.addActors(actors);
            }
        };
        task.execute(url);
        return content;
    }
}
