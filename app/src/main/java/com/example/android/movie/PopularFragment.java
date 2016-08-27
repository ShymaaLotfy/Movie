package com.example.android.movie;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Shimaa on 8/27/2016.
 */
public class PopularFragment extends Fragment {

    private MovieAdapter adapter = null ;
    private ArrayList<Movie> result=null;
    private String PopularUrl ="http://api.themoviedb.org/3/movie/popular?api_key=9d439968a128f2c3596337f5aaa636cc" ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.movie_list, container, false);

        // Start the AsyncTask to fetch the earthquake data
        QueryAsyncTask task = new QueryAsyncTask();

        try {
            result =task.execute(PopularUrl).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (container != null) {
            container.removeAllViews();
        }
        //data adapter
        ListView movieListView = (ListView) rootView.findViewById(R.id.list);
        adapter = new MovieAdapter(this.getActivity(),result);
        movieListView.setAdapter(adapter);

        return rootView;

    }


}
