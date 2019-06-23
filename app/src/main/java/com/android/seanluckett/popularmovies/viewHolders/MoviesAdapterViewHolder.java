package com.android.seanluckett.popularmovies.viewHolders;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.android.seanluckett.popularmovies.clickHandlers.MoviesAdapterOnClickHandler;
import com.android.seanluckett.popularmovies.R;
import com.android.seanluckett.popularmovies.models.FilmData;

public class MoviesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public final ImageView moviePosterImageView;
    private final MoviesAdapterOnClickHandler clickHandler;
    private FilmData selectedMovie;

    public MoviesAdapterViewHolder(View view, MoviesAdapterOnClickHandler handler) {
        super(view);
        clickHandler = handler;
        moviePosterImageView = view.findViewById(R.id.movie_poster_image);
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        clickHandler.onMovieClicked(selectedMovie);
    }

    public void bindData(FilmData movie) {
        selectedMovie = movie;
    }

}