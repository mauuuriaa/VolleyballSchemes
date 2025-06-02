package com.example.myapplication.presentation.activities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import com.example.myapplication.R;
import com.example.myapplication.data.repository.InFileTestRepository;
import com.example.myapplication.domain.entity.Test;
import com.example.myapplication.domain.usecase.TestUseCases;
import com.example.myapplication.data.adapter.AdapterTestRepository;

public class TestDetailActivity extends AppCompatActivity {
    private TextView tvTitle, tvDescription, tvPassingScore;
    private Button btnStartTest;

    private TestUseCases testUseCases;
    private Test test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_detail);

        Button btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());

        tvTitle = findViewById(R.id.tv_test_detail_title);
        tvDescription = findViewById(R.id.tv_test_detail_description);
        tvPassingScore = findViewById(R.id.tv_test_detail_passing_score);
        btnStartTest = findViewById(R.id.btn_start_test);

        long testId = getIntent().getLongExtra("test_id", -1);
        InFileTestRepository inFileRepo = new InFileTestRepository(this);
        AdapterTestRepository adapterRepo = new AdapterTestRepository(inFileRepo);
        testUseCases = new TestUseCases(adapterRepo);

        test = testUseCases.getTestById(testId);

        if (test != null) {
            tvTitle.setText(test.getTitle());
            tvDescription.setText(test.getDescription());
            tvPassingScore.setText("Проходной балл: " + test.getPassingScore());
        } else {
            Toast.makeText(this, "Тест не найден", Toast.LENGTH_SHORT).show();
            finish();
        }

        btnStartTest.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestPassingActivity.class);
            intent.putExtra("test_id", test.getId());
            startActivity(intent);
        });
    }
}
