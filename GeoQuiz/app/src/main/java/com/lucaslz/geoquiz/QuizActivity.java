package com.lucaslz.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;

    private Button mTrueButton;
    private Button mFalseButton;

    private TextView mQuestionTextView;

    private final Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, false),
            new Question(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;            // 当前问题索引。
    private int mHasAnsweredCount = 0;        // 回答总数。
    private int mHasAnsweredCorrectCount = 0; // 回答正确数。

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate(Bundle) called");

        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        ImageButton mPreviousImageButton = findViewById(R.id.previous_image_button);
        ImageButton mNextImageButton = findViewById(R.id.next_image_button);
        Button cheatButton = findViewById(R.id.cheat_button);
        cheatButton.setOnClickListener(view -> {
            boolean answerIsTrue = this.mQuestionBank[mCurrentIndex].isAnswerTrue();
            Intent intent = CheatActivity.createIntent(QuizActivity.this, answerIsTrue);
            startActivityForResult(intent, REQUEST_CODE_CHEAT);
        });

        mQuestionTextView = findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(view -> checkNext());
        updateQuestion();

        mTrueButton.setOnClickListener(view -> checkAnswer(true));
        mFalseButton.setOnClickListener(view -> checkAnswer(false));

        updateAnswerButton();

        mPreviousImageButton.setOnClickListener(view -> checkPrevious());
        mNextImageButton.setOnClickListener(view -> checkNext());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode != REQUEST_CODE_CHEAT) {
            return;
        }
        if (data == null) {
            return;
        }

        Question currentQuestion = mQuestionBank[mCurrentIndex];
        currentQuestion.setCheater(
                CheatActivity.hasAnswerShown(data));
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "onPause() called");
    }

    /**
     * 在onStop之前由系统调用。除非用户按了后退键。
     * 建议只保存基本类型数据。
     * 实现了序列化的类对象或者实现了Parcelable的类对象使用其他的方式。
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.i(TAG, "onSaveInstanceState()");

        outState.putInt(KEY_INDEX, mCurrentIndex);
    }

    /**
     * 保存永久性数据。而onSaveInstanceState只保存暂时数据。
     */
    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy() called");
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        Question currentQuestion = mQuestionBank[mCurrentIndex];
        if (currentQuestion.hasAnswered()) {
            Toast.makeText(this, R.string.has_answered_toast, Toast.LENGTH_SHORT).show();
            return;
        }


        currentQuestion.setAnswered(true);
        updateAnswerButton();

        mHasAnsweredCount++;

        int messageResId = R.string.incorrect_toast;
        if (currentQuestion.isCheater()) {
            messageResId =  R.string.judgment_toast;
        } else if (userPressedTrue == currentQuestion.isAnswerTrue()) {
            messageResId = R.string.correct_toast;
            mHasAnsweredCorrectCount++;
        }
        if (mHasAnsweredCount == mQuestionBank.length) {
            Log.i(TAG, "正确数 ：" + mHasAnsweredCorrectCount);
            Toast.makeText(this, "你已经回答了所有问题，正确率为：" + formatAnsweredResult() + "%", Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    private void checkPrevious() {
        mCurrentIndex = mCurrentIndex == 0 ? mQuestionBank.length - 1 : mCurrentIndex - 1;
        updateQuestion();
        updateAnswerButton();
    }

    private void checkNext() {
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        updateQuestion();
        updateAnswerButton();
    }

    private void updateAnswerButton() {

        if (this.mQuestionBank[mCurrentIndex].hasAnswered()) {
            mTrueButton.setBackgroundColor(Color.GRAY);
            mTrueButton.setTextColor(Color.BLACK);

            mFalseButton.setBackgroundColor(Color.GRAY);
            mFalseButton.setTextColor(Color.BLACK);

        } else {
            mTrueButton.setBackgroundColor(Color.BLUE);
            mTrueButton.setTextColor(Color.WHITE);

            mFalseButton.setBackgroundColor(Color.BLUE);
            mFalseButton.setTextColor(Color.WHITE);
        }
    }

    private String formatAnsweredResult() {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        return numberFormat.format((float)mHasAnsweredCorrectCount / (float)mHasAnsweredCount * 100);
    }
}
