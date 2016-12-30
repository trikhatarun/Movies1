package com.android.movies;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Boolean sort_popularity,sort_votes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        sort_popularity = preferences.getBoolean(getString(R.string.popularity),true);
        sort_votes = preferences.getBoolean(getString(R.string.votes),false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        MenuItem popularityButton,votesButton;
        popularityButton = (MenuItem) findViewById(R.id.popularity_button);
        votesButton = (MenuItem) findViewById(R.id.votes_button);
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
            }
        }
        return true;
    }
}
