package com.example.myapplication.domain.repository;

import com.example.myapplication.domain.entity.Test;
import com.example.myapplication.domain.entity.TestResult;
import java.util.List;

public interface TestRepository {
    List<Test> getAllTests();
    Test getTestById(long id);
    List<Test> getTestsByTheoryId(long theoryId);
    void saveTest(Test test);
    void updateTest(Test test);
    void deleteTest(long id);
    void saveTestResult(TestResult result);
    List<TestResult> getTestResults();
    TestResult getTestResultById(long id);
}
