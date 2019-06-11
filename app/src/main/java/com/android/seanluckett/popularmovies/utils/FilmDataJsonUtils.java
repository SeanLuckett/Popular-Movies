package com.android.seanluckett.popularmovies.utils;

import android.net.Uri;

import com.android.seanluckett.popularmovies.models.FilmData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FilmDataJsonUtils {
    private static final String RESPONSE_DATA_KEY = "results";

    private static final String ID_KEY = "id";
    private static final String POSTER_KEY = "poster_path";

    private static final String TITLE_KEY = "title";
    private static final String PLOT_KEY = "overview";
    private static final String AVERAGE_VOTE_KEY = "vote_average";
    private static final String RELEASE_DATE_KEY = "release_date";


    public static ArrayList<FilmData> parseMovieList(String json) {
        ArrayList<FilmData> films = new ArrayList<>();

        try {
            JSONObject movieJson = new JSONObject(json);
            JSONArray movies = movieJson.getJSONArray(RESPONSE_DATA_KEY);
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
        int id = filmJson.getInt(ID_KEY);
        String title = filmJson.getString(TITLE_KEY);
        Uri posterPath = BuildFullPosterUri.execute(filmJson.getString(POSTER_KEY));
        String plotSummary = filmJson.getString(PLOT_KEY);
        Double userAverage = filmJson.getDouble(AVERAGE_VOTE_KEY);
        String releaseDate = filmJson.getString(RELEASE_DATE_KEY);

        return new FilmData(id, title, posterPath, plotSummary, userAverage, releaseDate);
    }
}
