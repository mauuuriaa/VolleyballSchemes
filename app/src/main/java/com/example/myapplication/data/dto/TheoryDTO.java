package com.example.myapplication.data.dto;

import com.example.myapplication.domain.entity.Theory;
import java.io.Serializable;

public class TheoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String title;
    private String content;
    private int categoryId;
    private boolean isCompleted;

    public TheoryDTO() {}

    public TheoryDTO(long id, String title, String content, int categoryId, boolean isCompleted) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
        this.isCompleted = isCompleted;
    }

    // Конструктор из Entity
    public TheoryDTO(Theory theory) {
        this.id = theory.getId();
        this.title = theory.getTitle();
        this.content = theory.getContent();
        this.categoryId = theory.getCategoryId();
        this.isCompleted = theory.isCompleted();
    }

    // Преобразование в Entity
    public Theory toTheory() {
        Theory theory = new Theory(this.id, this.title, this.content, this.categoryId);
        theory.setCompleted(this.isCompleted);
        return theory;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }
}
