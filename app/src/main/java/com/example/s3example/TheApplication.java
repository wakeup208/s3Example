package com.example.s3example;

import android.app.Application;

public class TheApplication extends Application {

    public static TheApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}