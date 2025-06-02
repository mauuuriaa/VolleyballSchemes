package com.example.myapplication.data.adapter;

import com.example.myapplication.data.dto.TestDTO;
import com.example.myapplication.data.dto.TestResultDTO;
import com.example.myapplication.data.repository.InFileTestRepository;
import com.example.myapplication.domain.entity.Test;
import com.example.myapplication.domain.entity.TestResult;
import com.example.myapplication.domain.repository.TestRepository;

import java.util.ArrayList;
import java.util.List;

public class AdapterTestRepository implements TestRepository {
    private final InFileTestRepository dataRepo;

    public AdapterTestRepository(InFileTestRepository dataRepo) {
        this.dataRepo = dataRepo;
    }

    @Override
    public List<Test> getAllTests() {
        List<TestDTO> dtos = dataRepo.getAllTestDTOs();
        List<Test> result = new ArrayList<>();
        for (TestDTO dto : dtos) result.add(dto.toTest());
        return result;
    }

    @Override
    public Test getTestById(long id) {
        TestDTO dto = dataRepo.getTestDTOById(id);
        return dto != null ? dto.toTest() : null;
    }

    @Override
    public List<Test> getTestsByTheoryId(long theoryId) {
        List<TestDTO> dtos = dataRepo.getTestDTOsByTheoryId(theoryId);
        List<Test> result = new ArrayList<>();
        for (TestDTO dto : dtos) result.add(dto.toTest());
        return result;
    }

    @Override
    public void saveTest(Test test) {
        dataRepo.saveTestDTO(new TestDTO(test));
    }

    @Override
    public void updateTest(Test test) {
        dataRepo.updateTestDTO(new TestDTO(test));
    }

    @Override
    public void deleteTest(long id) {
        dataRepo.deleteTestDTO(id);
    }

    @Override
    public void saveTestResult(TestResult result) {
        dataRepo.saveTestResultDTO(new TestResultDTO(result));
    }

    @Override
    public List<TestResult> getTestResults() {
        List<TestResultDTO> dtos = dataRepo.getAllTestResultDTOs();
        List<TestResult> results = new ArrayList<>();
        for (TestResultDTO dto : dtos) results.add(dto.toTestResult());
        return results;
    }

    @Override
    public TestResult getTestResultById(long id) {
        TestResultDTO dto = dataRepo.getTestResultDTOById(id);
        return dto != null ? dto.toTestResult() : null;
    }
}
