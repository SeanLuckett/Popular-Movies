package com.android.seanluckett.popularmovies.wrappers;

import com.android.seanluckett.popularmovies.models.FilmData;
import com.android.seanluckett.popularmovies.utils.ApiService;
import com.android.seanluckett.popularmovies.utils.FilmDataJsonUtils;

import java.util.ArrayList;

public class MovieApiWrapper {
    private ApiService apiService;

    public MovieApiWrapper(ApiService api) { apiService = api; }

    public ArrayList<FilmData> getMostPopular() {
        String response = apiService.getMostPopular();
        ArrayList<FilmData> movies = new ArrayList<>(FilmDataJsonUtils.parseMovieList(response));
        return movies;
    }

    // TODO implement
//    public ArrayList<FilmData> getTopRate() {}
}
