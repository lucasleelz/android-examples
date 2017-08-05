package com.lucasleelz.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE = "com.lucasleelz.geoquiz.CheatActivity.answer_id_true";

    private TextView mAnswerTextView;

    private Button mShowAnswerButton;

    public static Intent createIntent(Context packageContext, boolean answerIsTrue) {
        Intent result = new Intent(packageContext, CheatActivity.class);
        result.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        boolean answerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
        mShowAnswerButton = (Button) findViewById(R.id.show_answer_button);
        mShowAnswerButton.setOnClickListener(view -> mAnswerTextView.setText(
                answerIsTrue
                        ? R.string.true_button
                        : R.string.false_button));
    }
}
