package com.example.myapplication;

import android.content.Context;
import com.example.myapplication.data.repository.InFileTheoryRepository;
import com.example.myapplication.data.repository.InFileTestRepository;
import com.example.myapplication.data.adapter.AdapterTheoryRepository;
import com.example.myapplication.data.adapter.AdapterTestRepository;
import com.example.myapplication.domain.usecase.TheoryUseCases;
import com.example.myapplication.domain.usecase.TestUseCases;

public class App {
    private static App instance;

    private InFileTheoryRepository inFileTheoryRepository;
    private InFileTestRepository inFileTestRepository;
    private AdapterTheoryRepository adapterTheoryRepository;
    private AdapterTestRepository adapterTestRepository;
    private TheoryUseCases theoryUseCases;
    private TestUseCases testUseCases;

    private App(Context context) {
        // Data-слой
        inFileTheoryRepository = new InFileTheoryRepository(context);
        inFileTestRepository = new InFileTestRepository(context);

        // Adapter-слой
        adapterTheoryRepository = new AdapterTheoryRepository(inFileTheoryRepository);
        adapterTestRepository = new AdapterTestRepository(inFileTestRepository);

        // UseCase-слой
        theoryUseCases = new TheoryUseCases(adapterTheoryRepository);
        testUseCases = new TestUseCases(adapterTestRepository);
    }

    public static App getInstance(Context context) {
        if (instance == null) {
            instance = new App(context.getApplicationContext());
        }
        return instance;
    }

    public TheoryUseCases getTheoryUseCases() {
        return theoryUseCases;
    }

    public TestUseCases getTestUseCases() {
        return testUseCases;
    }

    // Если нужно работать с data-слоем напрямую
    public InFileTheoryRepository getInFileTheoryRepository() {
        return inFileTheoryRepository;
    }
}
