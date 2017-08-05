package com.lucasleelz.geoquiz;

/**
 * 问题。
 * Created by LucasLee on 2017/8/5.
 */
public class Question {

    private final int mTextResId;

    private final boolean mAnswerTrue;

    private boolean mHasAnswered = false; // 已经回答了。

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

    public boolean hasAnswered() {
        return mHasAnswered;
    }

    public void setAnswered(boolean hasAnswered) {
        mHasAnswered = hasAnswered;
    }
}
