package com.example.myapplication.domain.repository;

import com.example.myapplication.domain.entity.Theory;
import java.util.List;

public interface TheoryRepository {
    List<Theory> getAllTheories();
    Theory getTheoryById(long id);
    void saveTheory(Theory theory);
    void updateTheory(Theory theory);
    void deleteTheory(long id);
    List<Theory> getCompletedTheories();
    void markTheoryAsCompleted(long theoryId);
}
