package com.android.seanluckett.popularmovies.models;

public class ReviewData {
    private String author, text;

    public ReviewData(String author, String text) {
        this.author = author;
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }
}
