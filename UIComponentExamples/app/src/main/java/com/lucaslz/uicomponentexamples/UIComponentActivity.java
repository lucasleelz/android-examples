package com.lucaslz.uicomponentexamples;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by lucas on 2017/10/8.
 */
public class UIComponentActivity extends GoBackActivity {

    private static final String TYPE_KEY = "TYPE";

    public static Intent newIntent(Context packageContext, String type) {
        Intent intent = new Intent(packageContext, UIComponentActivity.class);
        intent.putExtra(TYPE_KEY, type);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        Intent intent = getIntent();
        String type = intent.getStringExtra(TYPE_KEY);
        setActionBarTitle(type);
        if ("Layout".equals(type)) {
            return LayoutFragment.newInstance();
        }
        if ("Text".equals(type)) {
            return TextFragment.newInstance();
        }
        if ("CollapsingToolbarLayout".equals(type)) {
            return CollapsingToolbarLayoutFragment.newInstance();
        }

        return new Fragment();
    }
}
