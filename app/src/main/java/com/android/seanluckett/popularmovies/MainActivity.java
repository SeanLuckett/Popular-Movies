package com.android.seanluckett.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.seanluckett.popularmovies.models.FilmData;
import com.android.seanluckett.popularmovies.utils.MovieDbService;
import com.android.seanluckett.popularmovies.wrappers.MovieApiWrapper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.MoviesAdapterOnClickHandler {
    private static final String MOST_POPULAR_TITLE = "Popular Movies";
    private static final String TOP_RATED_TITLE = "Top Rated Movies";

    private RecyclerView moviesRecyclerView;
    private MoviesAdapter moviesAdapter;
    private ProgressBar loadingIndicator;
    private TextView errorMessageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        moviesRecyclerView = findViewById(R.id.recyclerview_movies);
        errorMessageView = findViewById(R.id.error_message_display);

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

        loadingIndicator = findViewById(R.id.loading_indicator);

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
                getSupportActionBar().setTitle(MOST_POPULAR_TITLE);
                return true;
            case R.id.sort_top_rated:
                sortMoviesTopRated();
                getSupportActionBar().setTitle(TOP_RATED_TITLE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void loadMovieData() {
        new FetchPopularMoviesTask().execute();
    }

    private void sortMoviesMostPopular() {
        loadMovieData();
    }

    private void sortMoviesTopRated() {
        new FetchTopRatedMoviesTask().execute();
    }

    private void showMoviesView() {
        errorMessageView.setVisibility(View.INVISIBLE);
        moviesRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        moviesRecyclerView.setVisibility(View.INVISIBLE);
        errorMessageView.setVisibility(View.VISIBLE);
    }

    class FetchTopRatedMoviesTask extends AsyncTask<Void, Void, ArrayList<FilmData>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            moviesRecyclerView.setVisibility(View.INVISIBLE);
            loadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<FilmData> doInBackground(Void... voids) {
            MovieApiWrapper moviesWrapper = new MovieApiWrapper(new MovieDbService());
            return moviesWrapper.getTopRated();
        }

        @Override
        protected void onPostExecute(ArrayList<FilmData> filmData) {
            loadingIndicator.setVisibility(View.INVISIBLE);

            if (filmData != null && !filmData.isEmpty()) {
                showMoviesView();
                moviesAdapter.setMovieListData(filmData);
            } else {
                showErrorMessage();
            }
        }
    }

    class FetchPopularMoviesTask extends AsyncTask<Void, Void, ArrayList<FilmData>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            moviesRecyclerView.setVisibility(View.INVISIBLE);
            loadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<FilmData> doInBackground(Void... voids) {
            MovieApiWrapper moviesWrapper = new MovieApiWrapper(new MovieDbService());
            return moviesWrapper.getMostPopular();
        }

        @Override
        protected void onPostExecute(ArrayList<FilmData> filmData) {
            loadingIndicator.setVisibility(View.INVISIBLE);

            if (filmData != null && !filmData.isEmpty()) {
                showMoviesView();
                moviesAdapter.setMovieListData(filmData);
            } else {
                showErrorMessage();
            }
        }
    }
}