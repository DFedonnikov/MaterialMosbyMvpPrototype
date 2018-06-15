package com.prototype.fedonnikovds.materialmosbymvpprototype.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.prototype.fedonnikovds.materialmosbymvpprototype.App;
import com.prototype.fedonnikovds.materialmosbymvpprototype.model.ILoginService;
import com.prototype.fedonnikovds.materialmosbymvpprototype.utils.AuthUtils;
import com.prototype.fedonnikovds.materialmosbymvpprototype.view.ILoginView;


import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class LoginPresenter extends MvpBasePresenter<ILoginView> implements ILoginPresenter {

    @Inject
    AuthUtils authUtils;
    @Inject
    ILoginService loginService;

    private CompositeDisposable compositeDisposable;

    public LoginPresenter() {
        App.getAppComponent().inject(this);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void detachView() {
        super.detachView();
        compositeDisposable.clear();
    }

    @Override
    public void destroy() {
        super.destroy();
        compositeDisposable.dispose();
    }

    @Override
    public void login(String login, String password) {
        Disposable loginDisposable = loginService
                .login(createBody(login, password))
                .subscribe(x -> ifViewAttached(view -> {
                    //TODO
                }), throwable -> {
                    //TODO
                });
        compositeDisposable.add(loginDisposable);
    }

    private Map<String, String> createBody(String login, String password) {
        Map<String, String> content = new HashMap<>();
        final String passHash = authUtils.getPasswordHash(password, true);
        final String passHashNoSalt = authUtils.getPasswordHash(password, false);
        content.put("login", login);
        content.put("password_hash", passHash);
        content.put("password_hash2", passHashNoSalt);
        return content;
    }
}