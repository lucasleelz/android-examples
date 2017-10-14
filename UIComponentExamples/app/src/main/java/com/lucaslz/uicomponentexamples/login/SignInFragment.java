package com.lucaslz.uicomponentexamples.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import com.lucaslz.uicomponentexamples.R;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/13.
 */
public class SignInFragment extends Fragment implements SignInContract.View {

    private SignInContract.Presenter mPresenter;

    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private View mProgressView;
    private View mLoginFormView;

    public static SignInFragment newInstance() {
        Bundle args = new Bundle();
        SignInFragment fragment = new SignInFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        mEmailEditText = rootView.findViewById(R.id.email);
        mPasswordEditText = rootView.findViewById(R.id.password);
        mPasswordEditText.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin();
//                signUp();
                return true;
            }
            return false;
        });

        Button mEmailSignInButton = rootView.findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener((view) -> attemptLogin());

        mLoginFormView = rootView.findViewById(R.id.login_form);
        mProgressView = rootView.findViewById(R.id.login_progress);
        return rootView;
    }

    void attemptLogin() {

        mEmailEditText.setError(null);
        mPasswordEditText.setError(null);

        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(email) && !isPasswordValid(password)) {
            mEmailEditText.setError(getString(R.string.error_field_required));
            focusView = mEmailEditText;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            // login...
        }
    }

    boolean isPasswordValid(String password) {
        return true;
    }

    void showProgress(final boolean show) {

        int shortAnimTime = getResources().getInteger(android.R.integer.config_longAnimTime);

        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void setPresenter(SignInContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
