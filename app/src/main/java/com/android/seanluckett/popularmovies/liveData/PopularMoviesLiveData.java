package com.android.seanluckett.popularmovies.liveData;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.android.seanluckett.popularmovies.models.FilmData;
import com.android.seanluckett.popularmovies.wrappers.MovieApiWrapper;

import java.util.ArrayList;

public class PopularMoviesLiveData extends LiveData<ArrayList<FilmData>> {
    private final MovieApiWrapper movieApiWrapper;

    public PopularMoviesLiveData(MovieApiWrapper wrapper) {
        movieApiWrapper = wrapper;
        loadData();
    }

    @SuppressLint("StaticFieldLeak")
    private void loadData() {
        new AsyncTask<Void, Void, ArrayList<FilmData>>() {

            @Override
            protected ArrayList<FilmData> doInBackground(Void... voids) {
                return movieApiWrapper.getMostPopular();
            }

            @Override
            protected void onPostExecute(ArrayList<FilmData> filmData) {
                setValue(filmData);
            }
        }.execute();
    }

}
