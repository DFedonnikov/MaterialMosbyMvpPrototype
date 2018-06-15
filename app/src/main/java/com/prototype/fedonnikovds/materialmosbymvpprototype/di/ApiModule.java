package com.prototype.fedonnikovds.materialmosbymvpprototype.di;

import android.support.annotation.NonNull;

import com.prototype.fedonnikovds.materialmosbymvpprototype.model.ILoginService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    private static final String BASE_URL = "https://uchebnik-stable.mos.ru";

    @Provides
    @Singleton
    ILoginService provideLoginService(@NonNull Retrofit retrofit) {
        return retrofit.create(ILoginService.class);
    }


    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
