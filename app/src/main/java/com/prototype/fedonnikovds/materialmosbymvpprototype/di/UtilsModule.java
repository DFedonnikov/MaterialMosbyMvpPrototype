package com.prototype.fedonnikovds.materialmosbymvpprototype.di;

import com.prototype.fedonnikovds.materialmosbymvpprototype.utils.AuthUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilsModule {

    @Singleton
    @Provides
    AuthUtils provideAuthUtils() {
        return new AuthUtils();
    }

}
