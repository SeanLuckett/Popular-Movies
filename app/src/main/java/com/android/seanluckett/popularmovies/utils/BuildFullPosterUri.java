package com.android.seanluckett.popularmovies.utils;

import android.net.Uri;

class BuildFullPosterUri {
    private final static String TAG = BuildFullPosterUri.class.getSimpleName();

    public static Uri execute(String filePath) {
        final String MOVIE_DB_IMAGE_BASE = "https://image.tmdb.org/t/p/";
        final String DEFAULT_IMG_SIZE = "w185";

        return Uri.parse(MOVIE_DB_IMAGE_BASE).buildUpon()
            .appendPath(DEFAULT_IMG_SIZE)
            .appendEncodedPath(filePath)
            .build();
    }
}
