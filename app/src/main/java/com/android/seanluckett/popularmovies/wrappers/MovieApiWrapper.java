package com.android.seanluckett.popularmovies.wrappers;

import android.widget.ImageView;

import com.android.seanluckett.popularmovies.models.FilmData;
import com.android.seanluckett.popularmovies.utils.ApiService;
import com.android.seanluckett.popularmovies.utils.FilmDataJsonUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieApiWrapper {
    private final ApiService apiService;

    public static void loadPosterIntoView(ImageView view, FilmData movie) {
        // TODO add placeholder and possibly error images
        Picasso.get()
            .load(movie.getPosterImagePath())
            .into(view);
    }

    public MovieApiWrapper(ApiService api) { apiService = api; }

    public ArrayList<FilmData> getMostPopular() {
        String response = apiService.getMostPopular();

        if (response  != null) {
            return parseList(response);
        } else {
            return new ArrayList<>();
        }
    }

    public ArrayList<FilmData> getTopRated() {
        String response = apiService.getTopRated();

        if (response  != null) {
            return parseList(response);
        } else {
            return new ArrayList<>();
        }
    }

    private ArrayList<FilmData> parseList(String json) {
        return new ArrayList<>(FilmDataJsonUtils.parseMovieList(json));
    }
}
