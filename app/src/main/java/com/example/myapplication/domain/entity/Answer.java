package com.example.myapplication.domain.entity;

public class Answer {
    private long id;
    private String text;
    private boolean isCorrect;
    private long questionId;

    public Answer(long id, String text, boolean isCorrect, long questionId) {
        this.id = id;
        this.text = text;
        this.isCorrect = isCorrect;
        this.questionId = questionId;
    }

    // Getters и Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public boolean isCorrect() { return isCorrect; }
    public void setCorrect(boolean correct) { isCorrect = correct; }

    public long getQuestionId() { return questionId; }
    public void setQuestionId(long questionId) { this.questionId = questionId; }
}
