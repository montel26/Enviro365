package com.enviro.assessment.grad001.MontelWood.RecycleTipsTests;

import com.enviro.assessment.grad001.MontelWood.Model.RecyclingTipEntity;
import com.enviro.assessment.grad001.MontelWood.Model.WasteCategoryEntity;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.*;

class recyclingTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void testValidRecycling() {
        WasteCategoryEntity category = new WasteCategoryEntity();
        category.setId(1L);
        category.setName("Paper");

        RecyclingTipEntity recycling = new RecyclingTipEntity();
        recycling.setProcessName("Pulping");
        recycling.setDescription("Convert waste paper to pulp");
        recycling.setWasteCategory(category);
        recycling.setProcessingSteps("Sort, Shred, Pulp");
        recycling.setBenefitsDescription("Saves trees");
        recycling.setResourceSavings("17 trees per ton");

        var violations = validator.validate(recycling);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidRecyclingWithoutProcessName() {
        RecyclingTipEntity recycling = new RecyclingTipEntity();
        recycling.setDescription("Test description");

        var violations = validator.validate(recycling);
        assertFalse(violations.isEmpty());
        assertEquals(2, violations.size()); // Missing processName and wasteCategory
    }

    @Test
    void testGettersAndSetters() {
        WasteCategoryEntity category = new WasteCategoryEntity();
        category.setId(1L);

        RecyclingTipEntity recycling = new RecyclingTipEntity();
        recycling.setId(1L);
        recycling.setProcessName("Glass Recycling");
        recycling.setDescription("Melting and reforming");
        recycling.setWasteCategory(category);
        recycling.setProcessingSteps("Clean, Sort, Melt");
        recycling.setBenefitsDescription("Reduces landfill");
        recycling.setResourceSavings("Energy reduction");

        assertEquals(1L, recycling.getId());
        assertEquals("Glass Recycling", recycling.getProcessName());
        assertEquals("Melting and reforming", recycling.getDescription());
        assertEquals(category, recycling.getWasteCategory());
        assertEquals("Clean, Sort, Melt", recycling.getProcessingSteps());
        assertEquals("Reduces landfill", recycling.getBenefitsDescription());
        assertEquals("Energy reduction", recycling.getResourceSavings());
    }
}
