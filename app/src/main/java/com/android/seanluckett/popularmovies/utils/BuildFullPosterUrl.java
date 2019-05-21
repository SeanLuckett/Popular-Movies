package com.android.seanluckett.popularmovies.utils;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

public class BuildFullPosterUrl {
    private final static String TAG = BuildFullPosterUrl.class.getSimpleName();

    public static final String MOVIE_DB_IMAGE_BASE = "https://image.tmdb.org/t/p/";
    public static final String DEFAULT_IMG_SIZE = "w185";

    public static URL execute(String filePath) {
        Uri builtImageUri = Uri
            .parse(MOVIE_DB_IMAGE_BASE).buildUpon()
            .appendPath(DEFAULT_IMG_SIZE)
            .appendEncodedPath(filePath)
            .build();

        URL fullImageUrl = null;
        try {
            fullImageUrl = new URL(builtImageUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return fullImageUrl;
    }
}
