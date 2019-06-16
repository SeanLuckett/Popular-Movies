package com.android.seanluckett.popularmovies;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.seanluckett.popularmovies.models.FilmData;
import com.android.seanluckett.popularmovies.wrappers.MovieApiWrapper;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment {
    public MovieDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FilmData movie = getArguments().getParcelable(MovieDetailPagerAdapter.MOVIE_KEY);

        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        ImageView posterView = view.findViewById(R.id.detail_poster_image);
        MovieApiWrapper.loadPosterIntoView(posterView, movie);

        TextView rating = view.findViewById(R.id.detail_rating_text);
        rating.setText(movie.getUserRating().toString());

        TextView releaseDate = view.findViewById(R.id.detail_release_date);
        releaseDate.setText(movie.getReleaseYear());

        TextView plot = view.findViewById(R.id.plot_summary);
        plot.setText(movie.getPlot());

        return view;
    }
    
    public static MovieDetailFragment newInstance(FilmData movie) {
        
        Bundle args = new Bundle();
        args.putParcelable(MovieDetailPagerAdapter.MOVIE_KEY, movie);
        
        MovieDetailFragment fragment = new MovieDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
