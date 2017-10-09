package com.lucaslz.uicomponentexamples;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.lucaslz.component.activity.SingleFragmentActivity;

/**
 * Created by lucas on 2017/10/8.
 */
public abstract class GoBackActivity extends SingleFragmentActivity {

    private String mActionBarTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            return;
        }
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setActionBarTitle(String actionBarTitle) {
        mActionBarTitle = actionBarTitle;

        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            return;
        }
        actionBar.setTitle(actionBarTitle);
    }

    public String getActionBarTitle() {
        return mActionBarTitle;
    }
}
