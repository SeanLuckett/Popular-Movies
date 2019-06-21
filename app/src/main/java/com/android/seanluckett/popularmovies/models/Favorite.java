package com.android.seanluckett.popularmovies.models;

import android.net.Uri;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorites", indices = {@Index("movieDbId")})
public class Favorite {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int movieDbId;

    private String title;
    private Uri posterImagePath;
    private String plotSummary;
    private double rating;
    private String releaseYear;
    private boolean isFavorite;

    @Ignore
    public Favorite(
        int movieDbId, String title,
        Uri posterImagePath, String plotSummary,
        double rating, String releaseYear
    ) {
        initializeFavorite(movieDbId, title, posterImagePath, plotSummary,
            rating, releaseYear, false);
    }

    public Favorite(
        int id, int movieDbId,
        String title, Uri posterImagePath,
        String plotSummary, double rating,
        String releaseYear
    ) {
        this.id = id;
        initializeFavorite(movieDbId, title, posterImagePath, plotSummary,
            rating, releaseYear, false);
    }

    public void initializeFavorite(
        int movieDbId, String title,
        Uri posterImagePath, String plotSummary,
        double rating, String releaseYear,
        boolean isFavorite
    ) {
        this.movieDbId = movieDbId;
        this.title = title;
        this.posterImagePath = posterImagePath;
        this.plotSummary = plotSummary;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.isFavorite = isFavorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieDbId() {
        return movieDbId;
    }

    public String getTitle() {
        return title;
    }

    public Uri getPosterImagePath() {
        return posterImagePath;
    }

    public String getPlotSummary() {
        return plotSummary;
    }

    public double getRating() {
        return rating;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public void toggleFavorite() {
        this.isFavorite = isFavorite() ? false : true;
    }
}
