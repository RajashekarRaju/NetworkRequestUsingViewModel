package com.developersbreach.networkrequestusingviewmodel.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.developersbreach.networkrequestusingviewmodel.R;
import com.developersbreach.networkrequestusingviewmodel.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> mMoviesList;

    /**
     * This method is used to set the movie data on a MovieAdapter if we've already created one.
     * This is handy when we get new data from the web but don't want to create a
     * new MovieAdapter to display it.
     *
     * @param movieData The new movie data to be displayed.
     */
    void setMovieData(List<Movie> movieData) {
        if (mMoviesList == null) {
            mMoviesList = movieData;
            notifyDataSetChanged();
        }
    }

    /**
     * Cache of the children views for a movieData.
     */
    class MovieViewHolder extends RecyclerView.ViewHolder {

        // Private TextView variable to show movie title text
        private final TextView mTitleTextView;
        // Private ImageView variable to show poster for items
        private final ImageView mPosterImageView;

        MovieViewHolder(View view) {
            super(view);
            mTitleTextView = view.findViewById(R.id.main_movie_title);
            mPosterImageView = view.findViewById(R.id.main_movie_poster);
        }
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieAdapter.MovieViewHolder holder, int position) {
        Movie movie = mMoviesList.get(position);
        holder.mTitleTextView.setText(movie.getMovieTitle());
        Picasso.get().load(movie.getImageView()).into(holder.mPosterImageView);
    }

    @Override
    public int getItemCount() {
        if (mMoviesList == null) {
            return 0;
        }
        return mMoviesList.size();
    }
}
