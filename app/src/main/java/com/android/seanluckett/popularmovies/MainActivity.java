package com.android.seanluckett.popularmovies;

import android.content.Intent;

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

import com.android.seanluckett.popularmovies.clickHandlers.FavoritesAdapterOnClickHandler;
import com.android.seanluckett.popularmovies.clickHandlers.MoviesAdapterOnClickHandler;
import com.android.seanluckett.popularmovies.models.Favorite;
import com.android.seanluckett.popularmovies.recyclerViewAdapters.FavoritesAdapter;
import com.android.seanluckett.popularmovies.recyclerViewAdapters.MoviesAdapter;
import com.android.seanluckett.popularmovies.models.FilmData;
import com.android.seanluckett.popularmovies.utils.ConvertToFilmData;
import com.android.seanluckett.popularmovies.viewModels.FavoritesListViewModel;
import com.android.seanluckett.popularmovies.viewModels.PopularMoviesViewModel;
import com.android.seanluckett.popularmovies.viewModels.TopMoviesViewModel;

import java.util.ArrayList;
import java.util.List;

import icepick.Icepick;
import icepick.State;

public class MainActivity extends AppCompatActivity
    implements MoviesAdapterOnClickHandler, FavoritesAdapterOnClickHandler {
    @State
    String movieListType;
    private final String MOVIE_LIST_STATE_POPULAR = "popular";
    private final String MOVIE_LIST_STATE_TOP = "top_rated";
    private final String MOVIE_LIST_STATE_FAVORITES = "favorites";

    private static final String MOST_POPULAR_TITLE = "Popular Movies";
    private static final String TOP_RATED_TITLE = "Top Rated Movies";
    private static final String FAVORITES_TITLE = "Favorite Movies";

    private RecyclerView moviesRecyclerView;
    private MoviesAdapter moviesAdapter;
    private FavoritesAdapter favoritesAdapter;
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
            numberOfColumns(),
            RecyclerView.VERTICAL,
            false
        );


        moviesRecyclerView.setLayoutManager(layoutManager);
        moviesRecyclerView.setHasFixedSize(true);

        moviesAdapter = new MoviesAdapter(this);
        favoritesAdapter = new FavoritesAdapter(this);

        loadingIndicator = findViewById(R.id.loading_indicator);
        showProgressLoader();

        Icepick.restoreInstanceState(this, savedInstanceState);
        loadMovieData();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public void onMovieClicked(FilmData movie) {
        Intent startMovieDetailIntent = new Intent(this, MovieDetailActivity.class);

        startMovieDetailIntent.putExtra(FilmData.TAG, movie);
        startActivity(startMovieDetailIntent);
    }


    @Override
    public void onFavoriteClicked(Favorite favorite) {
        Intent startMovieDetailIntent = new Intent(this, MovieDetailActivity.class);
        FilmData convertedMovie = ConvertToFilmData.execute(favorite);

        startMovieDetailIntent.putExtra(FilmData.TAG, convertedMovie);
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
        showProgressLoader();

        switch (item.getItemId()) {
            case R.id.sort_most_popular:
                registerMoviesAdapter();
                sortMoviesMostPopular();
                getSupportActionBar().setTitle(MOST_POPULAR_TITLE);
                return true;

            case R.id.sort_top_rated:
                registerMoviesAdapter();
                sortMoviesTopRated();
                getSupportActionBar().setTitle(TOP_RATED_TITLE);
                return true;

            case R.id.sort_favorites:
                registerFavoritesAdapter();
                sortMoviesFavorites();
                getSupportActionBar().setTitle(FAVORITES_TITLE);
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
        if (movieListType != null) {
            switch (movieListType) {
                case MOVIE_LIST_STATE_POPULAR:
                    registerMoviesAdapter();
                    sortMoviesMostPopular();
                    return;
                case MOVIE_LIST_STATE_TOP:
                    registerMoviesAdapter();
                    sortMoviesTopRated();
                    return;
                case MOVIE_LIST_STATE_FAVORITES:
                    registerFavoritesAdapter();
                    sortMoviesFavorites();
                    return;
            }
        } else {
            registerMoviesAdapter();
            sortMoviesMostPopular();
        }
    }

    private void registerMoviesAdapter() {
        moviesRecyclerView.setAdapter(moviesAdapter);
    }

    private void registerFavoritesAdapter() {
        moviesRecyclerView.setAdapter(favoritesAdapter);
    }

    private void sortMoviesMostPopular() {
        movieListType = MOVIE_LIST_STATE_POPULAR;

        PopularMoviesViewModel movieModel =
            ViewModelProviders.of(this).get(PopularMoviesViewModel.class);

        movieModel.getMovies().observe(this, new Observer<ArrayList<FilmData>>() {
            @Override
            public void onChanged(ArrayList<FilmData> movies) {
                loadingIndicator.setVisibility(View.INVISIBLE);
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
        movieListType = MOVIE_LIST_STATE_TOP;

        TopMoviesViewModel movieModel =
            ViewModelProviders.of(this).get(TopMoviesViewModel.class);

        movieModel.getMovies().observe(this, new Observer<ArrayList<FilmData>>() {
            @Override
            public void onChanged(ArrayList<FilmData> movies) {
                loadingIndicator.setVisibility(View.INVISIBLE);
                if (movies != null && !movies.isEmpty()) {
                    showMoviesView();
                    moviesAdapter.setMovieListData(movies);
                } else {
                    showErrorMessage();
                }
            }
        });
    }

    private void sortMoviesFavorites() {
        movieListType = MOVIE_LIST_STATE_FAVORITES;

        FavoritesListViewModel favModel =
            ViewModelProviders.of(this).get(FavoritesListViewModel.class);

        favModel.getFavorites().observe(this, new Observer<List<Favorite>>() {

            @Override
            public void onChanged(List<Favorite> favorites) {
                loadingIndicator.setVisibility(View.INVISIBLE);
                if (favorites != null && !favorites.isEmpty()) {
                    showMoviesView();
                    favoritesAdapter.setFavoriteListData(favorites);
                } else {
                    showNoFavoritesMessage();
                }
            }
        });
    }

    private void showMoviesView() {
        errorMessageView.setVisibility(View.INVISIBLE);
        moviesRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showProgressLoader() {
        moviesRecyclerView.setVisibility(View.INVISIBLE);
        loadingIndicator.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        moviesRecyclerView.setVisibility(View.INVISIBLE);
        errorMessageView.setVisibility(View.VISIBLE);
    }

    private void showNoFavoritesMessage() {
        moviesRecyclerView.setVisibility(View.INVISIBLE);
        // TODO design some message. For now, leave blank
    }

}