package com.example.myapplication.data.repository;

import android.content.Context;
import com.example.myapplication.data.dto.TestDTO;
import com.example.myapplication.data.dto.TestResultDTO;
import com.example.myapplication.data.storage.InFileStorage;
import java.util.List;

public class InFileTestRepository {
    private InFileStorage<TestDTO> storage;
    private InFileStorage<TestResultDTO> resultStorage;
    private InFileIDGenerator idGenerator;

    public InFileTestRepository(Context context) {
        this.storage = new InFileStorage<>(context, "tests.dat");
        this.resultStorage = new InFileStorage<>(context, "test_results.dat");
        this.idGenerator = new InFileIDGenerator(context, "test_id.dat");
    }

    public long generateTestId() {
        return idGenerator.generate();
    }

    // Методы для TestDTO
    public List<TestDTO> getAllTestDTOs() {
        return storage.loadList();
    }

    public TestDTO getTestDTOById(long id) {
        for (TestDTO dto : storage.loadList()) {
            if (dto.getId() == id) return dto;
        }
        return null;
    }

    public List<TestDTO> getTestDTOsByTheoryId(long theoryId) {
        List<TestDTO> dtos = storage.loadList();
        List<TestDTO> result = new java.util.ArrayList<>();
        for (TestDTO dto : dtos) {
            if (dto.getTheoryId() == theoryId) {
                result.add(dto);
            }
        }
        return result;
    }

    public void saveTestDTO(TestDTO testDTO) {
        List<TestDTO> dtos = storage.loadList();
        dtos.add(testDTO);
        storage.saveList(dtos);
    }

    public void updateTestDTO(TestDTO testDTO) {
        List<TestDTO> dtos = storage.loadList();
        for (int i = 0; i < dtos.size(); i++) {
            if (dtos.get(i).getId() == testDTO.getId()) {
                dtos.set(i, testDTO);
                break;
            }
        }
        storage.saveList(dtos);
    }

    public void deleteTestDTO(long id) {
        List<TestDTO> dtos = storage.loadList();
        for (int i = 0; i < dtos.size(); i++) {
            if (dtos.get(i).getId() == id) {
                dtos.remove(i);
                break;
            }
        }
        storage.saveList(dtos);
    }

    // Методы для TestResultDTO
    public List<TestResultDTO> getAllTestResultDTOs() {
        return resultStorage.loadList();
    }

    public TestResultDTO getTestResultDTOById(long id) {
        for (TestResultDTO dto : resultStorage.loadList()) {
            if (dto.getId() == id) return dto;
        }
        return null;
    }

    public void saveTestResultDTO(TestResultDTO resultDTO) {
        List<TestResultDTO> results = resultStorage.loadList();
        results.add(resultDTO);
        resultStorage.saveList(results);
    }

    public void updateTestResultDTO(TestResultDTO resultDTO) {
        List<TestResultDTO> results = resultStorage.loadList();
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).getId() == resultDTO.getId()) {
                results.set(i, resultDTO);
                break;
            }
        }
        resultStorage.saveList(results);
    }

    public void deleteTestResultDTO(long id) {
        List<TestResultDTO> results = resultStorage.loadList();
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).getId() == id) {
                results.remove(i);
                break;
            }
        }
        resultStorage.saveList(results);
    }
}
