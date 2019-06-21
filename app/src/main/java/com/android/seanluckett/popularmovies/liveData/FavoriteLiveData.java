package com.android.seanluckett.popularmovies.liveData;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.android.seanluckett.popularmovies.database.FavoriteDao;
import com.android.seanluckett.popularmovies.database.FavoriteDatabase;
import com.android.seanluckett.popularmovies.models.Favorite;
import com.android.seanluckett.popularmovies.models.FilmData;

public class FavoriteLiveData extends LiveData {
    public final static String TAG = FavoriteLiveData.class.getSimpleName();
    private final FavoriteDao dbDao;

    public FavoriteLiveData(Context context, FilmData movie) {
        this.dbDao = FavoriteDatabase.getInstance(context).getFavoriteDao();
        loadFavorite(movie);
    }

    @SuppressLint("StaticFieldLeak")
    public void toggleFavorite(final FilmData movie) {
        final Favorite nonPersistedFav = initializeNewFavorite(movie);

        new AsyncTask<Void, Void, Favorite>() {
            @Override
            protected Favorite doInBackground(Void... voids) {
                Favorite favorite = dbDao.findByMovieDbId(movie.getId());

                if (favorite != null) {
                    favorite.toggleFavorite();
                    dbDao.update(favorite);
                    return favorite;
                } else {
                    nonPersistedFav.toggleFavorite();
                    dbDao.insert(nonPersistedFav);
                    return nonPersistedFav;
                }
            }

            @Override
            protected void onPostExecute(Favorite favorite) {
                setValue(favorite);
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    private void loadFavorite(final FilmData movie) {
        final Favorite nonPersistedFav = initializeNewFavorite(movie);

        new AsyncTask<Void, Void, Favorite>() {

            @Override
            protected Favorite doInBackground(Void... voids) {
                return dbDao.findByMovieDbId(movie.getId());
            }

            @Override
            protected void onPostExecute(Favorite favorite) {
                setValue((favorite != null) ? favorite : nonPersistedFav);
            }


        }.execute();
    }

    private Favorite initializeNewFavorite(FilmData movie) {
        return new Favorite(
            movie.getId(),
            movie.getTitle(),
            movie.getPosterImagePath(),
            movie.getPlot(),
            movie.getUserRating(),
            movie.getReleaseYear()
        );
    }
}

