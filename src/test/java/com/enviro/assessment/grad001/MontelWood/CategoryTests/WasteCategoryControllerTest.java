package com.enviro.assessment.grad001.MontelWood.CategoryTests;

import com.enviro.assessment.grad001.MontelWood.Controller.WasteCategoryController;
import com.enviro.assessment.grad001.MontelWood.Model.WasteCategoryEntity;
import com.enviro.assessment.grad001.MontelWood.Service.WasteCategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WasteCategoryController.class)
class WasteCategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WasteCategoryService service;

    @Autowired
    private ObjectMapper objectMapper;

    private WasteCategoryEntity testCategory;

    @BeforeEach
    void setUp() {
        testCategory = new WasteCategoryEntity();
        testCategory.setId(1L);
        testCategory.setName("Metal");
        testCategory.setDescription("Metal waste");
        testCategory.setDisposalGuidelines("Clean and separate");
        testCategory.setRecyclingTips("Remove non-metal parts");
    }

    @Test
    void getAllCategories_ShouldReturnAllCategories() throws Exception {
        when(service.getAllCategories()).thenReturn(Arrays.asList(testCategory));

        mockMvc.perform(get("/api/waste-categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Metal"));
    }

    @Test
    void getCategoryById_WhenExists_ShouldReturnCategory() throws Exception {
        when(service.getCategoryById(1L)).thenReturn(Optional.of(testCategory));

        mockMvc.perform(get("/api/waste-categories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Metal"));
    }

    @Test
    void getCategoryById_WhenNotExists_ShouldReturn404() throws Exception {
        when(service.getCategoryById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/waste-categories/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createCategory_WithValidData_ShouldCreateAndReturn201() throws Exception {
        when(service.createCategory(any(WasteCategoryEntity.class))).thenReturn(testCategory);

        mockMvc.perform(post("/api/waste-categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testCategory)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Metal"));
    }

    @Test
    void createCategory_WithInvalidData_ShouldReturn400() throws Exception {
        testCategory.setName(""); // Invalid: name is blank

        mockMvc.perform(post("/api/waste-categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testCategory)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateCategory_WhenExists_ShouldUpdateAndReturn200() throws Exception {
        when(service.updateCategory(eq(1L), any(WasteCategoryEntity.class)))
                .thenReturn(Optional.of(testCategory));

        mockMvc.perform(put("/api/waste-categories/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testCategory)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Metal"));
    }

    @Test
    void updateCategory_WhenNotExists_ShouldReturn404() throws Exception {
        when(service.updateCategory(eq(1L), any(WasteCategoryEntity.class)))
                .thenReturn(Optional.empty());

        mockMvc.perform(put("/api/waste-categories/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testCategory)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteCategory_WhenExists_ShouldReturn204() throws Exception {
        when(service.deleteCategory(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/waste-categories/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteCategory_WhenNotExists_ShouldReturn404() throws Exception {
        when(service.deleteCategory(1L)).thenReturn(false);

        mockMvc.perform(delete("/api/waste-categories/1"))
                .andExpect(status().isNotFound());
    }
}
