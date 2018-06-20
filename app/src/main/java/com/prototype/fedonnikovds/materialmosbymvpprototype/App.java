package com.prototype.fedonnikovds.materialmosbymvpprototype;

import android.app.Application;

import com.prototype.fedonnikovds.materialmosbymvpprototype.di.AppComponent;
import com.prototype.fedonnikovds.materialmosbymvpprototype.di.DaggerAppComponent;

public class App extends Application {

    private static AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
