package com.example.myapplication.data.dto;

import com.example.myapplication.domain.entity.Question;
import com.example.myapplication.domain.entity.Answer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuestionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String text;
    private String imageUrl;
    private long testId;
    private List<AnswerDTO> answers = new ArrayList<>();

    public QuestionDTO() {}

    public QuestionDTO(long id, String text, String imageUrl, long testId, List<AnswerDTO> answers) {
        this.id = id;
        this.text = text;
        this.imageUrl = imageUrl;
        this.testId = testId;
        this.answers = answers;
    }

    // Конструктор из Entity
    public QuestionDTO(Question question) {
        this.id = question.getId();
        this.text = question.getText();
        this.imageUrl = question.getImageUrl();
        this.testId = question.getTestId();

        this.answers = new ArrayList<>();
        if (question.getAnswers() != null) {
            for (Answer answer : question.getAnswers()) {
                this.answers.add(new AnswerDTO(answer));
            }
        }
    }

    // Преобразование в Entity
    public Question toQuestion() {
        List<Answer> answerEntities = new ArrayList<>();
        if (this.answers != null) {
            for (AnswerDTO answerDTO : this.answers) {
                answerEntities.add(answerDTO.toAnswer());
            }
        }

        Question question = new Question(this.id, this.text, this.imageUrl, this.testId);
        question.setAnswers(answerEntities);
        return question;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public long getTestId() { return testId; }
    public void setTestId(long testId) { this.testId = testId; }

    public List<AnswerDTO> getAnswers() { return answers; }
    public void setAnswers(List<AnswerDTO> answers) { this.answers = answers; }
}
