package com.example.myapplication.domain.entity;

import java.util.List;

public class Question {
    private long id;
    private String text;
    private String imageUrl;
    private List<Answer> answers;
    private long testId;

    public Question(long id, String text, String imageUrl, long testId, List<Answer> answers) {
        this.id = id;
        this.text = text;
        this.imageUrl = imageUrl;
        this.testId = testId;
        this.answers = answers;
    }

    // Getters и Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public List<Answer> getAnswers() { return answers; }
    public void setAnswers(List<Answer> answers) { this.answers = answers; }

    public long getTestId() { return testId; }
    public void setTestId(long testId) { this.testId = testId; }
}
