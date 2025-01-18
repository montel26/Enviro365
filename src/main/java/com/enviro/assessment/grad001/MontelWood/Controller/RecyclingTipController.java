package com.enviro.assessment.grad001.MontelWood.Controller;

import com.enviro.assessment.grad001.MontelWood.Model.RecyclingTipEntity;
import com.enviro.assessment.grad001.MontelWood.Service.RecycleTipService;
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
@RequestMapping("/api/recycling-tips")
@Validated
public class RecyclingTipController {
    private static final Logger logger = LoggerFactory.getLogger(RecyclingTipController.class);
    private final RecycleTipService service;

    @Autowired
    public RecyclingTipController(RecycleTipService service) {
        this.service = service;
    }

    /**
     * Retrieve all recycling processes
     * @return List of all recycling tips
     */
    @GetMapping
    public ResponseEntity<List<RecyclingTipEntity>> getAllRecyclingProcesses() {
        logger.info("Fetching all recycling processes");
        return ResponseEntity.ok(service.getAllRecyclingProcesses());
    }

    /**
     * Retrieve a specific recycling tip by ID
     * @param id The ID of the recycling tip
     * @return The recycling tip if found, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<RecyclingTipEntity> getRecyclingById(@PathVariable Long id) {
        logger.info("Fetching recycling tip with id: {}", id);
        return service.getRecyclingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get recycling processes by category
     * @param categoryId The ID of the category
     * @return List of recycling processes for the specified category
     */
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<RecyclingTipEntity>> getRecyclingProcessesByCategory(@PathVariable Long categoryId) {
        logger.info("Fetching recycling processes for category: {}", categoryId);
        List<RecyclingTipEntity> processes = service.getRecyclingProcessesByCategory(categoryId);
        return ResponseEntity.ok(processes);
    }

    /**
     * Create a new recycling process
     * @param recycling The recycling tip details to create
     * @return The created recycling tip with location header
     */
    @PostMapping
    public ResponseEntity<RecyclingTipEntity> createRecyclingProcess(
            @Valid @RequestBody RecyclingTipEntity recycling) {
        logger.info("Creating new recycling process");
        RecyclingTipEntity created = service.createRecyclingProcess(recycling);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    /**
     * Update an existing recycling process
     * @param id The ID of the recycling tip to update
     * @param recycling The updated recycling tip details
     * @return The updated recycling tip, or 404 if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<RecyclingTipEntity> updateRecyclingProcess(
            @PathVariable Long id,
            @Valid @RequestBody RecyclingTipEntity recycling) {
        logger.info("Updating recycling process with id: {}", id);
        return service.updateRecyclingProcess(id, recycling)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete a recycling process
     * @param id The ID of the recycling tip to delete
     * @return 204 if deleted, 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecyclingProcess(@PathVariable Long id) {
        logger.info("Deleting recycling process with id: {}", id);
        return service.deleteRecyclingProcess(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}