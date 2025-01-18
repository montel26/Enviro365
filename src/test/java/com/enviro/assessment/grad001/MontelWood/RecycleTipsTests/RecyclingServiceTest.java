package com.enviro.assessment.grad001.MontelWood.RecycleTipsTests;

import com.enviro.assessment.grad001.MontelWood.Model.RecyclingTipEntity;
import com.enviro.assessment.grad001.MontelWood.Model.WasteCategoryEntity;
import com.enviro.assessment.grad001.MontelWood.Repository.RecyclingTipsRepository;
import com.enviro.assessment.grad001.MontelWood.Service.RecycleTipService;
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
class RecyclingServiceTest {

    @Mock
    private RecyclingTipsRepository repository;

    @InjectMocks
    private RecycleTipService service;

    private RecyclingTipEntity testRecycling;
    private WasteCategoryEntity testCategory;

    @BeforeEach
    void setUp() {
        testCategory = new WasteCategoryEntity();
        testCategory.setId(1L);
        testCategory.setName("Plastics");

        testRecycling = new RecyclingTipEntity();
        testRecycling.setId(1L);
        testRecycling.setProcessName("Plastic Recycling");
        testRecycling.setDescription("Converting waste plastic into new products");
        testRecycling.setWasteCategory(testCategory);
        testRecycling.setProcessingSteps("Sort, Clean, Melt, Mold");
    }

    @Test
    void getAllRecyclingProcesses_ShouldReturnAllProcesses() {
        when(repository.findAll()).thenReturn(Arrays.asList(testRecycling));

        List<RecyclingTipEntity> result = service.getAllRecyclingProcesses();

        assertEquals(1, result.size());
        verify(repository).findAll();
    }

    @Test
    void getRecyclingById_WhenExists_ShouldReturnRecycling() {
        when(repository.findById(1L)).thenReturn(Optional.of(testRecycling));

        Optional<RecyclingTipEntity> result = service.getRecyclingById(1L);

        assertTrue(result.isPresent());
        assertEquals(testRecycling, result.get());
    }

    @Test
    void getRecyclingProcessesByCategory_ShouldReturnCategoryProcesses() {
        when(repository.findByWasteCategoryId(1L)).thenReturn(Arrays.asList(testRecycling));

        List<RecyclingTipEntity> result = service.getRecyclingProcessesByCategory(1L);

        assertEquals(1, result.size());
        verify(repository).findByWasteCategoryId(1L);
    }

    @Test
    void createRecyclingProcess_ShouldSaveAndReturn() {
        when(repository.save(any(RecyclingTipEntity.class))).thenReturn(testRecycling);

        RecyclingTipEntity result = service.createRecyclingProcess(testRecycling);

        assertEquals(testRecycling, result);
        verify(repository).save(testRecycling);
    }

    @Test
    void updateRecyclingProcess_WhenExists_ShouldUpdate() {
        RecyclingTipEntity updatedRecycling = new RecyclingTipEntity();
        updatedRecycling.setProcessName("Updated Process");

        when(repository.findById(1L)).thenReturn(Optional.of(testRecycling));
        when(repository.save(any(RecyclingTipEntity.class))).thenReturn(testRecycling);

        Optional<RecyclingTipEntity> result = service.updateRecyclingProcess(1L, updatedRecycling);

        assertTrue(result.isPresent());
        verify(repository).findById(1L);
        verify(repository).save(any(RecyclingTipEntity.class));
    }

    @Test
    void deleteRecyclingProcess_WhenExists_ShouldDelete() {
        when(repository.findById(1L)).thenReturn(Optional.of(testRecycling));

        boolean result = service.deleteRecyclingProcess(1L);

        assertTrue(result);
        verify(repository).delete(testRecycling);
    }
}

