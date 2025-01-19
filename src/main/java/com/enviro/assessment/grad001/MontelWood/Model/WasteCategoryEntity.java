package com.enviro.assessment.grad001.MontelWood.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "waste_category")  // Explicitly specify table name
public class WasteCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Category name is required")
    @Size(min = 2, max = 50, message = "Category name must be between 2 and 50 characters")
    @Column(nullable = false)  // Ensure column is not nullable
    private String name;

    @NotBlank(message = "Description is required")
    @Size(min = 2, max = 700, message = "Description must be between 2 and 700 characters")
    @Column(nullable = false)  // Ensure column is not nullable
    private String description;

    @Column(name = "disposal_guidelines")  // Explicitly specify column name
    private String disposalGuidelines;

    @Column(name = "recycling_tips")  // Explicitly specify column name
    private String recyclingTips;

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public String getName() {  // Correct method name
        return name;
    }

    public void setName(String name) {  // Correct method name
        this.name = name;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getMethodName() {  // Fixed method name to match field
        return name;
    }

    public void setMethodName(String name) {  // Fixed method name to match field
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisposalGuidelines() {
        return disposalGuidelines;
    }

    public void setDisposalGuidelines(String disposalGuidelines) {
        this.disposalGuidelines = disposalGuidelines;
    }

    public String getRecyclingTips() {
        return recyclingTips;
    }

    public void setRecyclingTips(String recyclingTips) {
        this.recyclingTips = recyclingTips;
    }

}
