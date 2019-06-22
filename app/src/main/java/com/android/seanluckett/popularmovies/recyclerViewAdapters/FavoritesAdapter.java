package com.android.seanluckett.popularmovies.recyclerViewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.seanluckett.popularmovies.R;
import com.android.seanluckett.popularmovies.clickHandlers.FavoritesAdapterOnClickHandler;
import com.android.seanluckett.popularmovies.clickHandlers.MoviesAdapterOnClickHandler;
import com.android.seanluckett.popularmovies.models.Favorite;
import com.android.seanluckett.popularmovies.models.FilmData;
import com.android.seanluckett.popularmovies.viewHolders.FavoritesAdapterViewHolder;
import com.android.seanluckett.popularmovies.viewHolders.MoviesAdapterViewHolder;
import com.android.seanluckett.popularmovies.wrappers.MovieApiWrapper;

import java.util.ArrayList;
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapterViewHolder> {

    private List<Favorite> favoriteListData;
    private final FavoritesAdapterOnClickHandler clickHandler;

    public FavoritesAdapter(FavoritesAdapterOnClickHandler handler) { clickHandler = handler; }

    @NonNull
    @Override
    public FavoritesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_list_item, viewGroup, false);

        return new FavoritesAdapterViewHolder(view, clickHandler);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesAdapterViewHolder moviesAdapterViewHolder, int i) {
        Favorite favorite = favoriteListData.get(i);
        moviesAdapterViewHolder.bindData(favorite);

        MovieApiWrapper.loadPosterIntoView(
            moviesAdapterViewHolder.moviePosterImageView,
            favorite);
    }

    @Override
    public int getItemCount() {
        return (favoriteListData != null) ? favoriteListData.size() : 0;
    }

    public void setFavoriteListData(List<Favorite> movies) {
        favoriteListData = movies;
        notifyDataSetChanged();
    }


}
