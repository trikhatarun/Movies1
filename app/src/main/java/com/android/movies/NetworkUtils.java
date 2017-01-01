package com.android.movies;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by trikh on 28-12-2016.
 */

class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String BASE_URL = "https://api.themoviedb.org/3/discover/movie";
    private static final String API_KEY_PARAM = "api_key";
    private static final String SORT_BY_PARAM = "sort_by";
    private static final String VIDEO_PARAM = "include_video";
    private static final String PAGE_PARAM = "page";
    private static final String VOTE_COUNT = "vote_count.gte";

    private final static String key = "97f469b9e89b30f1f7d07e8b35973e56";
    private final static Boolean video = false;
    private final static int page = 1;
    private final static int vote = 500;

    static URL buildUrl(String sortBy) {
        Uri.Builder tempUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(API_KEY_PARAM,key)
                .appendQueryParameter(VIDEO_PARAM,video.toString())
                .appendQueryParameter(PAGE_PARAM,String.valueOf(page))
                .appendQueryParameter(VOTE_COUNT,String.valueOf(vote));

        Uri builtUri;
        URL url = null;
        Log.v(TAG, sortBy);
        if (sortBy.equals("votes")) {
            builtUri = tempUri.appendQueryParameter(SORT_BY_PARAM,"vote_average.desc").build();
        }
        else
            builtUri = tempUri.appendQueryParameter(SORT_BY_PARAM,"popularity.desc").build();

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    static String getResponseFromUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream inputStream = urlConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");

            if (scanner.hasNext()){
                return scanner.next();
            }
            else return null;
        }finally {
            urlConnection.disconnect();
        }
    }
}
