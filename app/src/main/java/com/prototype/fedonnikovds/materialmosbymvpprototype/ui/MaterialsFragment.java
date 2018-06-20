package com.prototype.fedonnikovds.materialmosbymvpprototype.ui;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceFragment;
import com.prototype.fedonnikovds.materialmosbymvpprototype.R;
import com.prototype.fedonnikovds.materialmosbymvpprototype.model.Material;
import com.prototype.fedonnikovds.materialmosbymvpprototype.presenter.IMaterialsPresenter;
import com.prototype.fedonnikovds.materialmosbymvpprototype.presenter.MaterialsPresenter;
import com.prototype.fedonnikovds.materialmosbymvpprototype.view.IMaterialsView;

import java.util.List;

public class MaterialsFragment extends MvpLceFragment<SwipeRefreshLayout, List<Material>, IMaterialsView, IMaterialsPresenter>
        implements IMaterialsView, SwipeRefreshLayout.OnRefreshListener, OnBackButtonListener {

    public static MaterialsFragment newInstance() {
        return new MaterialsFragment();
    }

    public MaterialsFragment() {
        // Required empty public constructor
    }

    @Override
    @NonNull
    public IMaterialsPresenter createPresenter() {
        return new MaterialsPresenter();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_materials, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contentView.setOnRefreshListener(this);
        loadData(false);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    @Override
    public void setData(List<Material> data) {

    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadMaterials(pullToRefresh);
    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        contentView.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public void onBackPressed() {
        presenter.onBackCommand();
    }
}
