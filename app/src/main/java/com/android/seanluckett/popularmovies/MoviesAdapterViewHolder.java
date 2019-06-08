package com.android.seanluckett.popularmovies;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.android.seanluckett.popularmovies.models.FilmData;

import java.util.ArrayList;

public class MoviesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    final ImageView moviePosterImageView;
    private final MoviesAdapterOnClickHandler clickHandler;
    private final ArrayList<FilmData> movieListData;

    MoviesAdapterViewHolder(
        View view,
        ArrayList<FilmData> movieList,
        MoviesAdapterOnClickHandler handler
    ) {

        super(view);
        clickHandler = handler;
        movieListData = movieList;
        moviePosterImageView = view.findViewById(R.id.movie_poster_image);
        view.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int adapterPosition = getAdapterPosition();
        FilmData selectedMovie = movieListData.get(adapterPosition);
        clickHandler.onMovieClicked(selectedMovie);
    }
}