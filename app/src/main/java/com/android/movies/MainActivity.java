package com.android.movies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private Boolean sort_popularity,sort_votes;
    private MenuItem popularityButton, votesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        sort_popularity = preferences.getBoolean(getString(R.string.popularity),true);
        sort_votes = preferences.getBoolean(getString(R.string.votes),false);

        Log.v("Popularity: ", sort_popularity.toString());
        Log.v("Votes: ", sort_votes.toString());
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
            Toast.makeText(this, "clicked pop", Toast.LENGTH_SHORT).show();
            if (sort_popularity == false){
                popularityButton.setChecked(true);
                votesButton.setChecked(false);
                editor.putBoolean(getString(R.string.popularity),true);
                editor.putBoolean(getString(R.string.votes),false);
                editor.commit();
                restartActivity();
            }
        }
        else {
            Toast.makeText(this, "clicked vot", Toast.LENGTH_SHORT).show();
            if (sort_votes == false){
                popularityButton.setChecked(false);
                votesButton.setChecked(true);
                editor.putBoolean(getString(R.string.popularity),false);
                editor.putBoolean(getString(R.string.votes),true);
                editor.commit();
                restartActivity();
            }
        }
        return true;
    }

    void restartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
    }
}
