package com.developersbreach.networkrequestusingviewmodel.data.network;

import android.arch.lifecycle.MediatorLiveData;

import com.developersbreach.networkrequestusingviewmodel.AppExecutors;
import com.developersbreach.networkrequestusingviewmodel.model.Movie;

import java.net.URL;
import java.util.List;

public class NetworkRequestDataSource {

    private static final Object LOCK = new Object();
    private static NetworkRequestDataSource INSTANCE;

    private static MediatorLiveData<List<Movie>> mMediatorLiveData;
    private static AppExecutors mExecutors;

    private NetworkRequestDataSource(AppExecutors executors) {
        mExecutors = executors;
        mMediatorLiveData = new MediatorLiveData<>();

        mExecutors.getNetworkIO().execute(new Runnable() {
            @Override
            public void run() {
                fetchMovies();
            }
        });
    }

    public MediatorLiveData<List<Movie>> getMediatorLiveData() {
        return mMediatorLiveData;
    }

    public static NetworkRequestDataSource getInstance(AppExecutors executors) {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                INSTANCE = new NetworkRequestDataSource(executors);
            }
        }
        return INSTANCE;
    }

    private void fetchMovies() {
        mExecutors.getNetworkIO().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL requestUrl = QueryUtils.createUrl(QueryUtils.uriBuilder());
                    String responseString = QueryUtils.getResponseFromHttpUrl(requestUrl);
                    List<Movie> movieList = QueryUtils.extractMovieDataFromJson(responseString);
                    mMediatorLiveData.postValue(movieList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
