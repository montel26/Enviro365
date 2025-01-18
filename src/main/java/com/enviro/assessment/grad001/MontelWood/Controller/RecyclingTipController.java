package com.enviro.assessment.grad001.MontelWood.Controller;

import com.enviro.assessment.grad001.MontelWood.Model.RecyclingTipEntity;
import com.enviro.assessment.grad001.MontelWood.Service.RecycleTipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

public class RecyclingTipController {
    private final RecycleTipService service;

    @Autowired
    public RecyclingTipController(RecycleTipService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RecyclingTipEntity>> getAllRecyclingProcesses() {
        return ResponseEntity.ok(service.getAllRecyclingProcesses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecyclingTipEntity> getRecyclingById(@PathVariable Long id) {
        return service.getRecyclingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<RecyclingTipEntity>> getRecyclingProcessesByCategory(@PathVariable Long categoryId) {
        List<RecyclingTipEntity> processes = service.getRecyclingProcessesByCategory(categoryId);
        return ResponseEntity.ok(processes);
    }

    @PostMapping
    public ResponseEntity<RecyclingTipEntity> createRecyclingProcess(@Valid @RequestBody RecyclingTipEntity recycling) {
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
        return service.updateRecyclingProcess(id, recycling)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecyclingProcess(@PathVariable Long id) {
        return service.deleteRecyclingProcess(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
