package com.lucaslz.uicomponentexamples.login;

import com.lucaslz.uicomponentexamples.BasePresenter;
import com.lucaslz.uicomponentexamples.BaseView;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/13.
 */
public interface LoginContract {

    interface View extends BaseView<Presenter> {

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

    }
}
