package com.android.seanluckett.popularmovies.recyclerViewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.seanluckett.popularmovies.viewHolders.MovieTrailerViewHolder;
import com.android.seanluckett.popularmovies.clickHandlers.MovieTrailersOnClickHandler;
import com.android.seanluckett.popularmovies.R;
import com.android.seanluckett.popularmovies.models.TrailerData;

import java.util.ArrayList;

public class MovieTrailersAdapter extends RecyclerView.Adapter<MovieTrailerViewHolder> {

    private ArrayList<TrailerData> trailerListData;
    private final MovieTrailersOnClickHandler clickHandler;

    public MovieTrailersAdapter(MovieTrailersOnClickHandler handler) { clickHandler = handler; }

    @NonNull
    @Override
    public MovieTrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_trailers_item, parent, false);

        return new MovieTrailerViewHolder(view, clickHandler);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieTrailerViewHolder holder, int position) {
        TrailerData trailer = trailerListData.get(position);
        holder.bindData(trailer);

        holder.trailerName.setText(trailer.getName());
        holder.trailerType.setText(trailer.getType());
        holder.trailerSize.setText(trailer.getSize());
    }

    @Override
    public int getItemCount() {
        return (trailerListData != null) ? trailerListData.size() : 0;
    }

    public void setTrailerListData(ArrayList<TrailerData> trailers) {
        trailerListData = trailers;
        notifyDataSetChanged();
    }
}