package com.android.seanluckett.popularmovies.models;

public class FilmData {

    private final String mTitle;
    private final String mPosterImagePath;
    private final String mPlot;
    private final Double mUserRating;
    private final String mReleaseDate;

    public FilmData(
        String title,
        String posterImagePath,
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

    public String getTitle() {
        return mTitle;
    }

    public String getPosterImagePath() {
        return mPosterImagePath;
    }

    public String getPlot() {
        return mPlot;
    }

    public Double getUserRating() {
        return mUserRating;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }
}