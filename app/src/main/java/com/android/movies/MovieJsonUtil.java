package com.android.movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by trikh on 01-01-2017.
 */

public class MovieJsonUtil {

    public static ArrayList<Movie> fetchJsonFromResponse(String response) throws JSONException {
        final String imagePrefixUrl = "https://image.tmdb.org/t/p/w500";
        ArrayList<Movie> data = new ArrayList<>();

        JSONObject jsonResponse = new JSONObject(response);
        JSONArray result = jsonResponse.getJSONArray("results");

        for (int i = 0; i < result.length(); i++) {
            JSONObject movieJson = result.getJSONObject(i);

            //getting poster link
            String imagePostUrl = movieJson.getString("poster_path");
            String imageUrl = imagePrefixUrl + imagePostUrl.substring(2);

            //getting movie title
            String title = movieJson.getString("original_title");

            //getting synopsis
            String synopsis = movieJson.getString("overview");

            //getting rating
            String rating = movieJson.getString("vote_average");

            //getting release date
            String releaseDate = movieJson.getString("release_date");

            Movie movie = new Movie(imageUrl, rating, title, synopsis, releaseDate);

            data.add(movie);
        }

        return data;
    }
}
