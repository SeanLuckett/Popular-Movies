package com.android.seanluckett.popularmovies.models;

public class FilmData {

    private String mTitle;
    private String mPosterImagePath;
    private String mPlot;
    private Double mUserRating;
    private String mReleaseDate;

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
