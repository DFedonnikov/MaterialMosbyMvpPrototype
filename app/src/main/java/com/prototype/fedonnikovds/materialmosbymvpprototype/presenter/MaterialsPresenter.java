package com.prototype.fedonnikovds.materialmosbymvpprototype.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.prototype.fedonnikovds.materialmosbymvpprototype.App;
import com.prototype.fedonnikovds.materialmosbymvpprototype.Screens;
import com.prototype.fedonnikovds.materialmosbymvpprototype.model.UserInfo;
import com.prototype.fedonnikovds.materialmosbymvpprototype.service.IMaterialService;
import com.prototype.fedonnikovds.materialmosbymvpprototype.view.IMaterialsView;

import org.reactivestreams.Subscription;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import ru.terrakok.cicerone.Router;

public class MaterialsPresenter extends MvpBasePresenter<IMaterialsView> implements IMaterialsPresenter {

    @Inject
    Router router;
    @Inject
    IMaterialService materialService;
    @Inject
    UserInfo userInfo;
    @Inject
    CompositeDisposable compositeDisposable;

    public MaterialsPresenter() {
        App.getAppComponent().inject(this);
    }

    @Override
    public void loadMaterials(boolean pullToRefresh) {
        ifViewAttached(view -> view.showLoading(pullToRefresh));

        Disposable disposable = materialService
                .getMaterialsList(createHeaders(userInfo), "2")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> ifViewAttached(view -> {
                            view.setData(data);
                            view.showContent();
                        }),
                        throwable -> {
                            throwable.printStackTrace();
                            ifViewAttached(view -> view.showError(throwable, pullToRefresh));
                        });
        compositeDisposable.add(disposable);
    }

    private Map<String, String> createHeaders(UserInfo userInfo) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Auth-Token", userInfo.getAuthToken());
        headers.put("User-Id", userInfo.getId());
        headers.put("Profile-Id", userInfo.getProfiles().get(0).getId());
        return headers;
    }

    @Override
    public void onBackCommand() {
        router.backTo(Screens.LOGIN_SCREEN);
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
}
