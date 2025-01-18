package com.enviro.assessment.grad001.MontelWood.WasteDisposalTests;

import com.enviro.assessment.grad001.MontelWood.Model.WasteCategoryEntity;
import com.enviro.assessment.grad001.MontelWood.Model.WasteDisposalEntity;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.*;

class DisposalTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void testValidDisposal() {
        WasteCategoryEntity category = new WasteCategoryEntity();
        category.setId(1L);
        category.setName("Plastics");

        WasteDisposalEntity disposal = new WasteDisposalEntity();
        disposal.setMethodName("Compacting");
        disposal.setDescription("Compress plastic waste");
        disposal.setWasteCategory(category);
        disposal.setSafetyPrecautions("Wear gloves");
        disposal.setEnvironmentalImpact("Minimal impact");

        var violations = validator.validate(disposal);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidDisposalWithoutMethodName() {
        WasteDisposalEntity disposal = new WasteDisposalEntity();
        disposal.setDescription("Test description");

        var violations = validator.validate(disposal);
        assertFalse(violations.isEmpty());
        assertEquals(2, violations.size()); // Missing methodName and wasteCategory
    }

    @Test
    void testGettersAndSetters() {
        WasteCategoryEntity category = new WasteCategoryEntity();
        category.setId(1L);

        WasteDisposalEntity disposal = new WasteDisposalEntity();
        disposal.setId(1L);
        disposal.setMethodName("Incineration");
        disposal.setDescription("Controlled burning");
        disposal.setWasteCategory(category);
        disposal.setSafetyPrecautions("Use protective gear");
        disposal.setEnvironmentalImpact("Emissions controlled");

        assertEquals(1L, disposal.getId());
        assertEquals("Incineration", disposal.getMethodName());
        assertEquals("Controlled burning", disposal.getDescription());
        assertEquals(category, disposal.getWasteCategory());
        assertEquals("Use protective gear", disposal.getSafetyPrecautions());
        assertEquals("Emissions controlled", disposal.getEnvironmentalImpact());
    }
}
