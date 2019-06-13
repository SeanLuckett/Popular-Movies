package com.android.seanluckett.popularmovies;

import android.app.Application;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.android.seanluckett.popularmovies.models.FilmData;
import com.android.seanluckett.popularmovies.utils.ApiService;
import com.android.seanluckett.popularmovies.utils.Configuration;

public class MovieDetailPagerAdapter extends FragmentPagerAdapter {
    public final static String MOVIE_KEY = "movie";

    private final int TOTAL_PAGES = 3;
    private FilmData movie;
    private ApiService apiService;

    public MovieDetailPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MovieDetailPagerAdapter(
        FragmentManager fm, FilmData selectedMovie, Application application
    ) {
        super(fm);
        apiService = Configuration.getApiServiceObject(application);
        movie = selectedMovie;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0: return MovieDetailFragment.newInstance(movie);
            case 1: return MovieTrailersFragment.newInstance(movie, apiService);
            case 2:
            default: return MovieDetailFragment.newInstance(movie);
        }
    }

    @Override
    public int getCount() {
        return TOTAL_PAGES;
    }
}
