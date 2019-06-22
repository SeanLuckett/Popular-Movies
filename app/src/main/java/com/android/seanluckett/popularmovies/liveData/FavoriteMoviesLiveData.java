package com.android.seanluckett.popularmovies.liveData;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.android.seanluckett.popularmovies.database.FavoriteDao;
import com.android.seanluckett.popularmovies.database.FavoriteDatabase;
import com.android.seanluckett.popularmovies.models.Favorite;

import java.util.List;

public class FavoriteMoviesLiveData extends LiveData {
    private final FavoriteDao dao;

    public FavoriteMoviesLiveData(Context context) {
        dao = FavoriteDatabase.getInstance(context).getFavoriteDao();
        loadData();
    }

    @SuppressLint("StaticFieldLeak")
    private void loadData() {
        new AsyncTask<Void, Void, List<Favorite>>() {

            @Override
            protected List<Favorite> doInBackground(Void... voids) {
                return dao.loadFavorites();
            }

            @Override
            protected void onPostExecute(List<Favorite> favorites) {
                Log.i(Favorite.TAG, "Favorite list size is " + favorites.size());
                setValue(favorites);
            }
        }.execute();
    }
}
