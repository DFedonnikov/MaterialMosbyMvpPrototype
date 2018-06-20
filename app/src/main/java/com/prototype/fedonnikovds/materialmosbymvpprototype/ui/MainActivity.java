package com.prototype.fedonnikovds.materialmosbymvpprototype.ui;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.prototype.fedonnikovds.materialmosbymvpprototype.App;
import com.prototype.fedonnikovds.materialmosbymvpprototype.R;
import com.prototype.fedonnikovds.materialmosbymvpprototype.Screens;

import javax.inject.Inject;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Replace;

public class MainActivity extends AppCompatActivity {

    @Inject
    NavigatorHolder navigatorHolder;

    private Navigator navigator = new SupportFragmentNavigator(getSupportFragmentManager(), R.id.fragmentContainer) {
        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            switch (screenKey) {
                case Screens.LOGIN_SCREEN:
                    return LoginFragment.newInstance();
                case Screens.MATERIALS_SCREEN:
                    return MaterialsFragment.newInstance();
                default:
                    return LoginFragment.newInstance();
            }
        }

        @Override
        protected void showSystemMessage(String message) {

        }

        @Override
        public void applyCommands(Command[] commands) {
            super.applyCommands(commands);
            getSupportFragmentManager().executePendingTransactions();
        }

        @Override
        protected void exit() {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getAppComponent().inject(this);
        navigator.applyCommands(new Command[]{new Replace(Screens.LOGIN_SCREEN, null)});

//        LoginFragment loginFragment = LoginFragment.newInstance();
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fragmentContainer, loginFragment)
//                .commit();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        super.onPause();
        navigatorHolder.removeNavigator();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (fragment != null && fragment instanceof OnBackButtonListener) {
            ((OnBackButtonListener) fragment).onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

}
