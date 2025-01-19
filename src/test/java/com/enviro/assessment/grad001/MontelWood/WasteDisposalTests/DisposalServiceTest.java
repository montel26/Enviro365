package com.enviro.assessment.grad001.MontelWood.WasteDisposalTests;


import com.enviro.assessment.grad001.MontelWood.Model.WasteCategoryEntity;
import com.enviro.assessment.grad001.MontelWood.Model.WasteDisposalEntity;
import com.enviro.assessment.grad001.MontelWood.Repository.DisposalGuidelineRepository;
import com.enviro.assessment.grad001.MontelWood.Service.WasteDisposalService;
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
class DisposalServiceTest {

    @Mock
    private DisposalGuidelineRepository repository;

    @InjectMocks
    private WasteDisposalService service;

    private WasteDisposalEntity testDisposal;
    private WasteCategoryEntity testCategory;

    @BeforeEach
    void setUp() {
        testCategory = new WasteCategoryEntity();
        testCategory.setId(1L);
        testCategory.setMethodName("Electronics");

        testDisposal = new WasteDisposalEntity();
        testDisposal.setId(1L);
        testDisposal.setMethodName("E-waste Processing");
        testDisposal.setDescription("Safe disposal of electronic waste");
        testDisposal.setWasteCategory(testCategory);
    }

    @Test
    void getAllDisposalMethods_ShouldReturnAllMethods() {
        when(repository.findAll()).thenReturn(Arrays.asList(testDisposal));

        List<WasteDisposalEntity> result = service.getAllDisposalMethods();

        assertEquals(1, result.size());
        verify(repository).findAll();
    }

    @Test
    void getDisposalById_WhenExists_ShouldReturnDisposal() {
        when(repository.findById(1L)).thenReturn(Optional.of(testDisposal));

        Optional<WasteDisposalEntity> result = service.getDisposalById(1L);

        assertTrue(result.isPresent());
        assertEquals(testDisposal, result.get());
    }

    @Test
    void getDisposalMethodsByCategory_ShouldReturnCategoryMethods() {
        when(repository.findByWasteCategoryId(1L)).thenReturn(Arrays.asList(testDisposal));

        List<WasteDisposalEntity> result = service.getDisposalMethodsByCategory(1L);

        assertEquals(1, result.size());
        verify(repository).findByWasteCategoryId(1L);
    }

    @Test
    void createDisposalMethod_ShouldSaveAndReturn() {
        when(repository.save(any(WasteDisposalEntity.class))).thenReturn(testDisposal);

        WasteDisposalEntity result = service.createDisposalMethod(testDisposal);

        assertEquals(testDisposal, result);
        verify(repository).save(testDisposal);
    }

    @Test
    void updateDisposalMethod_WhenExists_ShouldUpdate() {
        WasteDisposalEntity updatedDisposal = new WasteDisposalEntity();
        updatedDisposal.setMethodName("Updated Method");

        when(repository.findById(1L)).thenReturn(Optional.of(testDisposal));
        when(repository.save(any(WasteDisposalEntity.class))).thenReturn(testDisposal);

        Optional<WasteDisposalEntity> result = service.updateDisposalMethod(1L, updatedDisposal);

        assertTrue(result.isPresent());
        verify(repository).findById(1L);
        verify(repository).save(any(WasteDisposalEntity.class));
    }

    @Test
    void deleteDisposalMethod_WhenExists_ShouldDelete() {
        when(repository.findById(1L)).thenReturn(Optional.of(testDisposal));

        boolean result = service.deleteDisposalMethod(1L);

        assertTrue(result);
        verify(repository).delete(testDisposal);
    }
}