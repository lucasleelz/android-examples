package com.lucaslz.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/8/14.
 */
public class BeatBox {

    public static final String TAG = "BeatBox";

    public static final String SOUNDS_FOLDER = "sample_sounds";

    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();

    public BeatBox(Context context) {
        mAssets = context.getAssets();
        loadSounds();
    }

    private void loadSounds() {
        String[] soundNames;
        try {
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found " + soundNames.length + " sounds");
        } catch (IOException e) {
            Log.e(TAG, "Could not list assets", e);
            return;
        }
        for (String fileName : soundNames) {
            mSounds.add(new Sound(SOUNDS_FOLDER + "/" + fileName));
        }
    }

    public List<Sound> getSounds() {
        return mSounds;
    }
}
