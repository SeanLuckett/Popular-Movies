package com.android.seanluckett.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.seanluckett.popularmovies.models.FilmData;
import com.android.seanluckett.popularmovies.utils.ApiService;
import com.android.seanluckett.popularmovies.utils.Configuration;
import com.android.seanluckett.popularmovies.viewModels.PopularMoviesViewModel;
import com.android.seanluckett.popularmovies.wrappers.MovieApiWrapper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MoviesAdapterOnClickHandler {
    private static final String MOST_POPULAR_TITLE = "Popular Movies";
    private static final String TOP_RATED_TITLE = "Top Rated Movies";
    private final ApiService MOVIE_DATA_SERVICE = Configuration.getApiServiceObject(this.getApplication());

    private RecyclerView moviesRecyclerView;
    private MoviesAdapter moviesAdapter;
    private ProgressBar loadingIndicator;
    private TextView errorMessageView;
    private MovieApiWrapper moviesWrapper = new MovieApiWrapper(MOVIE_DATA_SERVICE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        moviesRecyclerView = findViewById(R.id.recyclerview_movies);
        errorMessageView = findViewById(R.id.error_message_display);

        GridLayoutManager layoutManager = new GridLayoutManager(
            MainActivity.this,
            numberOfColumns(),
            RecyclerView.VERTICAL,
            false
        );


        moviesRecyclerView.setLayoutManager(layoutManager);
        moviesRecyclerView.setHasFixedSize(true);

        moviesAdapter = new MoviesAdapter(this);
        moviesRecyclerView.setAdapter(moviesAdapter);

        loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.VISIBLE);

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

    private int numberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int widthDivider = 400;
        int numColumns = displayMetrics.widthPixels / widthDivider;
        if (numColumns < 2) return 2; //to ensure grid behavior

        return numColumns;
    }

    private void loadMovieData() {
        sortMoviesMostPopular();
    }

    private void sortMoviesMostPopular() {
        PopularMoviesViewModel movieModel =
            ViewModelProviders.of(this).get(PopularMoviesViewModel.class);

        movieModel.getMovies().observe(this, new Observer<ArrayList<FilmData>>() {
            @Override
            public void onChanged(ArrayList<FilmData> movies) {
                loadingIndicator.setVisibility(View.INVISIBLE);
                Log.i(FilmData.TAG, "Observe#onChange() called!");
                if (movies != null && !movies.isEmpty()) {
                    showMoviesView();
                    moviesAdapter.setMovieListData(movies);
                } else {
                    showErrorMessage();
                }
            }
        });
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
}