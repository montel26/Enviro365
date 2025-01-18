package com.enviro.assessment.grad001.MontelWood.Controller;

import com.enviro.assessment.grad001.MontelWood.Model.WasteCategoryEntity;
import com.enviro.assessment.grad001.MontelWood.Service.WasteCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/waste-categories")
public class WasteCategoryController {

    private final WasteCategoryService service;

    @Autowired
    public WasteCategoryController(WasteCategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<WasteCategoryEntity>> getAllCategories() {
        return ResponseEntity.ok(service.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WasteCategoryEntity> getCategoryById(@PathVariable Long id) {
        return service.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<WasteCategoryEntity> createCategory(@Valid @RequestBody WasteCategoryEntity category) {
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
        return service.updateCategory(id, category)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        return service.deleteCategory(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
