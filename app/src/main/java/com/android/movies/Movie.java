package com.android.movies;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by trikh on 30-12-2016.
 */

class Movie implements Parcelable {
    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
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

    protected Movie(Parcel in) {
        mImageUrl = in.readString();
        mRating = in.readString();
        mName = in.readString();
        mSynopsis = in.readString();
        mReleaseDate = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mImageUrl);
        parcel.writeString(mRating);
        parcel.writeString(mName);
        parcel.writeString(mSynopsis);
        parcel.writeString(mReleaseDate);
    }
}
