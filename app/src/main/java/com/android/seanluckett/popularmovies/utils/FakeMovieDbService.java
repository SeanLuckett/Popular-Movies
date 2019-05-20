package com.android.seanluckett.popularmovies.utils;

import android.content.Context;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


public class FakeMovieDbService extends ApiService {
    Context caller;

    public FakeMovieDbService(Context activity) {
        caller = activity;
    }

    @Override
    public String getMostPopular() {
        StringBuilder jsonResponse = new StringBuilder();

        try {
            InputStream mostPopularJson = caller.getAssets().open("moviedb_most_popular_response.json");
            Reader jsonReader = new InputStreamReader(mostPopularJson);
            BufferedReader reader = new BufferedReader(jsonReader);
            String line;

            while((line = reader.readLine()) != null) {
                jsonResponse.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonResponse.toString();
    }

    @Override
    public String getTopRated() {
        StringBuilder jsonResponse = new StringBuilder();

        try {
            InputStream topRatedJson = caller.getAssets().open("moviedb_top_rated_response.json");
            Reader jsonReader = new InputStreamReader(topRatedJson);
            BufferedReader reader = new BufferedReader(jsonReader);
            String line;

            while((line = reader.readLine()) != null) {
                jsonResponse.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonResponse.toString();
    }
}
