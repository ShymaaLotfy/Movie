package com.example.android.movie;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Shimaa on 8/27/2016.
 */
public class UpcomingFragment extends Fragment{

    private MovieAdapter adapter = null ;
    private ArrayList<Movie> result=null;
    private String UpComingUrl ="http://api.themoviedb.org/3/movie/upcoming?api_key=9d439968a128f2c3596337f5aaa636cc";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView =  inflater.inflate(R.layout.movie_list, container, false);

        // Start the AsyncTask to fetch the earthquake data
        QueryAsyncTask task = new QueryAsyncTask();

        try {
            result =task.execute(UpComingUrl).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //data adapter
        ListView movieListView = (ListView) rootView.findViewById(R.id.list);
        adapter = new MovieAdapter(this.getActivity(),result);
        movieListView.setAdapter(adapter);

        movieListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        // Find the view pager that will allow the user to swipe between fragments
                        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);

                        // Create an adapter that knows which fragment should be shown on each page
                        SimpleFragmentPageAdapter adapter = new SimpleFragmentPageAdapter(getFragmentManager());

                        // Set the adapter onto the view pager
                        viewPager.setAdapter(adapter);

                    }
                }

        );

        return rootView;

    }


}
