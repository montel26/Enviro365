package com.enviro.assessment.grad001.MontelWood.Controller;

import com.enviro.assessment.grad001.MontelWood.Model.DisposalGuidelineEntity;
import com.enviro.assessment.grad001.MontelWood.Model.RecyclingTipEntity;
import com.enviro.assessment.grad001.MontelWood.Model.WasteCategoryEntity;
import com.enviro.assessment.grad001.MontelWood.Service.DisposalService;
import com.enviro.assessment.grad001.MontelWood.Service.RecycleTipsService;
import com.enviro.assessment.grad001.MontelWood.Service.WasteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/waste")
public class SaveEarthController {
    @Autowired
    private WasteService wasteService;

    @Autowired
    private DisposalService disposalService;

    @Autowired
    private RecycleTipsService recycleTipsService;

    // Waste Category Endpoints
    @GetMapping("/categories")
    public ResponseEntity<List<WasteCategoryEntity>> getAllCategories() {
        return ResponseEntity.ok(wasteService.getAllCategory());
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<WasteCategoryEntity> getCategoryById(@PathVariable Long id) {
        return wasteService.getAllCategoryId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/categories")
    public ResponseEntity<WasteCategoryEntity> createCategory(@RequestBody WasteCategoryEntity category) {
        return new ResponseEntity<>(wasteService.saveCategory(category), HttpStatus.CREATED);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        try {
            wasteService.deleteCategory(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Disposal Guidelines Endpoints
    @GetMapping("/guidelines")
    public ResponseEntity<List<DisposalGuidelineEntity>> getAllGuidelines() {
        return ResponseEntity.ok(disposalService.getGuideLines());
    }

    @GetMapping("/guidelines/{id}")
    public ResponseEntity<DisposalGuidelineEntity> getGuidelineById(@PathVariable Long id) {
        return disposalService.getGuideLinesById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/guidelines")
    public ResponseEntity<DisposalGuidelineEntity> createGuideline(@RequestBody DisposalGuidelineEntity guideline) {
        return new ResponseEntity<>(disposalService.saveGuideLine(guideline), HttpStatus.CREATED);
    }

    @DeleteMapping("/guidelines/{id}")
    public ResponseEntity<Void> deleteGuideline(@PathVariable Long id) {
        try {
            disposalService.deleteDisposalGuideline(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Recycling Tips Endpoints
    @GetMapping("/tips")
    public ResponseEntity<List<RecyclingTipEntity>> getAllRecyclingTips() {
        return ResponseEntity.ok(recycleTipsService.getRecyclingTips());
    }

    @GetMapping("/tips/{id}")
    public ResponseEntity<RecyclingTipEntity> getRecyclingTipById(@PathVariable Long id) {
        return recycleTipsService.getRecyclingTipsById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/tips")
    public ResponseEntity<RecyclingTipEntity> createRecyclingTip(@RequestBody RecyclingTipEntity tip) {
        return new ResponseEntity<>(recycleTipsService.saveRecyclingTips(tip), HttpStatus.CREATED);
    }

    @DeleteMapping("/tips/{id}")
    public ResponseEntity<Void> deleteRecyclingTip(@PathVariable Long id) {
        try {
            recycleTipsService.deleteRecyclingTips(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Health Check Endpoint
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Waste Management Service is running");
    }
}

