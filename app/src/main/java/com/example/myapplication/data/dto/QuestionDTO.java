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
