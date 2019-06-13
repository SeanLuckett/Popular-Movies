package com.android.seanluckett.popularmovies.models;

public class TrailerData {
    private final String name, type, size, key;

    public TrailerData(String name, String type, String size, String key) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.key = key;
    }

    public String getName() { return name; }

    public String getType() { return type; }

    public String getSize() { return size; }

    public String getKey() { return key; }
}
