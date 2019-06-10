package com.developersbreach.networkrequestusingviewmodel.data.model;

public class Movie {

    private final String title;
    private final String imageView;

    public Movie(String title, String imageView) {
        this.title = title;
        this.imageView = imageView;
    }

    public String getTitle() {
        return title;
    }

    public String getImageView() {
        return imageView;
    }
}
