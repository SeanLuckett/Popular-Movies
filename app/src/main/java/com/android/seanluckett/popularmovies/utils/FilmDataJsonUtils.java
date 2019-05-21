package com.android.seanluckett.popularmovies.utils;

import android.util.Log;

import com.android.seanluckett.popularmovies.models.FilmData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FilmDataJsonUtils {
    private static final String TITLE_KEY = "title";
    private static final String POSTER_KEY = "poster_path";
    private static final String PLOT_KEY = "overview";
    private static final String AVERAGE_VOTE_KEY = "vote_average";
    private static final String RELEASE_DATE_KEY = "release_date";

    private static ArrayList<FilmData> films = new ArrayList<>();

    // TODO errors here

    public static ArrayList<FilmData> parseMovieList(String json) {
        try {
            JSONObject movieJson = new JSONObject(json);
            JSONArray movies = movieJson.getJSONArray("results");
            FilmData film;

            for (int i = 0; i < movies.length(); i++) {
                JSONObject filmJson = movies.getJSONObject(i);
                film = parseMovieJson(filmJson);
                films.add(film);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return films;
    }

    private static FilmData parseMovieJson(JSONObject filmJson) throws JSONException {
        String title = filmJson.getString(TITLE_KEY);
        String posterPath = filmJson.getString(POSTER_KEY);
        String plotSummary = filmJson.getString(PLOT_KEY);
        Double userAverage = filmJson.getDouble(AVERAGE_VOTE_KEY);
        String releaseDate = filmJson.getString(RELEASE_DATE_KEY);

        return new FilmData(title, posterPath, plotSummary, userAverage, releaseDate);
    }
}
