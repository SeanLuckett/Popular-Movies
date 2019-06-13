package com.android.seanluckett.popularmovies.utils;

import android.net.Uri;

import com.android.seanluckett.popularmovies.models.FilmData;
import com.android.seanluckett.popularmovies.models.TrailerData;

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

    private static final String TRAILER_NAME_KEY = "name";
    private static final String TRAILER_TYPE_KEY = "type";
    private static final String TRAILER_SIZE_KEY = "size";
    private static final String TRAILER_KEY_KEY = "key";


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

    public static ArrayList<TrailerData> parseTrailerList(String json) {
        ArrayList<TrailerData> trailers = new ArrayList<>();

        try {
            JSONObject trailerJson = new JSONObject(json);
            JSONArray trailersArray = trailerJson.getJSONArray(RESPONSE_DATA_KEY);
            TrailerData trailer;

            for (int i = 0; i < trailersArray.length(); i++) {
                JSONObject filmJson = trailersArray.getJSONObject(i);
                trailer = parseTrailerJson(filmJson);
                trailers.add(trailer);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return trailers;
    }

    private static TrailerData parseTrailerJson(JSONObject trailerJson) throws JSONException {
        String name = trailerJson.getString(TRAILER_NAME_KEY);
        String type = trailerJson.getString(TRAILER_TYPE_KEY);
        String size = trailerJson.getString(TRAILER_SIZE_KEY);
        String key = trailerJson.getString(TRAILER_KEY_KEY);

        return new TrailerData(name, type, size, key);
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
