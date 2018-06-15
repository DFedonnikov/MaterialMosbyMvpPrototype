package com.prototype.fedonnikovds.materialmosbymvpprototype.presenter;

import android.text.Editable;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.prototype.fedonnikovds.materialmosbymvpprototype.view.ILoginView;

public interface ILoginPresenter extends MvpPresenter<ILoginView> {
    void login(String login, String password);
}
