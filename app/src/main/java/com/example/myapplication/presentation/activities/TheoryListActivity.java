package com.example.myapplication.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.data.adapter.AdapterTheoryRepository;
import com.example.myapplication.data.repository.InFileTheoryRepository;
import com.example.myapplication.domain.entity.Theory;
import com.example.myapplication.domain.usecase.TheoryUseCases;
import com.example.myapplication.presentation.adapters.TheoryAdapter;

import java.util.List;

public class TheoryListActivity extends AppCompatActivity implements TheoryAdapter.OnTheoryClickListener {
    private RecyclerView recyclerView;
    private TheoryAdapter adapter;
    private TheoryUseCases theoryUseCases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory_list);

        Button btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());

        initUseCases();
        initViews();
        loadTheories();
    }

    private void initUseCases() {
        InFileTheoryRepository inFileRepo = new InFileTheoryRepository(this);
        AdapterTheoryRepository adapterRepo = new AdapterTheoryRepository(inFileRepo);
        theoryUseCases = new TheoryUseCases(adapterRepo);
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recycler_theories);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new TheoryAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private void loadTheories() {
        List<Theory> theories = theoryUseCases.getAllTheories();
        adapter.setTheories(theories);
    }

    @Override
    public void onTheoryClick(Theory theory) {
        Intent intent = new Intent(this, TheoryDetailActivity.class);
        intent.putExtra("theory_id", theory.getId());
        startActivity(intent);
    }
}
