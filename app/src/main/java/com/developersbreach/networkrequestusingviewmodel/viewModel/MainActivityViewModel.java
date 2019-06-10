package com.developersbreach.networkrequestusingviewmodel.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;


import com.developersbreach.networkrequestusingviewmodel.data.MovieRepository;
import com.developersbreach.networkrequestusingviewmodel.data.model.Movie;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private static LiveData<List<Movie>> mMutableLiveData;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        mMutableLiveData = MovieRepository.getInstance().getMediatorLiveData();
    }

    public LiveData<List<Movie>> getMutableLiveData() {
        return mMutableLiveData;
    }
}
