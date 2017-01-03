package com.android.movies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_screen);
        Bundle dataPack = getIntent().getExtras();

        Movie currentMovie = (Movie) dataPack.get("currentMovie");

        String title, synopsis, posterUrl, rating, releaseDate;

        title = currentMovie.getmName();
        synopsis = currentMovie.getmSynopsis();
        posterUrl = currentMovie.getmImageUrl();
        rating = currentMovie.getmRating();
        releaseDate = currentMovie.getmReleaseDate();

        setTitle(title);

        TextView synopsis_tv, rating_tv, releaseDate_tv;

        ImageView poster = (ImageView) findViewById(R.id.poster);

        Picasso.with(this).load(posterUrl).into(poster);

        synopsis_tv = (TextView) findViewById(R.id.synopsis_content);
        rating_tv = (TextView) findViewById(R.id.rating);
        releaseDate_tv = (TextView) findViewById(R.id.release_date);

        synopsis_tv.setText(synopsis);
        releaseDate_tv.setText(releaseDate);
        rating_tv.setText(rating);
    }
}
