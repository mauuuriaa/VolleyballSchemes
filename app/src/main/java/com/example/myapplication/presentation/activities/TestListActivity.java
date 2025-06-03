package com.example.myapplication.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.App;
import com.example.myapplication.domain.entity.Test;
import com.example.myapplication.domain.usecase.TestUseCases;
import com.example.myapplication.presentation.adapters.TestAdapter;

import java.util.List;

public class TestListActivity extends AppCompatActivity implements TestAdapter.OnTestClickListener {
    private RecyclerView recyclerView;
    private TestAdapter adapter;
    private TestUseCases testUseCases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_list);

        Button btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());

        // Получаем usecase только через App
        testUseCases = App.getInstance(this).getTestUseCases();

        initViews();
        loadTests();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recycler_tests);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TestAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private void loadTests() {
        List<Test> tests = testUseCases.getAllTests();
        adapter.setTests(tests);
    }

    @Override
    public void onTestClick(Test test) {
        Intent intent = new Intent(this, TestDetailActivity.class);
        intent.putExtra("test_id", test.getId());
        startActivity(intent);
    }
}
