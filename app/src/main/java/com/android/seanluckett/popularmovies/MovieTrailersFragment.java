package com.android.seanluckett.popularmovies;


import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.seanluckett.popularmovies.clickHandlers.MovieTrailersOnClickHandler;
import com.android.seanluckett.popularmovies.models.FilmData;
import com.android.seanluckett.popularmovies.models.TrailerData;
import com.android.seanluckett.popularmovies.recyclerViewAdapters.MovieTrailersAdapter;
import com.android.seanluckett.popularmovies.viewModels.MovieTrailersViewModel;

import java.util.ArrayList;

public class MovieTrailersFragment extends Fragment implements MovieTrailersOnClickHandler {
    private MovieTrailersAdapter trailersAdapter;
    private FilmData movie;


    public MovieTrailersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        movie = getArguments().getParcelable(MovieDetailPagerAdapter.MOVIE_KEY);
        trailersAdapter = new MovieTrailersAdapter(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie_trailers, container, false);

        RecyclerView movieTrailersRecyclerView = view.findViewById(R.id.rv_trailers);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
            container.getContext(), RecyclerView.VERTICAL, false
        );

        movieTrailersRecyclerView.setLayoutManager(layoutManager);
        movieTrailersRecyclerView.setAdapter(trailersAdapter);
        movieTrailersRecyclerView.setHasFixedSize(true);

        loadTrailers();

        return view;
    }

    public static MovieTrailersFragment newInstance(FilmData movie) {

        Bundle args = new Bundle();
        args.putParcelable(MovieDetailPagerAdapter.MOVIE_KEY, movie);

        MovieTrailersFragment fragment = new MovieTrailersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onTrailerClicked(TrailerData trailer) {
        String id = trailer.getKey();
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
            Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void loadTrailers() {
        MovieTrailersViewModel trailerModel =
            ViewModelProviders.of(this).get(MovieTrailersViewModel.class);

        trailerModel
            .getTrailers(movie.getId())
            .observe(this, new Observer<ArrayList<TrailerData>>() {

                @Override
                public void onChanged(ArrayList<TrailerData> trailers) {
                    if (trailers != null && !trailers.isEmpty()) {
                        trailersAdapter.setTrailerListData(trailers);
                    }
                }
            });
    }
}
