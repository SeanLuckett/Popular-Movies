package com.android.seanluckett.popularmovies.utils;

import android.net.Uri;

import com.android.seanluckett.popularmovies.BuildConfig;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MovieDbService implements ApiService {
    public static final String TAG = MovieDbService.class.getSimpleName();

    private final String API_QUERY_KEY = "api_key";
    private final String MOVIE_API_KEY = BuildConfig.MOVIE_API_KEY;

    private final String BASE_URL = "https://api.themoviedb.org";
    private final String API_VERSION = "3";
    private final String POPULAR_MOVIES_PATH = "movie/popular";
    private final String TOP_RATED_MOVIES_PATH = "movie/top_rated";

    private HttpURLConnection connection;
    private URL popularMoviesUrl;

    @Override
    public String getMostPopular() {
        try {
            popularMoviesUrl = buildUrl(POPULAR_MOVIES_PATH);
            connection = (HttpURLConnection) popularMoviesUrl.openConnection();

            InputStream in = new BufferedInputStream(connection.getInputStream());

            String json = buildJsonResponse(in);
            connection.disconnect();
            return json;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getTopRated() {
        try {
            popularMoviesUrl = buildUrl(TOP_RATED_MOVIES_PATH);
            connection = (HttpURLConnection) popularMoviesUrl.openConnection();

            InputStream in = new BufferedInputStream(connection.getInputStream());

            String json = buildJsonResponse(in);
            connection.disconnect();
            return json;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private URL buildUrl(String api_path) throws MalformedURLException {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
            .appendPath(API_VERSION)
            .appendEncodedPath(api_path)
            .appendQueryParameter(API_QUERY_KEY, MOVIE_API_KEY)
            .build();

        return new URL(builtUri.toString());
    }

    private String buildJsonResponse(InputStream in) throws IOException {
        Reader jsonReader = new InputStreamReader(in);
        BufferedReader reader = new BufferedReader(jsonReader);
        StringBuilder jsonResponse = new StringBuilder();

        String line;
        while((line = reader.readLine()) != null) {
            jsonResponse.append(line);
        }

        return jsonResponse.toString();
    }
}
