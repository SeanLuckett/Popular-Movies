package com.android.seanluckett.popularmovies.utils;

import com.android.seanluckett.popularmovies.models.Favorite;
import com.android.seanluckett.popularmovies.models.FilmData;

public class ConvertToFilmData {
    public static FilmData execute(Favorite favorite) {
        return new FilmData(
            favorite.getMovieDbId(),
            favorite.getTitle(),
            favorite.getPosterImagePath(),
            favorite.getPlotSummary(),
            favorite.getRating(),
            favorite.getReleaseDate()
        );
    }
}
