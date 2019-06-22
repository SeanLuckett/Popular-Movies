package com.android.seanluckett.popularmovies.viewHolders;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.seanluckett.popularmovies.R;
import com.android.seanluckett.popularmovies.clickHandlers.FavoritesAdapterOnClickHandler;
import com.android.seanluckett.popularmovies.models.Favorite;

public class FavoritesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public final ImageView moviePosterImageView;
    private final FavoritesAdapterOnClickHandler clickHandler;
    private Favorite selectedMovie;

    public FavoritesAdapterViewHolder(View view, FavoritesAdapterOnClickHandler handler) {
        super(view);
        clickHandler = handler;
        moviePosterImageView = view.findViewById(R.id.movie_poster_image);
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        clickHandler.onMovieClicked(selectedMovie);
    }

    public void bindData(Favorite movie) {
        selectedMovie = movie;
    }

}