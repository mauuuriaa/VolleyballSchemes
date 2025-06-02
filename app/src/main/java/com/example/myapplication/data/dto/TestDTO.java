package com.example.myapplication.data.dto;

import com.example.myapplication.domain.entity.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String title;
    private String description;
    private long theoryId;
    private int passingScore;
    private List<QuestionDTO> questions = new ArrayList<>();

    public TestDTO() {}

    public TestDTO(long id, String title, String description, long theoryId, int passingScore, List<QuestionDTO> questions) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.theoryId = theoryId;
        this.passingScore = passingScore;
        this.questions = questions;
    }

    // Конструктор из Entity
    public TestDTO(Test test) {
        this.id = test.getId();
        this.title = test.getTitle();
        this.description = test.getDescription();
        this.theoryId = test.getTheoryId();
        this.passingScore = test.getPassingScore();

        this.questions = new ArrayList<>();

    }

    // Преобразование в Entity
    public Test toTest() {
        return new Test(this.id, this.title, this.description, this.theoryId, this.passingScore);
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public long getTheoryId() { return theoryId; }
    public void setTheoryId(long theoryId) { this.theoryId = theoryId; }

    public int getPassingScore() { return passingScore; }
    public void setPassingScore(int passingScore) { this.passingScore = passingScore; }

    public List<QuestionDTO> getQuestions() { return questions; }
    public void setQuestions(List<QuestionDTO> questions) { this.questions = questions; }
}
