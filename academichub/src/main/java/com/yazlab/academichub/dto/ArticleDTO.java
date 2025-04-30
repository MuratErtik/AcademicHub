package com.yazlab.academichub.dto;

public class ArticleDTO {

    private String category; // Örn: "A1", "A2", "A5"

    public ArticleDTO(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}