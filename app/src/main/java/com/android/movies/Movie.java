package com.android.movies;

/**
 * Created by trikh on 30-12-2016.
 */

class Movie {
    private String mImageUrl;
    private String mRating;
    private String mName;
    private String mSynopsis;
    private String mReleaseDate;

    Movie(String imageUrl, String rating, String name, String synopsis, String releaseDate) {
        this.mImageUrl = imageUrl;
        this.mRating = rating;
        this.mName = name;
        this.mSynopsis = synopsis;
        this.mReleaseDate = releaseDate;
    }

    String getmImageUrl() {
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

    public String getmReleaseDate() {
        return mReleaseDate;
    }
}
