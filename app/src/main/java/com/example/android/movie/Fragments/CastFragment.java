package com.example.android.movie.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.movie.Activities.ActorClickActivity;
import com.example.android.movie.Adapters.CastAdapter;
import com.example.android.movie.Models.Actors;
import com.example.android.movie.Models.Movie;
import com.example.android.movie.Networking.CastDownloadTask;
import com.example.android.movie.R;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Shimaa on 8/30/2016.
 */
public class CastFragment extends Fragment {


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
       ListView castList = (ListView) content.findViewById(R.id.list);
       final CastAdapter adapter = new CastAdapter();
        castList.setAdapter(adapter);
        CastDownloadTask task = new CastDownloadTask() {
            @Override
            protected void onPostExecute(List<Actors> actors) {
                adapter.addActors(actors);
            }
        };
        task.execute(url);

        castList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
