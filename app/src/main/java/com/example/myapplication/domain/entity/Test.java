package com.example.myapplication.domain.entity;

import java.util.List;

public class Test {
    private long id;
    private String title;
    private String description;
    private long theoryId;
    private List<Question> questions;
    private int passingScore;

    public Test(long id, String title, String description, long theoryId, int passingScore) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.theoryId = theoryId;
        this.passingScore = passingScore;
    }

    // Getters и Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public long getTheoryId() { return theoryId; }
    public void setTheoryId(long theoryId) { this.theoryId = theoryId; }

    public List<Question> getQuestions() { return questions; }
    public void setQuestions(List<Question> questions) { this.questions = questions; }

    public int getPassingScore() { return passingScore; }
    public void setPassingScore(int passingScore) { this.passingScore = passingScore; }
}
