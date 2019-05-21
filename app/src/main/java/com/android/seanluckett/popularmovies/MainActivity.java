package com.android.seanluckett.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.seanluckett.popularmovies.models.FilmData;
import com.android.seanluckett.popularmovies.utils.FakeMovieDbService;
import com.android.seanluckett.popularmovies.wrappers.MovieApiWrapper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // TODO Move this api key code to api interacter
    private static final String MOVIE_API_KEY = BuildConfig.MOVIE_API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieApiWrapper moviesWrapper = new MovieApiWrapper(new FakeMovieDbService(this));
        ArrayList<FilmData> popularMovies = moviesWrapper.getMostPopular();
    }
}