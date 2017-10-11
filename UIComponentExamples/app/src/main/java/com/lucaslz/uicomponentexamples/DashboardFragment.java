package com.lucaslz.uicomponentexamples;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by lucas on 2017/10/11.
 */

public class DashboardFragment extends Fragment {

    public static final String TAG = DashboardFragment.class.getSimpleName();

    public static DashboardFragment newInstance() {
        Bundle args = new Bundle();

        DashboardFragment fragment = new DashboardFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
