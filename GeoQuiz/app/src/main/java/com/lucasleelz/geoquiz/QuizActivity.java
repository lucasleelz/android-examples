package com.lucasleelz.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_quiz);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mPreviousImageButton = (ImageButton) findViewById(R.id.previous_image_button);
        mNextImageButton = (ImageButton) findViewById(R.id.next_image_button);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(view -> checkNext());
        updateQuestion();

        mTrueButton.setOnClickListener(view -> checkAnswer(true));
        mFalseButton.setOnClickListener(view -> checkAnswer(false));
        mPreviousImageButton.setOnClickListener(view -> checkPrevious());
        mNextImageButton.setOnClickListener(view -> checkNext());
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = userPressedTrue == answerIsTrue
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
}
