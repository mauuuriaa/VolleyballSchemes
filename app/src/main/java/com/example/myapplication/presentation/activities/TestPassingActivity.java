package com.example.myapplication.presentation.activities;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.data.dto.AnswerDTO;
import com.example.myapplication.data.dto.QuestionDTO;
import com.example.myapplication.data.dto.TestDTO;
import com.example.myapplication.data.adapter.AdapterTestRepository;
import com.example.myapplication.data.repository.InFileTestRepository;
import com.example.myapplication.domain.entity.TestResult;
import com.example.myapplication.domain.usecase.TestUseCases;

import java.util.ArrayList;
import java.util.List;

public class TestPassingActivity extends AppCompatActivity {
    private TextView tvQuestionNumber, tvQuestionText;
    private RadioGroup rgAnswers;
    private Button btnNext;

    private List<QuestionDTO> questions = new ArrayList<>();
    private int currentQuestion = 0;
    private List<Integer> selectedAnswers = new ArrayList<>();
    private TestDTO testDTO;
    private int correctAnswers = 0;
    private int passingScore = 0;
    private long testId;

    private InFileTestRepository inFileRepo;
    private AdapterTestRepository adapterRepo;
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

        // Создаем репозитории и usecase
        inFileRepo = new InFileTestRepository(this);
        adapterRepo = new AdapterTestRepository(inFileRepo);
        testUseCases = new TestUseCases(adapterRepo);

        // Получаем тест через DTO-репозиторий
        testDTO = inFileRepo.getTestDTOById(testId);
        if (testDTO == null) {
            Toast.makeText(this, "Тест не найден", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        questions = testDTO.getQuestions();
        passingScore = testDTO.getPassingScore();

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
        QuestionDTO question = questions.get(currentQuestion);
        tvQuestionNumber.setText("Вопрос " + (currentQuestion + 1) + " из " + questions.size());
        tvQuestionText.setText(question.getText());

        rgAnswers.removeAllViews();
        List<AnswerDTO> answers = question.getAnswers();
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
            QuestionDTO question = questions.get(i);
            int selected = selectedAnswers.get(i);
            if (selected != -1 && question.getAnswers().get(selected).isCorrect()) {
                correctAnswers++;
            }
        }

        boolean passed = correctAnswers >= passingScore;
        String message = "Правильных ответов: " + correctAnswers + " из " + questions.size() +
                "\n" + (passed ? "Тест пройден!" : "Тест не пройден.");

        // СОХРАНЕНИЕ РЕЗУЛЬТАТА через usecase и adapter
        TestResult result = new TestResult(
                System.currentTimeMillis(),
                testId,
                correctAnswers,
                questions.size(),
                passed,
                new java.util.Date()
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
