package com.enviro.assessment.grad001.MontelWood.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Disposal-guidlines")
public class DisposalGuidelineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long Id;
    private String guideline;
    private String category;

    public String getGuideline() {
        return guideline;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setGuideline(String guideline) {
        this.guideline = guideline;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }
}
