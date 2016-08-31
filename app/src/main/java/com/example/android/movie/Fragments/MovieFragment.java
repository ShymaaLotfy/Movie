package com.example.android.movie.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.movie.Activities.MovieClickActivity;
import com.example.android.movie.Adapters.MovieAdapter;
import com.example.android.movie.Models.Movie;
import com.example.android.movie.Networking.MovieDownloadTask;
import com.example.android.movie.R;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Shimaa on 8/27/2016.
 */
public class MovieFragment extends Fragment {

    public static final String KEY_TITLE = "title";
    public static final String KEY_URL = "url";

    ListView moviesList;
    MovieAdapter adapter;
    String url;
    String title;
    Movie clickedMovie;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        url = args.getString(KEY_URL);
        title = args.getString(KEY_TITLE);
        clickedMovie = (Movie) args.getSerializable("Movie");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View content = inflater.inflate(R.layout.movie_list, null);
        moviesList = (ListView) content.findViewById(R.id.list);
        adapter = new MovieAdapter();
        moviesList.setAdapter(adapter);
        MovieDownloadTask task = new MovieDownloadTask () {
            @Override
            protected void onPostExecute(List<Movie> movies) {
                adapter.addMovies(movies);
            }
        };
        task.execute(url);

        moviesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //start a new intents to show the movie details
                Intent intent = new Intent(getActivity(), MovieClickActivity.class );
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
