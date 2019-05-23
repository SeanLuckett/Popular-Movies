package com.android.seanluckett.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.seanluckett.popularmovies.models.FilmData;
import com.android.seanluckett.popularmovies.utils.FakeMovieDbService;
import com.android.seanluckett.popularmovies.utils.MovieDbService;
import com.android.seanluckett.popularmovies.wrappers.MovieApiWrapper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.MoviesAdapterOnClickHandler {
    private RecyclerView moviesRecyclerView;
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        moviesRecyclerView = findViewById(R.id.recyclerview_movies);

        GridLayoutManager layoutManager = new GridLayoutManager(
            MainActivity.this,
            2,
            GridLayoutManager.VERTICAL,
            false
        );


        moviesRecyclerView.setLayoutManager(layoutManager);
        moviesRecyclerView.setHasFixedSize(true);

        moviesAdapter = new MoviesAdapter(this);
        moviesRecyclerView.setAdapter(moviesAdapter);

        // TODO add a progress bar and corresponding async task methods
        loadMovieData();
    }

    @Override
    public void onMovieClicked(FilmData movie) {
        Intent startMovieDetailIntent = new Intent(this, MovieDetailActivity.class);

        startMovieDetailIntent.putExtra(FilmData.TAG, movie);
        startActivity(startMovieDetailIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movie_sort_filters, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_most_popular:
                sortMoviesMostPopular();
                return true;
            case R.id.sort_top_rated:
                sortMoviesTopRated();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void loadMovieData() {
        new FetchPopularMoviesTask().execute();
    }

    private void sortMoviesMostPopular() {
        loadMovieData();
    }

    private void sortMoviesTopRated() {
        new FetchTopRatedMoviesTask().execute();
    }

    public class FetchTopRatedMoviesTask extends AsyncTask<Void, Void, ArrayList<FilmData>> {
        @Override
        protected ArrayList<FilmData> doInBackground(Void... voids) {
            MovieApiWrapper moviesWrapper = new MovieApiWrapper(new MovieDbService());
            ArrayList<FilmData> topRated = moviesWrapper.getTopRated();
            Log.i(MovieDbService.TAG, "In top rated task, the response list is size: " + topRated.size());
            return topRated;
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

    public class FetchPopularMoviesTask extends AsyncTask<Void, Void, ArrayList<FilmData>> {
        private final String TAG = FetchPopularMoviesTask.class.getSimpleName();

        @Override
        protected ArrayList<FilmData> doInBackground(Void... voids) {
            MovieApiWrapper moviesWrapper = new MovieApiWrapper(new MovieDbService());
            ArrayList<FilmData> mostPopular = moviesWrapper.getMostPopular();
            Log.i(MovieDbService.TAG, "In most popular task, the response list is size: " + mostPopular.size());
            return mostPopular;
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