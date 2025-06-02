package com.example.myapplication.data.dto;

import com.example.myapplication.domain.entity.Answer;
import java.io.Serializable;

public class AnswerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String text;
    private boolean isCorrect;
    private long questionId;

    public AnswerDTO() {}

    public AnswerDTO(long id, String text, boolean isCorrect, long questionId) {
        this.id = id;
        this.text = text;
        this.isCorrect = isCorrect;
        this.questionId = questionId;
    }

    // Конструктор из Entity
    public AnswerDTO(Answer answer) {
        this.id = answer.getId();
        this.text = answer.getText();
        this.isCorrect = answer.isCorrect();
        this.questionId = answer.getQuestionId();
    }

    // Преобразование в Entity
    public Answer toAnswer() {
        return new Answer(this.id, this.text, this.isCorrect, this.questionId);
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public boolean isCorrect() { return isCorrect; }
    public void setCorrect(boolean correct) { isCorrect = correct; }

    public long getQuestionId() { return questionId; }
    public void setQuestionId(long questionId) { this.questionId = questionId; }
}
