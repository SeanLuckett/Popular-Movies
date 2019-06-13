package com.android.seanluckett.popularmovies.liveData;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.android.seanluckett.popularmovies.models.TrailerData;
import com.android.seanluckett.popularmovies.wrappers.MovieApiWrapper;

import java.util.ArrayList;

public class MovieTrailersLiveData extends LiveData<ArrayList<TrailerData>> {
    private final MovieApiWrapper apiWrapper;

    public MovieTrailersLiveData(MovieApiWrapper wrapper, int movieId) {
        apiWrapper = wrapper;
        loadData(movieId);
    }

    @SuppressLint("StaticFieldLeak")
    private void loadData(final int movieId) {
        new AsyncTask<Void, Void, ArrayList<TrailerData>>() {

            @Override
            protected ArrayList<TrailerData> doInBackground(Void... voids) {
                return apiWrapper.getTrailers(movieId);
            }

            @Override
            protected void onPostExecute(ArrayList<TrailerData> trailers) {
                setValue(trailers);
            }
        }.execute();
    }

}
