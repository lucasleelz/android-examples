package com.lucaslz.uicomponentexamples.signin;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/13.
 */
public class SignInPresenter implements SignInContract.Presenter {

    private final SignInContract.View mLoginView;

    public SignInPresenter(SignInContract.View loginView) {
        mLoginView = loginView;
        mLoginView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
