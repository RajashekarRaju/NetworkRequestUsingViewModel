package com.developersbreach.networkrequestusingviewmodel.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.developersbreach.networkrequestusingviewmodel.data.MovieRepository;
import com.developersbreach.networkrequestusingviewmodel.model.Movie;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private static LiveData<List<Movie>> mMediatorLiveData;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        mMediatorLiveData = MovieRepository.getInstance().getMediatorLiveData();
    }

    LiveData<List<Movie>> getMediatorLiveData() {
        return mMediatorLiveData;
    }
}
