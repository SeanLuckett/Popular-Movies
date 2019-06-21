package com.android.seanluckett.popularmovies.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.android.seanluckett.popularmovies.models.Favorite;

@Database(entities = {Favorite.class}, version = 2, exportSchema = false)
@TypeConverters(StringUriConverter.class)
public abstract class FavoriteDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "popularmovies";
    private static volatile FavoriteDatabase ourInstance;

    public static synchronized FavoriteDatabase getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = create(context);
        }
        return ourInstance;
    }

    private static FavoriteDatabase create(final Context context) {
        return Room.databaseBuilder(
            context,
            FavoriteDatabase.class,
            FavoriteDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build();
    }

    public abstract FavoriteDao getFavoriteDao();
}
