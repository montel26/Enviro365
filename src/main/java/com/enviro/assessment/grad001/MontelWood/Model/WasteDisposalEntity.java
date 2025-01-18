package com.enviro.assessment.grad001.MontelWood.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class WasteDisposalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Method name is required")
    private String methodName;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "WasteCategory is required")
    @ManyToOne
    @JoinColumn(name = "waste_category_id")
    private WasteCategoryEntity wasteCategory;

    private String safetyPrecautions;
    private String environmentalImpact;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WasteCategoryEntity getWasteCategory() {
        return wasteCategory;
    }

    public void setWasteCategory(WasteCategoryEntity wasteCategory) {
        this.wasteCategory = wasteCategory;
    }

    public String getSafetyPrecautions() {
        return safetyPrecautions;
    }

    public void setSafetyPrecautions(String safetyPrecautions) {
        this.safetyPrecautions = safetyPrecautions;
    }

    public String getEnvironmentalImpact() {
        return environmentalImpact;
    }

    public void setEnvironmentalImpact(String environmentalImpact) {
        this.environmentalImpact = environmentalImpact;
    }
}
