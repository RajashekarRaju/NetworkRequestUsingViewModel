package com.developersbreach.networkrequestusingviewmodel.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.developersbreach.networkrequestusingviewmodel.R;
import com.developersbreach.networkrequestusingviewmodel.data.model.Movie;
import com.developersbreach.networkrequestusingviewmodel.viewModel.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mMovieRecyclerView;
    private MovieAdapter mMovieAdapter;

    private TextView mNoInternetConnectionTextView;
    private ImageView mNoInternetConnectionImageView;
    private ProgressBar mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNoInternetConnectionTextView = findViewById(R.id.no_connection_message);
        mNoInternetConnectionImageView = findViewById(R.id.no_connection_image);
        mLoadingBar = findViewById(R.id.loading_indicator);

        mMovieRecyclerView = findViewById(R.id.main_recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        mMovieRecyclerView.setLayoutManager(layoutManager);
        mMovieAdapter = new MovieAdapter();
        mMovieRecyclerView.setAdapter(mMovieAdapter);

        MainActivityViewModel model = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        model.getMutableLiveData().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if (movies.size() != 0) {
                    showData();
                    mMovieAdapter.setMovieData(movies);
                } else {
                    showError();
                }
            }
        });
    }

    private void showData() {
        mMovieRecyclerView.setVisibility(View.VISIBLE);
        mLoadingBar.setVisibility(View.GONE);
        mNoInternetConnectionTextView.setVisibility(View.GONE);
        mNoInternetConnectionImageView.setVisibility(View.GONE);
    }

    private void showError() {
        mMovieRecyclerView.setVisibility(View.INVISIBLE);
        mLoadingBar.setVisibility(View.GONE);
        mNoInternetConnectionTextView.setVisibility(View.VISIBLE);
        mNoInternetConnectionImageView.setVisibility(View.VISIBLE);
    }
}
