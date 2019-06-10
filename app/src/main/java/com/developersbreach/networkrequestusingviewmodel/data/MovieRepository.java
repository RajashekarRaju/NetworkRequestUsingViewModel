package com.developersbreach.networkrequestusingviewmodel.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.os.AsyncTask;


import com.developersbreach.networkrequestusingviewmodel.data.model.Movie;
import com.developersbreach.networkrequestusingviewmodel.data.network.QueryUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository {

    private static MediatorLiveData<List<Movie>> mMediatorLiveData;
    private static List<Movie> mMovieList;
    private static MovieRepository INSTANCE;

    private MovieRepository() {
        mMediatorLiveData = new MediatorLiveData<>();
        new MovieAsyncTask().execute();
    }

    public LiveData<List<Movie>> getMediatorLiveData() {
        return mMediatorLiveData;
    }

    public synchronized static MovieRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MovieRepository();
        }
        return INSTANCE;
    }

    private static class MovieAsyncTask extends AsyncTask<Void, Void, List<Movie>> {

        @Override
        protected List<Movie> doInBackground(Void... voids) {

            try {
                mMovieList = new ArrayList<>();
                URL requestUrl = QueryUtils.createUrl(QueryUtils.uriBuilder());
                String responseString = QueryUtils.getResponseFromHttpUrl(requestUrl);
                mMovieList = QueryUtils.extractMovieDataFromJson(responseString);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return mMovieList;
        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            mMediatorLiveData.postValue(movies);
        }
    }
}
