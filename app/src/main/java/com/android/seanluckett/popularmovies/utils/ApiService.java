package com.android.seanluckett.popularmovies.utils;

public interface ApiService {
    String getMostPopular();

    String getTopRated();

    String getTrailers(int id);
}
