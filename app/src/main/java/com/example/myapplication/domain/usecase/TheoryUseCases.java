package com.example.myapplication.domain.usecase;

import com.example.myapplication.domain.entity.Theory;
import com.example.myapplication.domain.repository.TheoryRepository;
import java.util.List;

public class TheoryUseCases {
    private TheoryRepository theoryRepository;

    public TheoryUseCases(TheoryRepository theoryRepository) {
        this.theoryRepository = theoryRepository;
    }

    public List<Theory> getAllTheories() {
        return theoryRepository.getAllTheories();
    }

    public Theory getTheoryById(long id) {
        return theoryRepository.getTheoryById(id);
    }

    public void addTheory(Theory theory) {
        theoryRepository.saveTheory(theory);
    }

    public void completeTheory(long theoryId) {
        theoryRepository.markTheoryAsCompleted(theoryId);
    }

    public List<Theory> getCompletedTheories() {
        return theoryRepository.getCompletedTheories();
    }
}
