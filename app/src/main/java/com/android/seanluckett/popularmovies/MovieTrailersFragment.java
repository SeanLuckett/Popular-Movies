package com.android.seanluckett.popularmovies;


import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.seanluckett.popularmovies.models.FilmData;
import com.android.seanluckett.popularmovies.models.TrailerData;
import com.android.seanluckett.popularmovies.utils.ApiService;
import com.android.seanluckett.popularmovies.utils.Configuration;
import com.android.seanluckett.popularmovies.utils.MovieDbService;
import com.android.seanluckett.popularmovies.wrappers.MovieApiWrapper;

import java.util.ArrayList;

public class MovieTrailersFragment extends Fragment {
    private RecyclerView movieTrailersRecyclerView;
    private MovieTrailersAdapter trailersAdapter;
    private MovieApiWrapper movieApiWrapper;


    public MovieTrailersFragment() {
        // Required empty public constructor
    }

    public MovieTrailersFragment(ApiService service) {
        movieApiWrapper = new MovieApiWrapper(service);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FilmData movie = getArguments().getParcelable(MovieDetailPagerAdapter.MOVIE_KEY);

        View view = inflater.inflate(R.layout.fragment_movie_trailers, container, false);

        movieTrailersRecyclerView = view.findViewById(R.id.rv_trailers);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
            container.getContext(), RecyclerView.VERTICAL, false
        );
        trailersAdapter = new MovieTrailersAdapter();

        movieTrailersRecyclerView.setLayoutManager(layoutManager);
        movieTrailersRecyclerView.setAdapter(trailersAdapter);
        movieTrailersRecyclerView.setHasFixedSize(true);

        loadTrailer(movie.getId());

        return view;
    }

    public static MovieTrailersFragment newInstance(FilmData movie, ApiService service) {

        Bundle args = new Bundle();
        args.putParcelable(MovieDetailPagerAdapter.MOVIE_KEY, movie);

        MovieTrailersFragment fragment = new MovieTrailersFragment(service);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("StaticFieldLeak")
    private void loadTrailer(final int movieId) {
        new AsyncTask<Void, Void, ArrayList<TrailerData>>() {

            @Override
            protected ArrayList<TrailerData> doInBackground(Void... voids) {
                return movieApiWrapper.getTrailers(movieId);
            }

            @Override
            protected void onPostExecute(ArrayList<TrailerData> trailerData) {
                trailersAdapter.setTrailerListData(trailerData);
            }
        }.execute();
    }
}
