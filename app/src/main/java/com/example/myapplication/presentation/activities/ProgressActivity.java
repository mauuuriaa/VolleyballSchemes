package com.example.myapplication.presentation.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.data.repository.InFileTestRepository;
import com.example.myapplication.data.adapter.AdapterTestRepository;
import com.example.myapplication.domain.usecase.TestUseCases;

public class ProgressActivity extends AppCompatActivity {
    private TextView tvProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        tvProgress = findViewById(R.id.tv_progress);

        InFileTestRepository inFileRepo = new InFileTestRepository(this);
        AdapterTestRepository adapterRepo = new AdapterTestRepository(inFileRepo);
        TestUseCases useCases = new TestUseCases(adapterRepo);

        double progress = useCases.calculateProgress();
        tvProgress.setText("Ваш прогресс: " + String.format("%.1f", progress) + " %");
    }
}
