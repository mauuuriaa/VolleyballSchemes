package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.presentation.activities.TheoryListActivity;
import com.example.myapplication.presentation.activities.TestListActivity;
import com.example.myapplication.presentation.activities.ProgressActivity;
import com.example.myapplication.presentation.activities.AddTheoryActivity;
import com.example.myapplication.presentation.activities.AddTestActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnTheoryList;
    private Button btnTestList;
    private Button btnProgress;
    private Button btnAddTheory;
    private Button btnAddTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTheoryList = findViewById(R.id.btn_theory_list);
        btnTestList = findViewById(R.id.btn_test_list);
        btnProgress = findViewById(R.id.btn_progress);
        btnAddTheory = findViewById(R.id.btn_add_theory);
        btnAddTest = findViewById(R.id.btn_add_test);

        btnTheoryList.setOnClickListener(v -> {
            startActivity(new Intent(this, TheoryListActivity.class));
        });

        btnTestList.setOnClickListener(v -> {
            startActivity(new Intent(this, TestListActivity.class));
        });

        btnProgress.setOnClickListener(v -> {
            startActivity(new Intent(this, ProgressActivity.class));
        });

        btnAddTheory.setOnClickListener(v -> {
            startActivity(new Intent(this, AddTheoryActivity.class));
        });

        btnAddTest.setOnClickListener(v -> {
            startActivity(new Intent(this, AddTestActivity.class));
        });
    }
}