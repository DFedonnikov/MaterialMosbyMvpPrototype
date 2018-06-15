package com.prototype.fedonnikovds.materialmosbymvpprototype.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.jakewharton.rxbinding2.view.RxView;
import com.prototype.fedonnikovds.materialmosbymvpprototype.R;
import com.prototype.fedonnikovds.materialmosbymvpprototype.presenter.ILoginPresenter;
import com.prototype.fedonnikovds.materialmosbymvpprototype.presenter.LoginPresenter;
import com.prototype.fedonnikovds.materialmosbymvpprototype.view.ILoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class LoginFragment extends MvpFragment<ILoginView, ILoginPresenter> implements ILoginView {

    @BindView(R.id.et_login)
    EditText etLogin;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_login)
    Button btLogin;

    private static final String FAST_LOGIN = "u1498668657_1022";
    private static final String FAST_PASSWORD = "Freetree5";

    private CompositeDisposable compositeDisposable;
    private Unbinder unbinder;

    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @NonNull
    @Override
    public ILoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etLogin.setText(FAST_LOGIN);
        etPassword.setText(FAST_PASSWORD);
        Disposable btLoginDisposable =  RxView
                .clicks(btLogin)
                .subscribe(ignored -> presenter.login(etLogin.getText().toString(), etPassword.getText().toString()));
        compositeDisposable.add(btLoginDisposable);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
        unbinder.unbind();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
