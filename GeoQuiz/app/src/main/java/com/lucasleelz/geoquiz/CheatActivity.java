package com.lucasleelz.geoquiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE = "com.lucasleelz.geoquiz.CheatActivity.answer_id_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.lucasleelz.geoquiz.CheatActivity.answer_shown";
    private static final String ANSWER_SHOWN = "answer_shown";

    private TextView mAnswerTextView;

    private Button mShowAnswerButton;
    private boolean mAnswerShow;

    public static Intent createIntent(Context packageContext, boolean answerIsTrue) {
        Intent result = new Intent(packageContext, CheatActivity.class);
        result.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return result;
    }

    public static boolean hasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerTextView = findViewById(R.id.answer_text_view);
        mShowAnswerButton = findViewById(R.id.show_answer_button);

        boolean answerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        if (savedInstanceState != null) {
            mAnswerShow = savedInstanceState.getBoolean(ANSWER_SHOWN, false);
            updateAnswerTextView(answerIsTrue);
            setAnswerShownResult();
        }

        mShowAnswerButton.setOnClickListener(view -> {
            mAnswerShow = true;
            updateAnswerTextView(answerIsTrue);
            setAnswerShownResult();

            int cx = mShowAnswerButton.getWidth() / 2;
            int cy = mShowAnswerButton.getHeight() / 2;
            float radius = mShowAnswerButton.getWidth();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Animator animator = ViewAnimationUtils.createCircularReveal(mShowAnswerButton, cx, cy, radius, 0);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        inVisibilityShowAnswerButton();
                    }
                });
                animator.start();
            } else {
                inVisibilityShowAnswerButton();
            }

        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(ANSWER_SHOWN, mAnswerShow);
    }

    private void setAnswerShownResult() {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, mAnswerShow);
        setResult(RESULT_OK, data);
    }

    private void updateAnswerTextView(boolean answerIsTrue) {
        if (!mAnswerShow) {
            return;
        }
        int AnswerResId = answerIsTrue ? R.string.true_button : R.string.false_button;
        mAnswerTextView.setText(AnswerResId);
    }

    private void inVisibilityShowAnswerButton() {
        mShowAnswerButton.setVisibility(View.INVISIBLE);
    }
}
