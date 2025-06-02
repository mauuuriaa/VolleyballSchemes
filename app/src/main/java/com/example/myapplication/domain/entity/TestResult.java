package com.example.myapplication.domain.entity;

import java.util.Date;
public class TestResult {
    private long id;
    private long testId;
    private int score;
    private int totalQuestions;
    private boolean passed;
    private Date completedAt;

    public TestResult(long id, long testId, int score, int totalQuestions, boolean passed, Date completedAt) {
        this.id = id;
        this.testId = testId;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.passed = passed;
        this.completedAt = completedAt != null ? completedAt : new Date();
    }

    // Getters и Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public long getTestId() { return testId; }
    public void setTestId(long testId) { this.testId = testId; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public int getTotalQuestions() { return totalQuestions; }
    public void setTotalQuestions(int totalQuestions) { this.totalQuestions = totalQuestions; }

    public boolean isPassed() { return passed; }
    public void setPassed(boolean passed) { this.passed = passed; }

    public Date getCompletedAt() { return completedAt; }
    public void setCompletedAt(Date completedAt) { this.completedAt = completedAt; }
}
