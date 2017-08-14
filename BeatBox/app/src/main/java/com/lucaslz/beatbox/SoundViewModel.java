package com.lucaslz.beatbox;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/8/14.
 */
public class SoundViewModel extends BaseObservable {

    private Sound mSound;
    private BeatBox mBeatBox;

    public SoundViewModel(BeatBox beatBox) {
        mBeatBox = beatBox;
    }

    public Sound getSound() {
        return mSound;
    }

    public void setSound(Sound sound) {
        mSound = sound;
        notifyChange();
    }

    @Bindable
    public String getTitle() {
        return mSound.getName();
    }

    public void onClickButton() {
        mBeatBox.play(mSound);
    }
}
