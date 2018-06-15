package com.prototype.fedonnikovds.materialmosbymvpprototype.di;

import com.prototype.fedonnikovds.materialmosbymvpprototype.model.ILoginService;
import com.prototype.fedonnikovds.materialmosbymvpprototype.presenter.LoginPresenter;
import com.prototype.fedonnikovds.materialmosbymvpprototype.utils.AuthUtils;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class, UtilsModule.class})
public interface AppComponent {

    ILoginService getLoginService();

    AuthUtils getAuthUtils();

    void inject(LoginPresenter loginPresenter);
}
