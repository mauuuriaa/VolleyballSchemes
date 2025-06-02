package com.example.myapplication.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.domain.entity.Test;
import com.example.myapplication.domain.usecase.TestUseCases;
import com.example.myapplication.data.repository.InFileTestRepository;
import com.example.myapplication.data.adapter.AdapterTestRepository;
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

        initUseCases();
        initViews();
        loadTests();
    }

    private void initUseCases() {
        InFileTestRepository inFileRepo = new InFileTestRepository(this);
        AdapterTestRepository adapterRepo = new AdapterTestRepository(inFileRepo);
        testUseCases = new TestUseCases(adapterRepo);

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
