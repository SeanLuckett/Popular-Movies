package com.android.seanluckett.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.seanluckett.popularmovies.models.FilmData;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder> {

    private ArrayList<FilmData> movieListData;

    @NonNull
    @Override
    public MoviesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_list_item, viewGroup, false);
        return new MoviesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapterViewHolder moviesAdapterViewHolder, int i) {
        FilmData movie = movieListData.get(i);
        // TODO bind movie poster to view (picasso)
    }

    @Override
    public int getItemCount() {
        return (movieListData != null) ? movieListData.size() : 0;
    }

    public void setMovieListData(ArrayList<FilmData> movies) {
        movieListData = movies;
        notifyDataSetChanged();
    }

    class MoviesAdapterViewHolder extends RecyclerView.ViewHolder {

        MoviesAdapterViewHolder(View view) {
            super(view);
        }
    }
}
