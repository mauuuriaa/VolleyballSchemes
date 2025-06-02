package com.example.myapplication.presentation.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data.adapter.AdapterTheoryRepository;
import com.example.myapplication.data.repository.InFileTheoryRepository;
import com.example.myapplication.domain.entity.Theory;
import com.example.myapplication.domain.usecase.TheoryUseCases;

public class AddTheoryActivity extends AppCompatActivity {
    private EditText etTitle, etContent, etCategoryId;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_theory);

        Button btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());
        etTitle = findViewById(R.id.et_theory_title);
        etContent = findViewById(R.id.et_theory_content);
        //etCategoryId = findViewById(R.id.et_theory_category_id);

        btnSave = findViewById(R.id.btn_save_theory);

        // Создаем репозиторий и адаптер
        InFileTheoryRepository inFileRepo = new InFileTheoryRepository(this);
        AdapterTheoryRepository adapterRepo = new AdapterTheoryRepository(inFileRepo);
        TheoryUseCases useCases = new TheoryUseCases(adapterRepo);

        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String content = etContent.getText().toString();
            int categoryId = 0;
            try {
                categoryId = Integer.parseInt(etCategoryId.getText().toString());
            } catch (Exception ignored) {}

            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_SHORT).show();
                return;
            }

            long newId = inFileRepo.generateTheoryId();
            Theory theory = new Theory(newId, title, content, categoryId);

            useCases.addTheory(theory);

            Toast.makeText(this, "Теория добавлена!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
