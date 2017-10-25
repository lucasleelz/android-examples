package com.lucaslz.uicomponentexamples.signup;

/**
 * Created by lucas on 2017/10/14.
 */

public class SignUpPresenter implements SignUpContract.Presenter {

    private SignUpContract.View mSignUpView;

    public SignUpPresenter(SignUpContract.View signUpView) {
        mSignUpView = signUpView;
        mSignUpView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
