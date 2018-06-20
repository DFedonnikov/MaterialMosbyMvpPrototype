package com.prototype.fedonnikovds.materialmosbymvpprototype.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.prototype.fedonnikovds.materialmosbymvpprototype.view.IMaterialsView;

public interface IMaterialsPresenter extends MvpPresenter<IMaterialsView> {
    void onBackCommand();

    void loadMaterials(boolean pullToRefresh);
}
