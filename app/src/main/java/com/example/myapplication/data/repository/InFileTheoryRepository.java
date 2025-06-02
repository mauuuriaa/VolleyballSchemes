package com.example.myapplication.data.repository;

import android.content.Context;
import com.example.myapplication.data.dto.TheoryDTO;
import com.example.myapplication.data.storage.InFileStorage;
import java.util.ArrayList;
import java.util.List;

public class InFileTheoryRepository {
    private InFileStorage<TheoryDTO> storage;
    private InFileIDGenerator idGenerator;

    public InFileTheoryRepository(Context context) {
        this.storage = new InFileStorage<>(context, "theories.dat");
        this.idGenerator = new InFileIDGenerator(context, "theory_id.dat");
    }

    public long generateTheoryId() {
        return idGenerator.generate();
    }

    public List<TheoryDTO> getAllTheoryDTOs() {
        return storage.loadList();
    }

    public TheoryDTO getTheoryDTOById(long id) {
        for (TheoryDTO dto : storage.loadList()) {
            if (dto.getId() == id) return dto;
        }
        return null;
    }

    public void saveTheoryDTO(TheoryDTO theoryDTO) {
        List<TheoryDTO> dtos = storage.loadList();
        dtos.add(theoryDTO);
        storage.saveList(dtos);
    }

    public void updateTheoryDTO(TheoryDTO theoryDTO) {
        List<TheoryDTO> dtos = storage.loadList();
        for (int i = 0; i < dtos.size(); i++) {
            if (dtos.get(i).getId() == theoryDTO.getId()) {
                dtos.set(i, theoryDTO);
                break;
            }
        }
        storage.saveList(dtos);
    }

    public void deleteTheoryDTO(long id) {
        List<TheoryDTO> dtos = storage.loadList();
        for (int i = 0; i < dtos.size(); i++) {
            if (dtos.get(i).getId() == id) {
                dtos.remove(i);
                break;
            }
        }
        storage.saveList(dtos);
    }
}
