package com.enviro.assessment.grad001.MontelWood.CategoryTests;

import com.enviro.assessment.grad001.MontelWood.Model.WasteCategoryEntity;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.*;

public class WasteCategoryTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void testValidWasteCategory() {
        WasteCategoryEntity category = new WasteCategoryEntity();
        category.setName("Plastic");
        category.setDescription("All plastic materials");
        category.setDisposalGuidelines("Clean and dry before disposal");
        category.setRecyclingTips("Remove caps and labels");

        var violations = validator.validate(category);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidNameTooShort() {
        WasteCategoryEntity category = new WasteCategoryEntity();
        category.setName("A"); // Too short
        category.setDescription("All plastic materials");

        var violations = validator.validate(category);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
    }

    @Test
    void testBlankDescription() {
        WasteCategoryEntity category = new WasteCategoryEntity();
        category.setName("Plastic");
        category.setDescription(""); // Blank description

        var violations = validator.validate(category);
        assertFalse(violations.isEmpty());
//        assertEquals(1, violations.size());
    }

    @Test
    void testGettersAndSetters() {
        WasteCategoryEntity category = new WasteCategoryEntity();

        category.setId(1L);
        category.setName("Paper");
        category.setDescription("Paper waste");
        category.setDisposalGuidelines("Fold and bundle");
        category.setRecyclingTips("Remove staples");

        assertEquals(1L, category.getId());
        assertEquals("Paper", category.getName());
        assertEquals("Paper waste", category.getDescription());
        assertEquals("Fold and bundle", category.getDisposalGuidelines());
        assertEquals("Remove staples", category.getRecyclingTips());
    }
}
