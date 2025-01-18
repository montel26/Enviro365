package com.enviro.assessment.grad001.MontelWood.CategoryTests;

import com.enviro.assessment.grad001.MontelWood.Model.WasteCategoryEntity;
import com.enviro.assessment.grad001.MontelWood.Repository.WasteCategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class WasteCategoryRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private WasteCategoryRepository repository;

    @Test
    void save_ShouldPersistCategory() {
        // Create test category
        WasteCategoryEntity category = new WasteCategoryEntity();
        category.setName("Electronics");
        category.setDescription("Electronic waste");
        category.setDisposalGuidelines("Take to recycling center");
        category.setRecyclingTips("Remove batteries");

        // Save category
        WasteCategoryEntity savedCategory = repository.save(category);

        // Verify saved category
        assertNotNull(savedCategory.getId());
        assertEquals("Electronics", savedCategory.getName());
    }

    @Test
    void findById_WhenExists_ShouldReturnCategory() {
        // Create and persist test category
        WasteCategoryEntity category = new WasteCategoryEntity();
        category.setName("Paper");
        category.setDescription("Paper waste");
        Long id = entityManager.persistAndGetId(category, Long.class);

        // Find category by id
        Optional<WasteCategoryEntity> found = repository.findById(id);

        // Verify found category
        assertTrue(found.isPresent());
        assertEquals("Paper", found.get().getName());
    }

    @Test
    void findById_WhenNotExists_ShouldReturnEmpty() {
        Optional<WasteCategoryEntity> found = repository.findById(999L);
        assertTrue(found.isEmpty());
    }

    @Test
    void delete_ShouldRemoveCategory() {
        // Create and persist test category
        WasteCategoryEntity category = new WasteCategoryEntity();
        category.setName("Glass");
        category.setDescription("Glass waste");
        Long id = entityManager.persistAndGetId(category, Long.class);

        // Delete category
        repository.deleteById(id);

        // Verify deletion
        Optional<WasteCategoryEntity> found = repository.findById(id);
        assertTrue(found.isEmpty());
    }
}
