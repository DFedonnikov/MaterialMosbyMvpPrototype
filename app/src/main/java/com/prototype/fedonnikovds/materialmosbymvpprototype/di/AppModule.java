package com.prototype.fedonnikovds.materialmosbymvpprototype.di;

import com.prototype.fedonnikovds.materialmosbymvpprototype.model.UserInfo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class AppModule {

    @Provides
    @Singleton
    UserInfo provideUserInfo() {
        return new UserInfo();
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

}
