package com.android.seanluckett.popularmovies.utils;

import android.content.Context;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/*
    This class was a helper for me to work on the design without having to make http
    calls. Once it was replaced with the http service, this is no longer used. I still
    think it's useful for any future cases where I'd want to take this "off line" to
    add more design.

    Tradeoff: this has to stay in sync with http service, so eventually might go away.
 */

public class FakeMovieDbService implements ApiService {
    // Needed to get json files from assets folder. There should be a better way
    // for an Android project to use local files without having to go through assets,
    // but I spent a couple hours trying to figure it out.
    private final Context caller;

    public FakeMovieDbService(Context activity) {
        caller = activity;
    }

    @Override
    public String getMostPopular() {
        StringBuilder jsonResponse = new StringBuilder();
        return readJsonAsset(jsonResponse, "moviedb_most_popular_response.json");
    }

    @Override
    public String getTopRated() {
        StringBuilder jsonResponse = new StringBuilder();
        return readJsonAsset(jsonResponse, "moviedb_top_rated_response.json");
    }

    private String readJsonAsset(StringBuilder jsonResponse, String s) {
        try {
            InputStream topRatedJson = caller.getAssets().open(s);
            Reader jsonReader = new InputStreamReader(topRatedJson);
            BufferedReader reader = new BufferedReader(jsonReader);
            String line;

            while ((line = reader.readLine()) != null) {
                jsonResponse.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonResponse.toString();
    }
}
