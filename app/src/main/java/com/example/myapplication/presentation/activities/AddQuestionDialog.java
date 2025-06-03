package com.example.myapplication.presentation.activities;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.*;
import com.example.myapplication.R;
import com.example.myapplication.domain.entity.Answer;
import com.example.myapplication.domain.entity.Question;

import java.util.ArrayList;
import java.util.List;

public class AddQuestionDialog extends Dialog {
    private EditText etQuestionText;
    private EditText etAnswerText;
    private CheckBox cbIsCorrect;
    private Button btnAddAnswer, btnSaveQuestion;
    private ListView lvAnswers;

    private List<Answer> answers = new ArrayList<>();
    private ArrayAdapter<String> answersAdapter;
    private List<String> answersStrings = new ArrayList<>();

    private OnQuestionAddedListener listener;

    public interface OnQuestionAddedListener {
        void onQuestionAdded(Question question);
    }

    public AddQuestionDialog(Context context, OnQuestionAddedListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_question);

        etQuestionText = findViewById(R.id.et_question_text);
        etAnswerText = findViewById(R.id.et_answer_text);
        cbIsCorrect = findViewById(R.id.cb_is_correct);
        btnAddAnswer = findViewById(R.id.btn_add_answer);
        btnSaveQuestion = findViewById(R.id.btn_save_question);
        lvAnswers = findViewById(R.id.lv_answers);

        answersAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, answersStrings);
        lvAnswers.setAdapter(answersAdapter);

        btnAddAnswer.setOnClickListener(v -> {
            String answerText = etAnswerText.getText().toString();
            boolean isCorrect = cbIsCorrect.isChecked();
            if (!answerText.isEmpty()) {
                Answer answer = new Answer(System.currentTimeMillis(), answerText, isCorrect, 0L);
                answers.add(answer);
                answersStrings.add(answerText + (isCorrect ? " (верный)" : ""));
                answersAdapter.notifyDataSetChanged();
                etAnswerText.setText("");
                cbIsCorrect.setChecked(false);
            }
        });

        btnSaveQuestion.setOnClickListener(v -> {
            String questionText = etQuestionText.getText().toString();

            if (questionText.isEmpty() || answers.isEmpty()) {
                Toast.makeText(getContext(), "Введите текст вопроса и добавьте ответы!", Toast.LENGTH_SHORT).show();
                return;
            }
            Question question = new Question(System.currentTimeMillis(), questionText, "", 0L, new ArrayList<>(answers));
            listener.onQuestionAdded(question);
            dismiss();
        });

        if (getWindow() != null) {
            getWindow().setLayout(
                    (int)(getContext().getResources().getDisplayMetrics().widthPixels * 0.95),
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
        }
    }
}
