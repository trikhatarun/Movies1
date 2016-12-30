package com.android.movies;

/**
 * Created by trikh on 30-12-2016.
 */

public class Movie {
    private String mImageUrl;
    private String mRating;
    private String mName;
    private String mSynopsis;

    Movie(String imageUrl, String rating, String name, String synopsis){
        this.mImageUrl = imageUrl;
        this.mRating = rating;
        this.mName = name;
        this.mSynopsis = synopsis;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getmRating() {
        return mRating;
    }

    public String getmName() {
        return mName;
    }

    public String getmSynopsis() {
        return mSynopsis;
    }
}
