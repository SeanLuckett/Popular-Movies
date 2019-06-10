package com.android.seanluckett.popularmovies.viewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.seanluckett.popularmovies.liveData.TopMoviesLiveData;
import com.android.seanluckett.popularmovies.models.FilmData;
import com.android.seanluckett.popularmovies.utils.ApiService;
import com.android.seanluckett.popularmovies.utils.Configuration;
import com.android.seanluckett.popularmovies.wrappers.MovieApiWrapper;

import java.util.ArrayList;

public class TopMoviesViewModel extends AndroidViewModel {
    private final TopMoviesLiveData movieList;

    public TopMoviesViewModel(Application application) {
        super(application);
        ApiService appApiService = Configuration.getApiServiceObject(application);
        MovieApiWrapper wrapper = new MovieApiWrapper(appApiService);
        movieList = new TopMoviesLiveData(wrapper);
    }

    public LiveData<ArrayList<FilmData>> getMovies() {
        return movieList;
    }

}
