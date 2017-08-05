package com.lucasleelz.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE = "com.lucasleelz.geoquiz.CheatActivity.answer_id_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.lucasleelz.geoquiz.CheatActivity.answer_shown";

    private TextView mAnswerTextView;

    private Button mShowAnswerButton;

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

        boolean answerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
        mShowAnswerButton = (Button) findViewById(R.id.show_answer_button);
        mShowAnswerButton.setOnClickListener(view -> {
            int AnswerResId = answerIsTrue ? R.string.true_button : R.string.false_button;
            mAnswerTextView.setText(AnswerResId);
            setAnswerShownResult(true);
        });
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }
}
