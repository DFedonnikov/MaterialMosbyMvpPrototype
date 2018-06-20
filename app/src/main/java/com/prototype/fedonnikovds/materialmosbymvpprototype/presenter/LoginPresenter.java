package com.prototype.fedonnikovds.materialmosbymvpprototype.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.prototype.fedonnikovds.materialmosbymvpprototype.App;
import com.prototype.fedonnikovds.materialmosbymvpprototype.Screens;
import com.prototype.fedonnikovds.materialmosbymvpprototype.model.UserInfo;
import com.prototype.fedonnikovds.materialmosbymvpprototype.service.ILoginService;
import com.prototype.fedonnikovds.materialmosbymvpprototype.utils.AuthUtils;
import com.prototype.fedonnikovds.materialmosbymvpprototype.view.ILoginView;


import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ru.terrakok.cicerone.Router;

public class LoginPresenter extends MvpBasePresenter<ILoginView> implements ILoginPresenter {

    @Inject
    AuthUtils authUtils;
    @Inject
    ILoginService loginService;
    @Inject
    Router router;
    @Inject
    UserInfo userInfo;
    @Inject
    CompositeDisposable compositeDisposable;

    public LoginPresenter() {
        App.getAppComponent().inject(this);
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
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> ifViewAttached(view -> {
                    initUserInfo(data);
                    router.navigateTo(Screens.MATERIALS_SCREEN);
                }), throwable -> {
                    throwable.printStackTrace();
                    ifViewAttached(view -> view.showError(throwable.getMessage()));
                });
        compositeDisposable.add(loginDisposable);
    }

    private void initUserInfo(UserInfo data) {
        userInfo.setId(data.getId());
        userInfo.setAuthToken(data.getAuthToken());
        userInfo.setProfiles(data.getProfiles());
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

    @Override
    public void onBackCommand() {
        router.exit();
    }
}