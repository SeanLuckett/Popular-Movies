package com.android.seanluckett.popularmovies.models;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// TODO use parceler library to handle boilerplate some day
// https://github.com/johncarl81/parceler

public class FilmData implements Parcelable {
    public static final String TAG = FilmData.class.getSimpleName();

    private final int mId;
    private final String mTitle;
    private final Uri mPosterImagePath;
    private final String mPlot;
    private final Double mUserRating;
    private final String mReleaseDate;
    private String mReleaseYear;

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mTitle);
        dest.writeString(mPlot);
        dest.writeString(mReleaseDate);
        dest.writeString(mPosterImagePath.toString());

        dest.writeDouble(mUserRating);
    }

    private FilmData(Parcel in) {
        mId = in.readInt();
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
        int id,
        String title,
        Uri posterImagePath,
        String plot,
        Double userRating,
        String releaseDate
    ) {
        mId = id;
        mTitle = title;
        mPosterImagePath = posterImagePath;
        mPlot = plot;
        mUserRating = userRating;
        mReleaseDate = releaseDate;
    }

    /********** Getters ***********/

    public Uri getPosterImagePath() {
        return mPosterImagePath;
    }

    public String getTitle() {
        return (mTitle != null) ? mTitle : "";
    }

    public String getPlot() {
        return (mPlot != null) ? mPlot : "";
    }

    public Double getUserRating() {
        return (mUserRating != null) ? mUserRating : 0.0d;
    }

    public String getReleaseDate() { return (mReleaseDate != null) ? mReleaseDate : ""; }

    public String getReleaseYear() {
        if (mReleaseYear != null) { return mReleaseYear; }

        mReleaseYear = parseReleaseYear();
        return mReleaseYear;
    }

    /********** Setters ***********/


    private String parseReleaseYear() {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = parser.parse(mReleaseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        return formatter.format(date);
    }

    /********** Parcelable Creator ***********/

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
