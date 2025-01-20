
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


/**
 * REST controller for managing recycling tips and processes.
 * Provides endpoints for CRUD operations on recycling information .
 * Base path: /api/recycling-tips
 */

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

    @GetMapping
    public ResponseEntity<List<RecyclingTipEntity>> getAllRecyclingProcesses() {
        logger.info("Fetching all recycling processes");
        List<RecyclingTipEntity> tips = service.getAllRecyclingProcesses();
        return ResponseEntity.ok(tips);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecyclingTipEntity> getRecyclingById(@PathVariable Long id) {
        logger.info("Fetching recycling tip with id: {}", id);
        return service.getRecyclingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<RecyclingTipEntity>> getRecyclingProcessesByCategory(@PathVariable Long categoryId) {
        logger.info("Fetching recycling processes for category: {}", categoryId);
        List<RecyclingTipEntity> processes = service.getRecyclingProcessesByCategory(categoryId);
        return ResponseEntity.ok(processes);
    }

    @PostMapping
    public ResponseEntity<RecyclingTipEntity> createRecyclingProcess(@Valid @RequestBody RecyclingTipEntity recycling) {
        logger.info("Creating new recycling process");
        RecyclingTipEntity created = service.createRecyclingProcess(recycling);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecyclingTipEntity> updateRecyclingProcess(
            @PathVariable Long id,
            @Valid @RequestBody RecyclingTipEntity recycling) {
        logger.info("Updating recycling process with id: {}", id);
        return service.updateRecyclingProcess(id, recycling)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecyclingProcess(@PathVariable Long id) {
        logger.info("Deleting recycling process with id: {}", id);
        return service.deleteRecyclingProcess(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}

