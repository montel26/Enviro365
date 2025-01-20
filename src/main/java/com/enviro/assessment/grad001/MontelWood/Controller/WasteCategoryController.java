package com.enviro.assessment.grad001.MontelWood.Controller;

import com.enviro.assessment.grad001.MontelWood.Model.WasteCategoryEntity;
import com.enviro.assessment.grad001.MontelWood.Service.WasteCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.List;

/**
 * REST controller for managing waste categories.
 * Provides endpoints for CRUD operations on waste categories.
 * Base path: /api/waste-categories
 */

@RestController
@RequestMapping("/api/waste-categories")
@Validated
public class WasteCategoryController {
    private static final Logger logger = LoggerFactory.getLogger(WasteCategoryController.class);
    private final WasteCategoryService service;

    @Autowired
    public WasteCategoryController(WasteCategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<WasteCategoryEntity>> getAllCategories() {
        logger.info("Fetching all waste categories");
        List<WasteCategoryEntity> categories = service.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WasteCategoryEntity> getCategoryById(@PathVariable Long id) {
        logger.info("Fetching waste category with ID: {}", id);
        return service.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<WasteCategoryEntity> createCategory(@Valid @RequestBody WasteCategoryEntity category) {
        logger.info("Creating new waste category");
        WasteCategoryEntity created = service.createCategory(category);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WasteCategoryEntity> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody WasteCategoryEntity category) {
        logger.info("Updating waste category with ID: {}", id);
        return service.updateCategory(id, category)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        logger.info("Deleting waste category with ID: {}", id);
        if (service.deleteCategory(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}