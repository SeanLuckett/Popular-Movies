package com.android.seanluckett.popularmovies;

import android.app.Application;
import android.content.res.Resources;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.android.seanluckett.popularmovies.models.FilmData;
import com.android.seanluckett.popularmovies.utils.ApiService;
import com.android.seanluckett.popularmovies.utils.Configuration;

public class  MovieDetailPagerAdapter extends FragmentPagerAdapter {
    public final static String MOVIE_KEY = "movie";

    private final int TOTAL_PAGES = 3;
    private FilmData movie;
    private ApiService apiService;
    private Application app;

    public MovieDetailPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MovieDetailPagerAdapter(
        FragmentManager fm, FilmData selectedMovie, Application application
    ) {
        super(fm);
        apiService = Configuration.getApiServiceObject(application);
        movie = selectedMovie;
        app = application;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0: return MovieDetailFragment.newInstance(movie);
            case 1: return MovieTrailersFragment.newInstance(movie);
            case 2: return MovieReviewsFragment.newInstance(movie);
            default: return MovieDetailFragment.newInstance(movie);
        }
    }

    @Override
    public int getCount() {
        return TOTAL_PAGES;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Resources resources = app.getResources();

        switch (position) {
            case 0: return resources.getString(R.string.movie_detail_tab_label);
            case 1: return resources.getString(R.string.movie_trailer_tab_label);
            case 2: return resources.getString(R.string.movie_review_tab_label);
            default: return resources.getString(R.string.movie_tab_label_overflow);
        }
    }
}
