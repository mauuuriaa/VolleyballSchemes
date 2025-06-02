package com.example.myapplication.domain.usecase;

import com.example.myapplication.domain.entity.Test;
import com.example.myapplication.domain.entity.TestResult;
import com.example.myapplication.domain.repository.TestRepository;
import java.util.List;

public class TestUseCases {
    private TestRepository testRepository;

    public TestUseCases(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<Test> getAllTests() {
        return testRepository.getAllTests();
    }

    public Test getTestById(long id) {
        return testRepository.getTestById(id);
    }

    public List<Test> getTestsByTheoryId(long theoryId) {
        return testRepository.getTestsByTheoryId(theoryId);
    }

    public void addTest(Test test) {
        testRepository.saveTest(test);
    }

    public void submitTestResult(TestResult result) {
        testRepository.saveTestResult(result);
    }

    public List<TestResult> getTestResults() {
        return testRepository.getTestResults();
    }

    public double calculateProgress() {
        List<TestResult> results = testRepository.getTestResults();
        if (results.isEmpty()) return 0.0;

        long passedTests = results.stream()
                .mapToLong(result -> result.isPassed() ? 1 : 0)
                .sum();

        return (double) passedTests / results.size() * 100;
    }
}
