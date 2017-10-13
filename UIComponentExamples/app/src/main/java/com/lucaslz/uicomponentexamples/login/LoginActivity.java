package com.lucaslz.uicomponentexamples.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

import com.lucaslz.component.activity.BaseActivity;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/13.
 */
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.lucaslz.component.R.layout.activity_fragment);

        FragmentManager fragmentManager = getSupportFragmentManager();
        LoginFragment fragment = (LoginFragment) fragmentManager.findFragmentById(com.lucaslz.component.R.id.fragment_container);
        if (fragment == null) {
            fragment = LoginFragment.newInstance();
            fragmentManager.beginTransaction()
                    .add(com.lucaslz.component.R.id.fragment_container, fragment)
                    .commit();
        }

        new LoginPresenter(fragment);
    }

}
