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
    private static final String MOVIE_DETAIL_KEY = MovieDetailPagerAdapter.MOVIE_KEY;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        FilmData movie = getArguments().getParcelable(MOVIE_DETAIL_KEY);

        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        TextView movieTitle = view.findViewById(R.id.movie_title_text);
        movieTitle.setText(movie.getTitle());
        Log.i("FRAGMENT", "view movie title: " + movieTitle.getText().toString());

        ImageView posterView = view.findViewById(R.id.detail_poster_image);
        MovieApiWrapper.loadPosterIntoView(posterView, movie);

        TextView rating = view.findViewById(R.id.detail_rating_text);
        rating.setText(movie.getUserRating().toString());
        Log.i("FRAGMENT", "view movie rating: " + rating.getText().toString());

        TextView releaseDate = view.findViewById(R.id.detail_release_date);
        releaseDate.setText(movie.getReleaseYear());
        Log.i("FRAGMENT", "view movie release date: " + releaseDate.getText().toString());

        TextView plot = view.findViewById(R.id.plot_summary);
        plot.setText(movie.getPlot());
        Log.i("FRAGMENT", "view movie plot: " + plot.getText().toString());


        return view;
    }
    
    public static MovieDetailFragment newInstance(FilmData movie) {
        
        Bundle args = new Bundle();
        args.putParcelable(MOVIE_DETAIL_KEY, movie);
        
        MovieDetailFragment fragment = new MovieDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
