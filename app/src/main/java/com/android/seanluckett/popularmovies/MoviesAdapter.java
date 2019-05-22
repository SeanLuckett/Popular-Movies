package com.android.seanluckett.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.seanluckett.popularmovies.models.FilmData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder> {

    private ArrayList<FilmData> movieListData;
    private final MoviesAdapterOnClickHandler clickHandler;

    public interface MoviesAdapterOnClickHandler {
        public void onMovieClicked(FilmData movie);
    }

    public MoviesAdapter(MoviesAdapterOnClickHandler handler) { clickHandler = handler; }

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
        // TODO add placeholder and possibly error images
        Picasso.get()
            .load(movie.getPosterImagePath())
            .into(moviesAdapterViewHolder.moviePosterImageView);
    }

    @Override
    public int getItemCount() {
        return (movieListData != null) ? movieListData.size() : 0;
    }

    public void setMovieListData(ArrayList<FilmData> movies) {
        movieListData = movies;
        notifyDataSetChanged();
    }

    public class MoviesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final ImageView moviePosterImageView;

        MoviesAdapterViewHolder(View view) {
            super(view);
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
}
