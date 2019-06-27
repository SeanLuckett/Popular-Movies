package com.android.seanluckett.popularmovies;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.seanluckett.popularmovies.models.Favorite;
import com.android.seanluckett.popularmovies.models.FilmData;
import com.android.seanluckett.popularmovies.viewModels.FavoriteMovieViewModel;
import com.android.seanluckett.popularmovies.wrappers.MovieApiWrapper;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment {
    private FilmData selectedMovie;
    private FavoriteMovieViewModel favViewModel;
    private Button favButton;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        selectedMovie = getArguments().getParcelable(MovieDetailPagerAdapter.MOVIE_KEY);

        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        ImageView posterView = view.findViewById(R.id.detail_poster_image);
        MovieApiWrapper.loadPosterIntoView(posterView, selectedMovie);

        TextView rating = view.findViewById(R.id.detail_rating_text);
        rating.setText(selectedMovie.getUserRating().toString());

        TextView releaseDate = view.findViewById(R.id.detail_release_date);
        releaseDate.setText(selectedMovie.getReleaseDate());

        TextView plot = view.findViewById(R.id.plot_summary);
        plot.setText(selectedMovie.getPlot());

        initializeFavoriteButton(view);

        return view;
    }

    public static MovieDetailFragment newInstance(FilmData movie) {

        Bundle args = new Bundle();
        args.putParcelable(MovieDetailPagerAdapter.MOVIE_KEY, movie);

        MovieDetailFragment fragment = new MovieDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initializeFavoriteButton(View view) {
        favButton = view.findViewById(R.id.make_favorite_button);
        favViewModel = ViewModelProviders.of(this).get(FavoriteMovieViewModel.class);

        favViewModel.getFavorite(selectedMovie).observe(this, new Observer<Favorite>() {
            @Override
            public void onChanged(Favorite favorite) {
                String buttonLabel;

                if (favorite.isFavorite()) {
                    buttonLabel = getString(R.string.unfavorite_label);
                } else {
                    buttonLabel = getString(R.string.make_favorite_label);
                }
                favButton.setText(buttonLabel);
            }
        });

        favButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onFavoriteButtonClicked();
            }
        });
    }

    private void onFavoriteButtonClicked() {
        favViewModel.toggleFavorite(selectedMovie);
    }

}
