package com.bignerdranch.android.geoquiz.controller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.bignerdranch.android.geoquiz.R;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE =
            CheatActivity.class.getPackage().getName() + ".answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN =
            CheatActivity.class.getPackage().getName() + ".answer_shown";

    private TextView mAnswerTextView;
    private Button mShowAnswer;

    private boolean mAnswerIsTrue;

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
                int answerRes = R.string.false_button;
                if (mAnswerIsTrue) {
                    answerRes = R.string.true_button;
                }
                mAnswerTextView.setText(answerRes);
                setAnswerShownResult(true);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    int cx = mShowAnswer.getWidth() / 2;
                    int cy = mShowAnswer.getHeight() / 2;
                    float radius = mShowAnswer.getWidth();
                    Animator animator =
                            ViewAnimationUtils.createCircularReveal(mShowAnswer, cx, cy, radius, 0);
                    animator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);

                            mAnswerTextView.setVisibility(View.VISIBLE);
                            mShowAnswer.setVisibility(View.INVISIBLE);
                        }
                    });
                    animator.start();
                } else {
                    mAnswerTextView.setVisibility(View.VISIBLE);
                    mShowAnswer.setVisibility(View.INVISIBLE);
                }
            }
        });
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
}
