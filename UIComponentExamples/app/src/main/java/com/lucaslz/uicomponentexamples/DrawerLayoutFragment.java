package com.lucaslz.uicomponentexamples;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lucas on 2017/10/9.
 */

public class DrawerLayoutFragment extends Fragment {

    public static DrawerLayoutFragment newInstance() {
        Bundle args = new Bundle();
        DrawerLayoutFragment fragment = new DrawerLayoutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_drawer_layout, container, false);
    }
}
