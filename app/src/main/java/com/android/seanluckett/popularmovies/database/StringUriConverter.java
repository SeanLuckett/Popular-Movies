package com.android.seanluckett.popularmovies.database;

import android.net.Uri;

import androidx.room.TypeConverter;


class StringUriConverter {
    @TypeConverter
    public static Uri toUri(String path) {
        return path == null ? null : Uri.parse(path);
    }

    @TypeConverter
    public static String toString(Uri path) {
        return path == null ? null : path.toString();
    }
}
