package com.android.seanluckett.popularmovies.wrappers;

import android.widget.ImageView;

import com.android.seanluckett.popularmovies.R;
import com.android.seanluckett.popularmovies.models.Favorite;
import com.android.seanluckett.popularmovies.models.FilmData;
import com.android.seanluckett.popularmovies.models.ReviewData;
import com.android.seanluckett.popularmovies.models.TrailerData;
import com.android.seanluckett.popularmovies.utils.ApiService;
import com.android.seanluckett.popularmovies.utils.FilmDataJsonUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieApiWrapper {
    private final ApiService apiService;

    public static void loadPosterIntoView(ImageView view, FilmData movie) {
        Picasso.get()
            .load(movie.getPosterImagePath())
            .placeholder(R.mipmap.poster_placeholder)
            .error(R.drawable.ic_error_outline_accent_24dp)
            .into(view);
    }

    public static void loadPosterIntoView(ImageView view, Favorite favorite) {
        Picasso.get()
            .load(favorite.getPosterImagePath())
            .placeholder(R.mipmap.poster_placeholder)
            .error(R.drawable.ic_error_outline_accent_24dp)
            .into(view);
    }

    public MovieApiWrapper(ApiService api) { apiService = api; }

    public ArrayList<FilmData> getMostPopular() {
        String response = apiService.getMostPopular();
        return parseList(response);
    }

    public ArrayList<FilmData> getTopRated() {
        String response = apiService.getTopRated();
        return parseList(response);
    }

    public ArrayList<TrailerData> getTrailers(int id) {
        String response = apiService.getTrailers(id);
        return parseTrailers(response);
    }

    public ArrayList<ReviewData> getReviews(int id) {
        String response = apiService.getReviews(id);
        return parseReviews(response);
    }

    private ArrayList<FilmData> parseList(String json) {
        if (json != null) {
            return new ArrayList<>(FilmDataJsonUtils.parseMovieList(json));
        } else {
            return new ArrayList<>();
        }
    }

    private ArrayList<TrailerData> parseTrailers(String json) {
        if (json != null) {
            return new ArrayList<>(FilmDataJsonUtils.parseTrailerList(json));
        } else {
            return new ArrayList<>();
        }
    }

    private ArrayList<ReviewData> parseReviews(String json) {
        if (json != null) {
            return new ArrayList<>(FilmDataJsonUtils.parseReviewList(json));
        } else {
            return new ArrayList<>();
        }
    }
}
