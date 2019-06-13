package com.android.seanluckett.popularmovies.models;

public class TrailerData {
    private final String name, type, size;

    public TrailerData(String name, String type, String size) {
        this.name = name;
        this.type = type;
        this.size = size;
    }

    public String getName() { return name; }

    public String getType() { return type; }

    public String getSize() { return size; }
}
