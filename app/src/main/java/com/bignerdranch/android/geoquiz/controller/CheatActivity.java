package com.bignerdranch.android.geoquiz.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bignerdranch.android.geoquiz.R;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE =
            CheatActivity.class.getPackage().getName() + ".answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN =
            CheatActivity.class.getPackage().getName() + ".answer_shown";

    private static final String KEY_ANSWER_SHOWN = "answer_shown";

    private TextView mAnswerTextView;
    private Button mShowAnswer;

    private boolean mAnswerIsTrue;
    private boolean mIsAnswerShown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);

        mShowAnswer = (Button) findViewById(R.id.show_answer_button);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAnswer();
            }
        });

        if (savedInstanceState != null) {
            mIsAnswerShown = savedInstanceState.getBoolean(KEY_ANSWER_SHOWN, false);
            if (mIsAnswerShown) {
                showAnswer();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(KEY_ANSWER_SHOWN, mIsAnswerShown);
    }

    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        return new Intent(packageContext, CheatActivity.class).putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        setResult(RESULT_OK, new Intent().putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown));
    }

    private void showAnswer() {
        int answerRes = R.string.false_button;
        if (mAnswerIsTrue) {
            answerRes = R.string.true_button;
        }
        mAnswerTextView.setText(answerRes);
        mIsAnswerShown = true;
        setAnswerShownResult(mIsAnswerShown);
    }
}
