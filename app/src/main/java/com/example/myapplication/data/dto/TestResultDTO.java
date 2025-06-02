package com.example.myapplication.data.dto;

import com.example.myapplication.domain.entity.TestResult;
import java.io.Serializable;
import java.util.Date;

public class TestResultDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private long testId;
    private int score;
    private int totalQuestions;
    private boolean passed;
    private Date completedAt;

    public TestResultDTO() {}

    public TestResultDTO(long id, long testId, int score, int totalQuestions, boolean passed, Date completedAt) {
        this.id = id;
        this.testId = testId;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.passed = passed;
        this.completedAt = completedAt;
    }

    // Конструктор из Entity
    public TestResultDTO(TestResult testResult) {
        this.id = testResult.getId();
        this.testId = testResult.getTestId();
        this.score = testResult.getScore();
        this.totalQuestions = testResult.getTotalQuestions();
        this.passed = testResult.isPassed();
        this.completedAt = testResult.getCompletedAt();
    }

    // Преобразование в Entity
    public TestResult toTestResult() {
        return new TestResult(this.id, this.testId, this.score, this.totalQuestions, this.passed, this.completedAt);
    }

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
