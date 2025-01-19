package com.enviro.assessment.grad001.MontelWood.CategoryTests;

import com.enviro.assessment.grad001.MontelWood.Model.WasteCategoryEntity;
import com.enviro.assessment.grad001.MontelWood.Repository.WasteCategoryRepository;
import com.enviro.assessment.grad001.MontelWood.Service.WasteCategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WasteCategoryServiceTest {

    @Mock
    private WasteCategoryRepository repository;

    @InjectMocks
    private WasteCategoryService service;

    private WasteCategoryEntity testCategory;

    @BeforeEach
    void setUp() {
        testCategory = new WasteCategoryEntity();
        testCategory.setId(1L);
        testCategory.setMethodName("Glass");
        testCategory.setDescription("Glass waste");
    }

    @Test
    void getAllCategories_ShouldReturnAllCategories() {
        List<WasteCategoryEntity> expectedCategories = Arrays.asList(testCategory);
        when(repository.findAll()).thenReturn(expectedCategories);

        List<WasteCategoryEntity> actualCategories = service.getAllCategories();

        assertEquals(expectedCategories, actualCategories);
        verify(repository).findAll();
    }

    @Test
    void getCategoryById_WhenExists_ShouldReturnCategory() {
        when(repository.findById(1L)).thenReturn(Optional.of(testCategory));

        Optional<WasteCategoryEntity> result = service.getCategoryById(1L);

        assertTrue(result.isPresent());
        assertEquals(testCategory, result.get());
        verify(repository).findById(1L);
    }

    @Test
    void getCategoryById_WhenNotExists_ShouldReturnEmpty() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Optional<WasteCategoryEntity> result = service.getCategoryById(1L);

        assertTrue(result.isEmpty());
        verify(repository).findById(1L);
    }

    @Test
    void createCategory_ShouldSaveAndReturnCategory() {
        when(repository.save(any(WasteCategoryEntity.class))).thenReturn(testCategory);

        WasteCategoryEntity result = service.createCategory(testCategory);

        assertEquals(testCategory, result);
        verify(repository).save(testCategory);
    }

    @Test
    void updateCategory_WhenExists_ShouldUpdateAndReturnCategory() {
        WasteCategoryEntity updatedCategory = new WasteCategoryEntity();
        updatedCategory.setMethodName("Updated Glass");
        updatedCategory.setDescription("Updated description");

        when(repository.findById(1L)).thenReturn(Optional.of(testCategory));
        when(repository.save(any(WasteCategoryEntity.class))).thenReturn(testCategory);

        Optional<WasteCategoryEntity> result = service.updateCategory(1L, updatedCategory);

        assertTrue(result.isPresent());
        verify(repository).findById(1L);
        verify(repository).save(any(WasteCategoryEntity.class));
    }

    @Test
    void updateCategory_WhenNotExists_ShouldReturnEmpty() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Optional<WasteCategoryEntity> result = service.updateCategory(1L, testCategory);

        assertTrue(result.isEmpty());
        verify(repository).findById(1L);
        verify(repository, never()).save(any(WasteCategoryEntity.class));
    }

    @Test
    void deleteCategory_WhenExists_ShouldReturnTrue() {
        when(repository.findById(1L)).thenReturn(Optional.of(testCategory));

        boolean result = service.deleteCategory(1L);

        assertTrue(result);
        verify(repository).findById(1L);
        verify(repository).delete(testCategory);
    }

    @Test
    void deleteCategory_WhenNotExists_ShouldReturnFalse() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        boolean result = service.deleteCategory(1L);

        assertFalse(result);
        verify(repository).findById(1L);
        verify(repository, never()).delete(any());
    }
}
