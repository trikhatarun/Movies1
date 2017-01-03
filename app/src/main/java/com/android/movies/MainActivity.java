package com.android.movies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener, MovieAdapter.OnMovieClickListener {

    private Boolean sort_popularity,sort_votes;
    private MenuItem popularityButton, votesButton;
    private MovieAdapter mMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.rv_home_screen);
        mRecyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mMovieAdapter = new MovieAdapter(this, this);
        mRecyclerView.setAdapter(mMovieAdapter);
        loadData();
    }

    public void loadData() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        sort_popularity = preferences.getBoolean(getString(R.string.popularity), true);
        sort_votes = preferences.getBoolean(getString(R.string.votes), false);

        new FetchMoviesTask().execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        popularityButton = menu.findItem(R.id.popularity_button);
        votesButton = menu.findItem(R.id.votes_button);
        if (sort_popularity)
            popularityButton.setChecked(true);
        else
            votesButton.setChecked(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        if (item.getItemId() == R.id.popularity_button){
            if (!sort_popularity) {
                popularityButton.setChecked(true);
                votesButton.setChecked(false);
                editor.putBoolean(getString(R.string.popularity),true);
                editor.putBoolean(getString(R.string.votes),false);
                editor.apply();
                loadData();
            }
        }
        else {
            if (!sort_votes) {
                popularityButton.setChecked(false);
                votesButton.setChecked(true);
                editor.putBoolean(getString(R.string.popularity),false);
                editor.putBoolean(getString(R.string.votes),true);
                editor.apply();
                loadData();
            }
        }
        return true;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
    }

    @Override
    public void onClick(Movie currentMovie) {
        Intent details = new Intent(this, DetailScreen.class);
        details.putExtra("currentMovie", currentMovie);
        startActivity(details);
    }

    public class FetchMoviesTask extends AsyncTask<Void, Void, ArrayList<Movie>> {

        @Override
        protected ArrayList<Movie> doInBackground(Void... voids) {
            if (sort_popularity) {
                try {
                    return MovieJsonUtil.fetchJsonFromResponse(NetworkUtils.getResponseFromUrl(NetworkUtils.buildUrl(getString(R.string.popularity))));
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            } else
                try {
                    Log.v("doInBackground: ", "called votes");
                    return MovieJsonUtil.fetchJsonFromResponse(NetworkUtils.getResponseFromUrl(NetworkUtils.buildUrl(getString(R.string.votes))));
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> list) {
            if (list != null) {
                mMovieAdapter.setMovieList(list);
            } else {
                mMovieAdapter.setMovieList(null);
                Log.e("Data: ", "No data available");
            }
        }
    }
}
