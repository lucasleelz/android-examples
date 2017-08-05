package com.lucasleelz.geoquiz;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    public static final String TAG = "QuizActivity";
    public static final String KEY_INDEX = "index";

    private Button mTrueButton;
    private Button mFalseButton;

    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, true),
            new Question(R.string.question_africa, true),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;

    private ImageButton mPreviousImageButton;
    private ImageButton mNextImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate(Bundle) called");

        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mPreviousImageButton = (ImageButton) findViewById(R.id.previous_image_button);
        mNextImageButton = (ImageButton) findViewById(R.id.next_image_button);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(view -> checkNext());
        updateQuestion();

        mTrueButton.setOnClickListener(view -> checkAnswer(true));
        mFalseButton.setOnClickListener(view -> checkAnswer(false));

        updateAnswerButton();

        mPreviousImageButton.setOnClickListener(view -> checkPrevious());
        mNextImageButton.setOnClickListener(view -> checkNext());
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
            Toast.makeText(this, R.string.has_answered_toast, Toast.LENGTH_LONG).show();
            return;
        }
        currentQuestion.setAnswered(true);
        updateAnswerButton();

        int messageResId = userPressedTrue == currentQuestion.isAnswerTrue()
                ? R.string.correct_toast
                : R.string.incorrect_toast;

        Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show();

    }

    private void checkPrevious() {
        mCurrentIndex = mCurrentIndex == 0 ? mQuestionBank.length - 1 : mCurrentIndex - 1;
        updateQuestion();
    }

    private void checkNext() {
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        updateQuestion();
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
}
