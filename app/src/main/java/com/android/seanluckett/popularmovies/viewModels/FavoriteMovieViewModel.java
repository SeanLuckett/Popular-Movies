package com.android.seanluckett.popularmovies.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.seanluckett.popularmovies.liveData.FavoriteLiveData;
import com.android.seanluckett.popularmovies.models.Favorite;
import com.android.seanluckett.popularmovies.models.FilmData;

public class FavoriteMovieViewModel extends AndroidViewModel {
    private FavoriteLiveData favoriteData;

    public FavoriteMovieViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Favorite> getFavorite(FilmData movie) {
        if (favoriteData == null) {
            findOrCreateFavorite(movie);
        }

        return favoriteData;
    }

    public void toggleFavorite(FilmData movie) {
        favoriteData.toggleFavorite(movie);
    }

    private void findOrCreateFavorite(FilmData movie) {
        favoriteData = new FavoriteLiveData(getApplication(), movie);
    }
}
