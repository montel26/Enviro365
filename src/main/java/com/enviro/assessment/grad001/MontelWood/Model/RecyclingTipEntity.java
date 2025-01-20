package com.enviro.assessment.grad001.MontelWood.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Entity class representing recycling tips and processes.
 * This class stores detailed information about recycling procedures,
 * their benefits, and resource conservation aspects.
 */

@Entity
@Table(name = "recycling_tip")

public class RecyclingTipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Process name is required")
    @Column(name = "process_name", nullable = false)
    private String processName;

    @NotBlank(message = "Description is required")
    @Column(nullable = false)
    private String description;

    @NotNull(message = "WasteCategory is required")
    @ManyToOne()
    @JoinColumn(name = "waste_category_id", nullable = false)
    private WasteCategoryEntity wasteCategory;

    @Column(name = "processing_steps")
    private String processingSteps;

    @Column(name = "benefits_description")
    private String benefitsDescription;

    @Column(name = "resource_savings")
    private String resourceSavings;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
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

    public String getProcessingSteps() {
        return processingSteps;
    }

    public void setProcessingSteps(String processingSteps) {
        this.processingSteps = processingSteps;
    }

    public String getBenefitsDescription() {
        return benefitsDescription;
    }

    public void setBenefitsDescription(String benefitsDescription) {
        this.benefitsDescription = benefitsDescription;
    }

    public String getResourceSavings() {
        return resourceSavings;
    }

    public void setResourceSavings(String resourceSavings) {
        this.resourceSavings = resourceSavings;
    }

}
