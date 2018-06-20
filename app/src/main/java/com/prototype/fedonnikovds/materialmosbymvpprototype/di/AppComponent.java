package com.prototype.fedonnikovds.materialmosbymvpprototype.di;

import com.prototype.fedonnikovds.materialmosbymvpprototype.presenter.LoginPresenter;
import com.prototype.fedonnikovds.materialmosbymvpprototype.presenter.MaterialsPresenter;
import com.prototype.fedonnikovds.materialmosbymvpprototype.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class, UtilsModule.class, NavigationModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void inject(LoginPresenter loginPresenter);

    void inject(MaterialsPresenter materialsPresenter);
}
