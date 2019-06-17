package com.android.seanluckett.popularmovies.database;

import android.net.Uri;

import androidx.room.TypeConverter;

import com.android.seanluckett.popularmovies.utils.BuildFullPosterUri;

public class StringUriConverter {
    @TypeConverter
    public static Uri toUri(String path) {
        return path == null ? null : BuildFullPosterUri.execute(path);
    }

    @TypeConverter
    public static String toString(Uri path) {
        return path == null ? null : path.toString();
    }
}
