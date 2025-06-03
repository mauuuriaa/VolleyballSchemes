package com.example.myapplication.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.App;
import com.example.myapplication.domain.entity.Theory;
import com.example.myapplication.domain.usecase.TheoryUseCases;

public class TheoryDetailActivity extends AppCompatActivity {

    private TextView tvTitle, tvContent;
    private Button btnMarkCompleted, btnGoToTest;

    private TheoryUseCases theoryUseCases;
    private Theory theory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory_detail);

        Button btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());
        tvTitle = findViewById(R.id.tv_theory_detail_title);
        tvContent = findViewById(R.id.tv_theory_detail_content);
        btnMarkCompleted = findViewById(R.id.btn_mark_completed);
        btnGoToTest = findViewById(R.id.btn_go_to_test);

        long theoryId = getIntent().getLongExtra("theory_id", -1);

        // Получаем usecase только через App
        theoryUseCases = App.getInstance(this).getTheoryUseCases();

        theory = theoryUseCases.getTheoryById(theoryId);

        if (theory != null) {
            tvTitle.setText(theory.getTitle());
            tvContent.setText(theory.getContent());
            btnMarkCompleted.setEnabled(!theory.isCompleted());
            btnMarkCompleted.setText(theory.isCompleted() ? "Изучено" : "Отметить как изучено");
        } else {
            Toast.makeText(this, "Тема не найдена", Toast.LENGTH_SHORT).show();
            finish();
        }

        btnMarkCompleted.setOnClickListener(v -> {
            if (!theory.isCompleted()) {
                theoryUseCases.completeTheory(theory.getId());
                btnMarkCompleted.setEnabled(false);
                btnMarkCompleted.setText("Изучено");
                Toast.makeText(this, "Тема отмечена как изученная", Toast.LENGTH_SHORT).show();
            }
        });

        btnGoToTest.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestListActivity.class);
            intent.putExtra("theory_id", theory.getId());
            startActivity(intent);
        });
    }
}
