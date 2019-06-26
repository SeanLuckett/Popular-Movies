package com.android.seanluckett.popularmovies.utils;

import android.app.Application;

import com.android.seanluckett.popularmovies.BuildConfig;

public class Configuration {
    public static ApiService getApiServiceObject(Application application) {
        if (BuildConfig.MOVIE_API_SERVICE.equals("LIVE")) {
            return new MovieDbService();
        } else {
            return new FakeMovieDbService(application.getApplicationContext());
        }
    }
}
