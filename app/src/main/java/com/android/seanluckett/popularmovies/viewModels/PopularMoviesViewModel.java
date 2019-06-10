package com.android.seanluckett.popularmovies.viewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.seanluckett.popularmovies.liveData.PopularMoviesLiveData;
import com.android.seanluckett.popularmovies.models.FilmData;
import com.android.seanluckett.popularmovies.utils.ApiService;
import com.android.seanluckett.popularmovies.utils.Configuration;
import com.android.seanluckett.popularmovies.wrappers.MovieApiWrapper;

import java.util.ArrayList;

public class PopularMoviesViewModel extends AndroidViewModel {
    private final PopularMoviesLiveData movieList;

    public PopularMoviesViewModel(Application application) {
        super(application);
        ApiService appApiService = Configuration.getApiServiceObject(application);
        MovieApiWrapper wrapper = new MovieApiWrapper(appApiService);
        movieList = new PopularMoviesLiveData(wrapper);
    }

    public LiveData<ArrayList<FilmData>> getMovies() {
        return movieList;
    }

}
