package com.android.seanluckett.popularmovies.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.android.seanluckett.popularmovies.models.Favorite;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Query("select * from favorites")
    List<Favorite> loadFavorites();

    @Insert
    void insert(Favorite favorite);

    @Delete
    void delete(Favorite favorite);

    @Query("select * from favorites where id = :id")
    Favorite findById(int id);

    @Query("select * from favorites where movieDbId = :movieDbId")
    Favorite findByMovieDbId(int movieDbId);
}
