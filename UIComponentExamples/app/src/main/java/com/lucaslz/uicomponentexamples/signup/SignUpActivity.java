package com.lucaslz.uicomponentexamples.signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

import com.lucaslz.component.activity.BaseActivity;

/**
 * Created by lucas on 2017/10/14.
 */

public class SignUpActivity extends BaseActivity {

    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, SignUpActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.lucaslz.component.R.layout.activity_fragment);

        FragmentManager fragmentManager = getSupportFragmentManager();
        SignUpFragment fragment = (SignUpFragment) fragmentManager.findFragmentById(com.lucaslz.component.R.id.fragment_container);
        if (fragment == null) {
            fragment = SignUpFragment.newInstance();
            fragmentManager.beginTransaction()
                    .add(com.lucaslz.component.R.id.fragment_container, fragment)
                    .commit();
        }

        new SignUpPresenter(fragment);
    }
}
