package com.example.myapplication.presentation.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.App;
import com.example.myapplication.domain.usecase.TestUseCases;

public class ProgressActivity extends AppCompatActivity {
    private TextView tvProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        tvProgress = findViewById(R.id.tv_progress);

        // Получаем usecase только через App
        TestUseCases useCases = App.getInstance(this).getTestUseCases();

        double progress = useCases.calculateProgress();
        tvProgress.setText("Ваш прогресс: " + String.format("%.1f", progress) + " %");
    }
}
