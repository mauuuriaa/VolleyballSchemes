package com.example.myapplication.presentation.activities;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.App;
import com.example.myapplication.domain.entity.Test;
import com.example.myapplication.domain.entity.Question;
import com.example.myapplication.domain.entity.Answer;
import com.example.myapplication.domain.entity.TestResult;
import com.example.myapplication.domain.usecase.TestUseCases;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestPassingActivity extends AppCompatActivity {
    private TextView tvQuestionNumber, tvQuestionText;
    private RadioGroup rgAnswers;
    private Button btnNext;

    private List<Question> questions = new ArrayList<>();
    private int currentQuestion = 0;
    private List<Integer> selectedAnswers = new ArrayList<>();
    private Test test;
    private int correctAnswers = 0;
    private int passingScore = 0;
    private long testId;

    private TestUseCases testUseCases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_passing);

        tvQuestionNumber = findViewById(R.id.tv_question_number);
        tvQuestionText = findViewById(R.id.tv_question_text);
        rgAnswers = findViewById(R.id.rg_answers);
        btnNext = findViewById(R.id.btn_next);

        testId = getIntent().getLongExtra("test_id", -1);

        // Получаем usecase только через App
        testUseCases = App.getInstance(this).getTestUseCases();

        // Получаем тест через usecase
        test = testUseCases.getTestById(testId);

        if (test == null) {
            Toast.makeText(this, "Тест не найден", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        questions = test.getQuestions();
        passingScore = test.getPassingScore();

        if (questions == null || questions.isEmpty()) {
            Toast.makeText(this, "В этом тесте нет вопросов", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        for (int i = 0; i < questions.size(); i++) {
            selectedAnswers.add(-1);
        }

        showQuestion();

        btnNext.setOnClickListener(v -> {
            int checkedId = rgAnswers.getCheckedRadioButtonId();
            if (checkedId == -1) {
                Toast.makeText(this, "Выберите ответ!", Toast.LENGTH_SHORT).show();
                return;
            }

            int selectedIndex = rgAnswers.indexOfChild(findViewById(checkedId));
            selectedAnswers.set(currentQuestion, selectedIndex);

            if (currentQuestion < questions.size() - 1) {
                currentQuestion++;
                showQuestion();
            } else {
                checkResult();
            }
        });
    }

    private void showQuestion() {
        Question question = questions.get(currentQuestion);
        tvQuestionNumber.setText("Вопрос " + (currentQuestion + 1) + " из " + questions.size());
        tvQuestionText.setText(question.getText());

        rgAnswers.removeAllViews();
        List<Answer> answers = question.getAnswers();
        for (int i = 0; i < answers.size(); i++) {
            RadioButton rb = new RadioButton(this);
            rb.setText(answers.get(i).getText());
            rb.setId(i);
            rgAnswers.addView(rb);
        }

        // Восстановить выбранный ответ, если уже был выбран
        int prevSelected = selectedAnswers.get(currentQuestion);
        if (prevSelected != -1 && prevSelected < rgAnswers.getChildCount()) {
            ((RadioButton) rgAnswers.getChildAt(prevSelected)).setChecked(true);
        }

        btnNext.setText(currentQuestion == questions.size() - 1 ? "Завершить" : "Далее");
    }

    private void checkResult() {
        correctAnswers = 0;
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            int selected = selectedAnswers.get(i);
            if (selected != -1 && question.getAnswers().get(selected).isCorrect()) {
                correctAnswers++;
            }
        }

        boolean passed = correctAnswers >= passingScore;
        String message = "Правильных ответов: " + correctAnswers + " из " + questions.size() +
                "\n" + (passed ? "Тест пройден!" : "Тест не пройден.");

        // СОХРАНЕНИЕ РЕЗУЛЬТАТА через usecase
        TestResult result = new TestResult(
                System.currentTimeMillis(),
                testId,
                correctAnswers,
                questions.size(),
                passed,
                new Date()
        );
        testUseCases.submitTestResult(result);

        new android.app.AlertDialog.Builder(this)
                .setTitle("Результат")
                .setMessage(message)
                .setPositiveButton("ОК", (dialog, which) -> finish())
                .setCancelable(false)
                .show();
    }
}
