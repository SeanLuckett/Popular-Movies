package com.android.seanluckett.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.seanluckett.popularmovies.models.FilmData;
import com.android.seanluckett.popularmovies.wrappers.MovieApiWrapper;

import java.util.ArrayList;

class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapterViewHolder> {

    private ArrayList<FilmData> movieListData;
    private final MoviesAdapterOnClickHandler clickHandler;

    MoviesAdapter(MoviesAdapterOnClickHandler handler) { clickHandler = handler; }

    @NonNull
    @Override
    public MoviesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_list_item, viewGroup, false);
        return new MoviesAdapterViewHolder(view, movieListData, clickHandler);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapterViewHolder moviesAdapterViewHolder, int i) {
        FilmData movie = movieListData.get(i);
        MovieApiWrapper.loadPosterIntoView(
            moviesAdapterViewHolder.moviePosterImageView,
            movie);
    }

    @Override
    public int getItemCount() {
        return (movieListData != null) ? movieListData.size() : 0;
    }

    void setMovieListData(ArrayList<FilmData> movies) {
        movieListData = movies;
        notifyDataSetChanged();
    }


}
