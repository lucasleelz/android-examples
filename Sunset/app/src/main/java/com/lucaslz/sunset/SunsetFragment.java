package com.lucaslz.sunset;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/9/22.
 */
public class SunsetFragment extends Fragment {

    private static final String yPropertyName = "y";
    private static final String backgroundColorPropertyName = "backgroundColor";
    private static final int animatorDuration = 3000;

    private View mSceneView;
    private View mSunView;
    private View mSkyView;

    private int mBlueSkyColor;
    private int mSunsetSkyColor;
    private int mNightSkyColor;

    public static SunsetFragment newInstance() {
        return new SunsetFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sunset, container, false);
        mSceneView = view;
        mSunView = view.findViewById(R.id.sun);
        mSkyView = view.findViewById(R.id.sky);

        Resources resources = getResources();
        mBlueSkyColor = resources.getColor(R.color.blue_sky);
        mSunsetSkyColor = resources.getColor(R.color.sunset_sky);
        mNightSkyColor = resources.getColor(R.color.night_sky);


        mSceneView.setOnClickListener(v -> {
            startAnimation();
        });
        return view;
    }

    private void startAnimation() {
        float sunYStart = mSunView.getTop();
        float sunYEnd = mSkyView.getHeight();

        ObjectAnimator heightAnimator = ObjectAnimator
                .ofFloat(mSunView, yPropertyName, sunYStart, sunYEnd)
                .setDuration(animatorDuration);
        heightAnimator.setInterpolator(new AccelerateInterpolator());

        ObjectAnimator sunsetSkyAnimator = ObjectAnimator
                .ofInt(mSkyView, backgroundColorPropertyName, mBlueSkyColor, mSunsetSkyColor)
                .setDuration(animatorDuration);
        sunsetSkyAnimator.setEvaluator(new ArgbEvaluator());
        heightAnimator.start();
        sunsetSkyAnimator.start();
    }
}
