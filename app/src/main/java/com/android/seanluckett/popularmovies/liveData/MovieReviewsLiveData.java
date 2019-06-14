package com.android.seanluckett.popularmovies.liveData;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.android.seanluckett.popularmovies.models.ReviewData;
import com.android.seanluckett.popularmovies.wrappers.MovieApiWrapper;

import java.util.ArrayList;

public class MovieReviewsLiveData extends LiveData<ArrayList<ReviewData>> {
    private final MovieApiWrapper apiWrapper;

    public MovieReviewsLiveData(MovieApiWrapper apiWrapper, int movieId) {
        this.apiWrapper = apiWrapper;
        loadData(movieId);
    }

    @SuppressLint("StaticFieldLeak")
    private void loadData(final int movieId) {
        new AsyncTask<Void, Void, ArrayList<ReviewData>>() {

            @Override
            protected ArrayList<ReviewData> doInBackground(Void... voids) {
                return apiWrapper.getReviews(movieId);
            }

            @Override
            protected void onPostExecute(ArrayList<ReviewData> reviews) {
                setValue(reviews);
            }
        }.execute();
    }
}
