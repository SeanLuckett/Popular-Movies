package com.android.seanluckett.popularmovies.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.seanluckett.popularmovies.liveData.MovieReviewsLiveData;
import com.android.seanluckett.popularmovies.models.ReviewData;
import com.android.seanluckett.popularmovies.utils.ApiService;
import com.android.seanluckett.popularmovies.utils.Configuration;
import com.android.seanluckett.popularmovies.wrappers.MovieApiWrapper;

import java.util.ArrayList;

public class MovieReviewsViewModel extends AndroidViewModel {
    private final MovieApiWrapper apiWrapper;
    private MovieReviewsLiveData reviews;

    public MovieReviewsViewModel(@NonNull Application application) {
        super(application);
        ApiService appApiService = Configuration.getApiServiceObject(application);
        apiWrapper = new MovieApiWrapper(appApiService);
    }

    public LiveData<ArrayList<ReviewData>> getReviews(int movieId) {
        if (reviews == null) {
            reviews = new MovieReviewsLiveData(apiWrapper, movieId);
        }

        return reviews;
    }
}
