package com.android.seanluckett.popularmovies.wrappers;

import com.android.seanluckett.popularmovies.models.FilmData;
import com.android.seanluckett.popularmovies.utils.ApiService;
import com.android.seanluckett.popularmovies.utils.FilmDataJsonUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MovieApiWrapper {
    private ApiService apiService;

    public MovieApiWrapper(ApiService api) { apiService = api; }

    public ArrayList<FilmData> getMostPopular() {
        String response = apiService.getMostPopular();
        return parseList(response);
    }

    public ArrayList<FilmData> getTopRated() {
        String response = apiService.getTopRated();
        return parseList(response);
    }

    private ArrayList<FilmData> parseList(String json) {
        return new ArrayList<>(FilmDataJsonUtils.parseMovieList(json));
    }
}
