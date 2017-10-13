package com.lucaslz.uicomponentexamples.login;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/13.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View mLoginView;

    public LoginPresenter(LoginContract.View loginView) {
        mLoginView = loginView;
        mLoginView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
