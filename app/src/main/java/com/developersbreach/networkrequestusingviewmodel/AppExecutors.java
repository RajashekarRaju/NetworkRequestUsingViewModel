package com.developersbreach.networkrequestusingviewmodel;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {

    private Executor mNetworkIO;
    private static AppExecutors INSTANCE;
    private static final Object LOCK = new Object();

    private AppExecutors(Executor networkIO) {
        this.mNetworkIO = networkIO;
    }

    public static AppExecutors getInstance() {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                INSTANCE = new AppExecutors(Executors.newSingleThreadExecutor());
            }
        }
        return INSTANCE;
    }

    public Executor getNetworkIO() {
        return mNetworkIO;
    }
}
