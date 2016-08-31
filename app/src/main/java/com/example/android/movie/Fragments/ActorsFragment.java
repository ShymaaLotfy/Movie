package com.example.android.movie.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.movie.Activities.ActorClickActivity;
import com.example.android.movie.Activities.MovieClickActivity;
import com.example.android.movie.Adapters.ActorsAdapter;
import com.example.android.movie.Adapters.CastAdapter;
import com.example.android.movie.Adapters.MovieAdapter;
import com.example.android.movie.Models.Actors;
import com.example.android.movie.Models.Movie;
import com.example.android.movie.Networking.ActorsDownloadTask;
import com.example.android.movie.R;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Shimaa on 8/31/2016.
 */
public class ActorsFragment extends Fragment {

    public static final String KEY_TITLE = "title";
    public static final String KEY_URL = "url";
    public ActorsAdapter adapter;

    String url;
    String title;
    Actors clickedActor;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        url = args.getString(KEY_URL);
        title = args.getString(KEY_TITLE);
        clickedActor = (Actors) args.getSerializable("Actor");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View content = inflater.inflate(R.layout.movie_list, null);
        ListView actorsList = (ListView) content.findViewById(R.id.list);
        adapter = new ActorsAdapter();
        actorsList.setAdapter(adapter);
        final ActorsDownloadTask task = new ActorsDownloadTask() {
            @Override
            protected void onPostExecute(List<Actors> actors) {
                adapter.addActors(actors);
            }
        };
        task.execute(url);


       actorsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //start a new intents to show the movie details
                Intent intent = new Intent(getActivity(), ActorClickActivity.class );
                //send the clicked movie information to the new intent
                Bundle bundle = new Bundle();
                bundle.putSerializable("clickedItem", (Serializable) adapter.getItem(position));
                intent.putExtras(bundle);
                //start the intent
                startActivity(intent);

            }
        });


        return content;
    }
}
