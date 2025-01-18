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

    /**
     * Retrieve all waste categories
     * @return List of all waste categories
     */
    @GetMapping
    public ResponseEntity<List<WasteCategoryEntity>> getAllCategories() {
        logger.info("Fetching all waste categories");
        return ResponseEntity.ok(service.getAllCategories());
    }

    /**
     * Retrieve a specific waste category by ID
     * @param id The ID of the waste category
     * @return The waste category if found, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<WasteCategoryEntity> getCategoryById(@PathVariable Long id) {
        logger.info("Fetching waste category with id: {}", id);
        return service.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create a new waste category
     * @param category The waste category details to create
     * @return The created waste category with location header
     */
    @PostMapping
    public ResponseEntity<WasteCategoryEntity> createCategory(
            @Valid @RequestBody WasteCategoryEntity category) {
        logger.info("Creating new waste category");
        WasteCategoryEntity created = service.createCategory(category);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    /**
     * Update an existing waste category
     * @param id The ID of the waste category to update
     * @param category The updated waste category details
     * @return The updated waste category, or 404 if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<WasteCategoryEntity> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody WasteCategoryEntity category) {
        logger.info("Updating waste category with id: {}", id);
        return service.updateCategory(id, category)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete a waste category
     * @param id The ID of the waste category to delete
     * @return 204 if deleted, 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        logger.info("Deleting waste category with id: {}", id);
        return service.deleteCategory(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
