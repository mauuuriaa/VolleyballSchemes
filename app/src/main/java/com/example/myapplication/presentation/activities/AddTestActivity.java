package com.example.myapplication.presentation.activities;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

import com.example.myapplication.data.dto.QuestionDTO;
import com.example.myapplication.data.dto.TestDTO;
import com.example.myapplication.data.repository.InFileTestRepository;
import com.example.myapplication.data.repository.InFileTheoryRepository;
import com.example.myapplication.domain.entity.Theory;
import com.example.myapplication.data.adapter.AdapterTheoryRepository;
import com.example.myapplication.domain.usecase.TheoryUseCases;


import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class AddTestActivity extends AppCompatActivity {
    private EditText etTitle, etDescription, etPassingScore;
    private Spinner spinnerTheory;
    private Button btnSave, btnAddQuestion;
    private TextView tvQuestionsCount;

    private List<Theory> theoryList = new ArrayList<>();
    private List<QuestionDTO> questions = new ArrayList<>();
    private ArrayAdapter<String> theoryAdapter;
    private long selectedTheoryId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_test);

        Button btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());
        etTitle = findViewById(R.id.et_test_title);
        etDescription = findViewById(R.id.et_test_description);
        etPassingScore = findViewById(R.id.et_test_passing_score);
        spinnerTheory = findViewById(R.id.spinner_theory);
        btnSave = findViewById(R.id.btn_save_test);
        btnAddQuestion = findViewById(R.id.btn_add_question);
        tvQuestionsCount = findViewById(R.id.tv_questions_count);

        // Загрузка теорий
        InFileTheoryRepository theoryRepo = new InFileTheoryRepository(this);
        AdapterTheoryRepository adapterRepo = new AdapterTheoryRepository(theoryRepo);
        TheoryUseCases theoryUseCases = new TheoryUseCases(adapterRepo);
        theoryList = theoryUseCases.getAllTheories();

        List<String> theoryTitles = new ArrayList<>();
        for (Theory t : theoryList) {
            theoryTitles.add(t.getTitle());
        }
        theoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, theoryTitles);
        theoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTheory.setAdapter(theoryAdapter);

        spinnerTheory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTheoryId = theoryList.get(position).getId();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { selectedTheoryId = -1; }
        });

        btnAddQuestion.setOnClickListener(v -> {
            // Открываем диалог для добавления вопроса
            openAddQuestionDialog();
        });

        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String description = etDescription.getText().toString();
            int passingScore = 0;
            try {
                passingScore = Integer.parseInt(etPassingScore.getText().toString());
            } catch (Exception ignored) {}

            if (title.isEmpty() || description.isEmpty() || selectedTheoryId == -1) {
                Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (questions.isEmpty()) {
                Toast.makeText(this, "Добавьте хотя бы один вопрос!", Toast.LENGTH_SHORT).show();
                return;
            }

            TestDTO test = new TestDTO(System.currentTimeMillis(), title, description, selectedTheoryId, passingScore, new ArrayList<>(questions));
            InFileTestRepository repo = new InFileTestRepository(this);
            repo.saveTestDTO(test);

            Toast.makeText(this, "Тест добавлен!", Toast.LENGTH_SHORT).show();
            finish();
        });

        updateQuestionsCount();
    }

    private void openAddQuestionDialog() {

        AddQuestionDialog dialog = new AddQuestionDialog(this, questionDTO -> {
            questions.add(questionDTO);
            updateQuestionsCount();
        });
        dialog.show();
    }

    private void updateQuestionsCount() {
        tvQuestionsCount.setText("Вопросов добавлено: " + questions.size());
    }
}
