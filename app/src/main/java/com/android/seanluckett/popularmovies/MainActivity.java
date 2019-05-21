package com.android.seanluckett.popularmovies;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.seanluckett.popularmovies.models.FilmData;
import com.android.seanluckett.popularmovies.utils.FakeMovieDbService;
import com.android.seanluckett.popularmovies.wrappers.MovieApiWrapper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView moviesRecyclerView;
    private MoviesAdapter moviesAdapter = new MoviesAdapter();

    // TODO Move this api key code to api interacter
    private static final String MOVIE_API_KEY = BuildConfig.MOVIE_API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        moviesRecyclerView = findViewById(R.id.recyclerview_movies);

        GridLayoutManager layoutManager = new GridLayoutManager(
            MainActivity.this,
            GridLayoutManager.DEFAULT_SPAN_COUNT,
            GridLayoutManager.VERTICAL,
            false
        );

        moviesRecyclerView.setLayoutManager(layoutManager);
        moviesRecyclerView.setHasFixedSize(true);
        moviesRecyclerView.setAdapter(moviesAdapter);

        loadMovieData();
    }

    public void loadMovieData() {
        new FetchPopularMoviesTask().execute(this);
    }

    public class FetchPopularMoviesTask extends AsyncTask<Context, Void, ArrayList<FilmData>> {

        @Override
        protected ArrayList<FilmData> doInBackground(Context... contexts) {
            MovieApiWrapper moviesWrapper = new MovieApiWrapper(new FakeMovieDbService(contexts[0]));
            return moviesWrapper.getMostPopular();
        }

        @Override
        protected void onPostExecute(ArrayList<FilmData> filmData) {
            if (filmData != null || !filmData.isEmpty()) {
                moviesAdapter.setMovieListData(filmData);
            } else {
                super.onPostExecute(filmData);
            }
        }
    }
}