package com.android.seanluckett.popularmovies.viewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.seanluckett.popularmovies.liveData.FavoriteMoviesLiveData;
import com.android.seanluckett.popularmovies.models.Favorite;

import java.util.List;

public class FavoritesListViewModel extends AndroidViewModel {
    private final FavoriteMoviesLiveData favoriteList;

    public FavoritesListViewModel(@NonNull Application application) {
        super(application);
        favoriteList = new FavoriteMoviesLiveData(application);
    }

    public LiveData<List<Favorite>> getFavorites() {
        Log.i(Favorite.TAG, "Loading favorites...");
        return favoriteList;
    }
}
