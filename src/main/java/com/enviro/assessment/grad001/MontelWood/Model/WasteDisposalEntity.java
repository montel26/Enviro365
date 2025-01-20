package com.enviro.assessment.grad001.MontelWood.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Entity class representing waste disposal methods.
 * This class maintains information about different ways to dispose of waste,
 * including environmental impacts and safety considerations.
 */

@Entity
public class WasteDisposalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Method name is required")
    @Column(name = "method_name", nullable = false)
    private String methodName;

    @NotBlank(message = "Description is required")
    @Column(nullable = false)
    private String description;

    @Column(name = "environmental_impact")
    private String environmentalImpact;

    @Column(name = "safety_precautions")
    private String safetyPrecautions;

    @NotNull(message = "WasteCategory is required")
    @ManyToOne
    @JoinColumn(name = "waste_category_id")
    private WasteCategoryEntity wasteCategory;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMethodName() { return methodName; }
    public void setMethodName(String methodName) { this.methodName = methodName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getEnvironmentalImpact() { return environmentalImpact; }
    public void setEnvironmentalImpact(String environmentalImpact) { this.environmentalImpact = environmentalImpact; }

    public String getSafetyPrecautions() { return safetyPrecautions; }
    public void setSafetyPrecautions(String safetyPrecautions) { this.safetyPrecautions = safetyPrecautions; }

    public WasteCategoryEntity getWasteCategory() { return wasteCategory; }
    public void setWasteCategory(WasteCategoryEntity wasteCategory) { this.wasteCategory = wasteCategory; }
}


