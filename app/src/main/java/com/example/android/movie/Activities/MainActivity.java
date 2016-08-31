
package com.example.android.movie.Activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.movie.Adapters.MovieAdapter;

import com.example.android.movie.Fragments.ActorsFragment;
import com.example.android.movie.Fragments.MovieFragment;
import com.example.android.movie.Models.Movie;
import com.example.android.movie.Networking.MovieDownloadTask;
import com.example.android.movie.Networking.Urls;
import com.example.android.movie.R;

import java.io.Serializable;
import java.util.List;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    public MovieAdapter popularAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //the outer list
        final ListView movieListView = (ListView) findViewById(R.id.outerPopularList);
        popularAdapter = new MovieAdapter();
        movieListView.setAdapter(popularAdapter);
        // Start the AsyncTask to fetch the popular data to the main activity
        MovieDownloadTask task = new MovieDownloadTask(){
            @Override
            protected void onPostExecute(List<Movie> movies) {
                popularAdapter.addMovies(movies);
            }
        };
        task.execute(Urls.POPULAR);
        movieListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //start a new intents to show the movie details
                         Intent intent = new Intent(MainActivity.this, MovieClickActivity.class );
                        //send the clicked movie information to the new intent
                         Bundle bundle = new Bundle();
                         bundle.putSerializable("clickedItem", (Serializable) popularAdapter.getItem(position));
                         intent.putExtras(bundle);
                         //start the intent
                         startActivity(intent);
                    }
                }

        );

        // Navigation Drawer list
        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout)  findViewById(R.id.drawer_layout);
        mDrawerList = (ListView)  findViewById(R.id.nvDrawerList);
        // Set the adapter for the list view
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mNavigationDrawerItemTitles);
        mDrawerList.setAdapter(adapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment f = new MovieFragment();
                Bundle args = new Bundle();
                String url = null ;
                String title = null ;

                switch (position) {
                    case 0:
                        url = Urls.POPULAR;
                        title = "Popular";
                        break;
                    case 1 :
                        url = Urls.TOP_RATED;
                        title = "Top Rated";
                        break;
                    case 2 :
                        url = Urls.NOW_PLAYING;
                        title = "Now Playing";
                        break;
                    case 3 :
                        url = Urls.UPCOMING;
                        title = "UpComing";
                        break;
                    case 4 :
                        f = new ActorsFragment();
                        url = Urls.POPULAR_PEOPLE ;
                        title = "Popluar People";

                }

                args.putString(MovieFragment.KEY_URL, url);
                args.putString(MovieFragment.KEY_TITLE, title);
                f.setArguments(args);

                if (f != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, f).commit();

                    mDrawerList.setItemChecked(position, true);
                    mDrawerList.setSelection(position);
                    // getActionBar().setTitle(mNavigationDrawerItemTitles[position]);
                    mDrawerLayout.closeDrawer(mDrawerList);

                } else {
                    Log.e("MainActivity", "Error in creating fragment");
                }
            }
        });


    }


    //search methods
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {

        String url = Urls.BASE_SEARCH_URL + query;

        ListView movieListView = (ListView) findViewById(R.id.outerPopularList);
        popularAdapter = new MovieAdapter();
        movieListView.setAdapter(popularAdapter);

       MovieDownloadTask task = new MovieDownloadTask() {
            @Override
            protected void onPostExecute(List<Movie> movies) {

                popularAdapter.addMovies(movies);

            }
        };
        task.execute(url);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
