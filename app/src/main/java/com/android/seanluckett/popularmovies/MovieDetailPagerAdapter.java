package com.android.seanluckett.popularmovies;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.android.seanluckett.popularmovies.models.FilmData;

public class MovieDetailPagerAdapter extends FragmentPagerAdapter {
    public final static String MOVIE_KEY = "movie";

    private final int TOTAL_PAGES = 3;
    private FilmData movie;

    public MovieDetailPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MovieDetailPagerAdapter(FragmentManager fm, FilmData selectedMovie) {
        super(fm);
        movie = selectedMovie;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
            case 1:
            case 2:  return MovieDetailFragment.newInstance(movie);
            default: return MovieDetailFragment.newInstance(movie);
        }
    }

    @Override
    public int getCount() {
        return TOTAL_PAGES;
    }
}
