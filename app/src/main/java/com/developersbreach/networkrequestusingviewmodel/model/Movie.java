package com.developersbreach.networkrequestusingviewmodel.model;

public class Movie {

    private final String mMovieTitle;
    private final String mImageView;

    public Movie(String movieTitle, String imageView) {
        this.mMovieTitle = movieTitle;
        this.mImageView = imageView;
    }

    public String getMovieTitle() {
        return mMovieTitle;
    }

    public String getImageView() {
        return mImageView;
    }
}
