package com.android.seanluckett.popularmovies.models;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

// TODO use parceler library to handle boilerplate some day
// https://github.com/johncarl81/parceler

public class FilmData implements Parcelable {

    private final String mTitle;
    private final Uri mPosterImagePath;
    private final String mPlot;
    private final Double mUserRating;
    private final String mReleaseDate;

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mPlot);
        dest.writeString(mReleaseDate);
        dest.writeString(mPosterImagePath.toString());

        dest.writeDouble(mUserRating);
    }

    private FilmData(Parcel in) {
        mTitle = in.readString();
        mPlot = in.readString();
        mReleaseDate = in.readString();
        mPosterImagePath = Uri.parse(in.readString());

        mUserRating = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public FilmData(
        String title,
        Uri posterImagePath,
        String plot,
        Double userRating,
        String releaseDate
    ) {
        mTitle = title;
        mPosterImagePath = posterImagePath;
        mPlot = plot;
        mUserRating = userRating;
        mReleaseDate = releaseDate;
    }

    public Uri getPosterImagePath() {
        return mPosterImagePath;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getPlot() {
        return mPlot;
    }

    public Double getUserRating() {
        return mUserRating;
    }

    public String getReleaseDate() { return mReleaseDate; }

    public static final Parcelable.Creator<FilmData> CREATOR =
        new Parcelable.Creator<FilmData>() {

            @Override
            public FilmData createFromParcel(Parcel source) {
                return new FilmData(source);
            }

            @Override
            public FilmData[] newArray(int size) {
                return new FilmData[size];
            }
        };
}
