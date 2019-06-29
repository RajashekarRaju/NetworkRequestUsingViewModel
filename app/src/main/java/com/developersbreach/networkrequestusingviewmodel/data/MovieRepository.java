package com.developersbreach.networkrequestusingviewmodel.data;

import android.arch.lifecycle.LiveData;

import com.developersbreach.networkrequestusingviewmodel.AppExecutors;
import com.developersbreach.networkrequestusingviewmodel.model.Movie;
import com.developersbreach.networkrequestusingviewmodel.data.network.NetworkRequestDataSource;

import java.util.List;

public class MovieRepository {

    private LiveData<List<Movie>> mMediatorLiveData;
    private static MovieRepository INSTANCE;
    private static final Object LOCK = new Object();

    private MovieRepository() {
        AppExecutors appExecutors = AppExecutors.getInstance();
        NetworkRequestDataSource networkRequestDataSource = NetworkRequestDataSource.getInstance(appExecutors);
        mMediatorLiveData = networkRequestDataSource.getMediatorLiveData();
    }

    public static MovieRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                INSTANCE = new MovieRepository();
            }
        }
        return INSTANCE;
    }

    public LiveData<List<Movie>> getMediatorLiveData() {
        return mMediatorLiveData;
    }
}
