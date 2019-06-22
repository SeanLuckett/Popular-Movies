package com.android.seanluckett.popularmovies.recyclerViewAdapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.seanluckett.popularmovies.clickHandlers.MoviesAdapterOnClickHandler;
import com.android.seanluckett.popularmovies.viewHolders.MoviesAdapterViewHolder;
import com.android.seanluckett.popularmovies.R;
import com.android.seanluckett.popularmovies.models.FilmData;
import com.android.seanluckett.popularmovies.wrappers.MovieApiWrapper;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapterViewHolder> {

    private ArrayList<FilmData> movieListData;
    private final MoviesAdapterOnClickHandler clickHandler;

    public MoviesAdapter(MoviesAdapterOnClickHandler handler) { clickHandler = handler; }

    @NonNull
    @Override
    public MoviesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_list_item, viewGroup, false);

        return new MoviesAdapterViewHolder(view, clickHandler);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapterViewHolder moviesAdapterViewHolder, int i) {
        FilmData movie = movieListData.get(i);
        moviesAdapterViewHolder.bindData(movie);

        MovieApiWrapper.loadPosterIntoView(
            moviesAdapterViewHolder.moviePosterImageView,
            movie);
    }

    @Override
    public int getItemCount() {
        return (movieListData != null) ? movieListData.size() : 0;
    }

    public void setMovieListData(ArrayList<FilmData> movies) {
        movieListData = movies;
        notifyDataSetChanged();
    }


}
