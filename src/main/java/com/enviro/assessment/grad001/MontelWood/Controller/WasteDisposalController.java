package com.enviro.assessment.grad001.MontelWood.Controller;

import com.enviro.assessment.grad001.MontelWood.Model.WasteDisposalEntity;
import com.enviro.assessment.grad001.MontelWood.Service.WasteDisposalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

public class WasteDisposalController {
    private final WasteDisposalService service;

    @Autowired
    public WasteDisposalController(WasteDisposalService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<WasteDisposalEntity>> getAllDisposalMethods() {
        return ResponseEntity.ok(service.getAllDisposalMethods());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WasteDisposalEntity> getDisposalById(@PathVariable Long id) {
        return service.getDisposalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<WasteDisposalEntity>> getDisposalMethodsByCategory(@PathVariable Long categoryId) {
        List<WasteDisposalEntity> methods = service.getDisposalMethodsByCategory(categoryId);
        return ResponseEntity.ok(methods);
    }

    @PostMapping
    public ResponseEntity<WasteDisposalEntity> createDisposalMethod(@Valid @RequestBody WasteDisposalEntity disposal) {
        WasteDisposalEntity created = service.createDisposalMethod(disposal);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WasteDisposalEntity> updateDisposalMethod(
            @PathVariable Long id,
            @Valid @RequestBody WasteDisposalEntity disposal) {
        return service.updateDisposalMethod(id, disposal)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisposalMethod(@PathVariable Long id) {
        return service.deleteDisposalMethod(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
