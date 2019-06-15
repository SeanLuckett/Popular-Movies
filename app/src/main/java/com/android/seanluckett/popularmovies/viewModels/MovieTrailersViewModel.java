package com.android.seanluckett.popularmovies.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.seanluckett.popularmovies.liveData.MovieTrailersLiveData;
import com.android.seanluckett.popularmovies.models.TrailerData;
import com.android.seanluckett.popularmovies.utils.ApiService;
import com.android.seanluckett.popularmovies.utils.Configuration;
import com.android.seanluckett.popularmovies.wrappers.MovieApiWrapper;

import java.util.ArrayList;

public class MovieTrailersViewModel extends AndroidViewModel {
    private final MovieApiWrapper apiWrapper;
    private MovieTrailersLiveData trailers;

    public MovieTrailersViewModel(@NonNull Application application) {
        super(application);
        ApiService appApiService = Configuration.getApiServiceObject(application);
        apiWrapper = new MovieApiWrapper(appApiService);
    }

    public LiveData<ArrayList<TrailerData>> getTrailers(int movieId) {
        if (trailers != null) {
            return trailers;
        } else {
            trailers = new MovieTrailersLiveData(apiWrapper, movieId);
            return trailers;
        }
    }
}
