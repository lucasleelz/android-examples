package com.lucaslz.uicomponentexamples.signup;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by lucas on 2017/10/14.
 */
public class SignUpFragment extends Fragment implements SignUpContract.View {

    private SignUpContract.Presenter mPresenter;

    public static SignUpFragment newInstance() {
        Bundle args = new Bundle();
        SignUpFragment fragment = new SignUpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setPresenter(SignUpContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }
}
