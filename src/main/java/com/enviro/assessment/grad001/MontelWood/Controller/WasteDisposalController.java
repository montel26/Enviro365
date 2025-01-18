package com.enviro.assessment.grad001.MontelWood.Controller;

import com.enviro.assessment.grad001.MontelWood.Model.WasteDisposalEntity;
import com.enviro.assessment.grad001.MontelWood.Service.WasteDisposalService;
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
@RequestMapping("/api/waste-disposal")
@Validated
public class WasteDisposalController {
    private static final Logger logger = LoggerFactory.getLogger(WasteDisposalController.class);
    private final WasteDisposalService service;

    @Autowired
    public WasteDisposalController(WasteDisposalService service) {
        this.service = service;
    }

    /**
     * Retrieve all disposal methods
     * @return List of all disposal methods
     */
    @GetMapping
    public ResponseEntity<List<WasteDisposalEntity>> getAllDisposalMethods() {
        logger.info("Fetching all disposal methods");
        return ResponseEntity.ok(service.getAllDisposalMethods());
    }

    /**
     * Retrieve a specific disposal method by ID
     * @param id The ID of the disposal method
     * @return The disposal method if found, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<WasteDisposalEntity> getDisposalById(@PathVariable Long id) {
        logger.info("Fetching disposal method with id: {}", id);
        return service.getDisposalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get disposal methods by category
     * @param categoryId The ID of the category
     * @return List of disposal methods for the specified category
     */
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<WasteDisposalEntity>> getDisposalMethodsByCategory(@PathVariable Long categoryId) {
        logger.info("Fetching disposal methods for category: {}", categoryId);
        List<WasteDisposalEntity> methods = service.getDisposalMethodsByCategory(categoryId);
        return ResponseEntity.ok(methods);
    }

    /**
     * Create a new disposal method
     * @param disposal The disposal method details to create
     * @return The created disposal method with location header
     */
    @PostMapping
    public ResponseEntity<WasteDisposalEntity> createDisposalMethod(
            @Valid @RequestBody WasteDisposalEntity disposal) {
        logger.info("Creating new disposal method");
        WasteDisposalEntity created = service.createDisposalMethod(disposal);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    /**
     * Update an existing disposal method
     * @param id The ID of the disposal method to update
     * @param disposal The updated disposal method details
     * @return The updated disposal method, or 404 if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<WasteDisposalEntity> updateDisposalMethod(
            @PathVariable Long id,
            @Valid @RequestBody WasteDisposalEntity disposal) {
        logger.info("Updating disposal method with id: {}", id);
        return service.updateDisposalMethod(id, disposal)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete a disposal method
     * @param id The ID of the disposal method to delete
     * @return 204 if deleted, 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisposalMethod(@PathVariable Long id) {
        logger.info("Deleting disposal method with id: {}", id);
        return service.deleteDisposalMethod(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
