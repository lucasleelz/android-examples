package com.lucasleelz.geoquiz;

/**
 * Created by LucasLee on 2017/8/5.
 */

public class Question {

    private int mTextResId;

    private boolean mAnswerTrue;

    private Question() {

    }

    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }
}
