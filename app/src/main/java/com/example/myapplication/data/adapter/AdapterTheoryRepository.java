package com.example.myapplication.data.adapter;

import com.example.myapplication.data.dto.TheoryDTO;
import com.example.myapplication.data.repository.InFileTheoryRepository;
import com.example.myapplication.domain.entity.Theory;
import com.example.myapplication.domain.repository.TheoryRepository;

import java.util.ArrayList;
import java.util.List;

public class AdapterTheoryRepository implements TheoryRepository {
    private final InFileTheoryRepository dataRepo;

    public AdapterTheoryRepository(InFileTheoryRepository dataRepo) {
        this.dataRepo = dataRepo;
    }

    @Override
    public List<Theory> getAllTheories() {
        List<TheoryDTO> dtos = dataRepo.getAllTheoryDTOs();
        List<Theory> result = new ArrayList<>();
        for (TheoryDTO dto : dtos) result.add(dto.toTheory());
        return result;
    }

    @Override
    public Theory getTheoryById(long id) {
        TheoryDTO dto = dataRepo.getTheoryDTOById(id);
        return dto != null ? dto.toTheory() : null;
    }

    @Override
    public void saveTheory(Theory theory) {
        dataRepo.saveTheoryDTO(new TheoryDTO(theory));
    }

    @Override
    public void updateTheory(Theory theory) {
        dataRepo.updateTheoryDTO(new TheoryDTO(theory));
    }

    @Override
    public void deleteTheory(long id) {
        dataRepo.deleteTheoryDTO(id);
    }

    @Override
    public List<Theory> getCompletedTheories() {
        List<TheoryDTO> dtos = dataRepo.getAllTheoryDTOs();
        List<Theory> result = new ArrayList<>();
        for (TheoryDTO dto : dtos) {
            if (dto.isCompleted()) result.add(dto.toTheory());
        }
        return result;
    }

    @Override
    public void markTheoryAsCompleted(long theoryId) {
        TheoryDTO dto = dataRepo.getTheoryDTOById(theoryId);
        if (dto != null && !dto.isCompleted()) {
            dto.setCompleted(true);
            dataRepo.updateTheoryDTO(dto);
        }
    }
}
