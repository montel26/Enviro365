package com.enviro.assessment.grad001.MontelWood.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Waste-category")
public class WasteCategoryEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private long Id;
    private String name;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }
}
