package com.android.seanluckett.popularmovies.models;

import android.net.Uri;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

// TODO: add a favorite flag field to support "soft" deleting
// Might be easier to toggle favorite rather than add/delete db rows
// A user is likely to waffle on whether a movie is a favorite. Would
// also optimize for accidental favorites.

@Entity(
    tableName = "favorites",
    indices = {@Index("movieDbId")}
    )
public class Favorite {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int movieDbId;

    private String title;
    private Uri posterImagePath;
    private String plotSummary;
    private double rating;
    private String releaseYear;

    @Ignore
    public Favorite(
        int movieDbId, String title,
        Uri posterImagePath, String plotSummary,
        double rating, String releaseYear
    ) {
        this.movieDbId = movieDbId;
        this.title = title;
        this.posterImagePath = posterImagePath;
        this.plotSummary = plotSummary;
        this.rating = rating;
        this.releaseYear = releaseYear;
    }

    public Favorite(
        int id, int movieDbId,
        String title, Uri posterImagePath,
        String plotSummary, double rating,
        String releaseYear
    ) {
        this.id = id;
        this.movieDbId = movieDbId;
        this.title = title;
        this.posterImagePath = posterImagePath;
        this.plotSummary = plotSummary;
        this.rating = rating;
        this.releaseYear = releaseYear;
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
}
