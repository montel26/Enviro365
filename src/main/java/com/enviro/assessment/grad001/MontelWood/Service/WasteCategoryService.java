package com.enviro.assessment.grad001.MontelWood.Service;

import com.enviro.assessment.grad001.MontelWood.Model.WasteCategoryEntity;
import com.enviro.assessment.grad001.MontelWood.Repository.WasteCategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WasteCategoryService {

    private final WasteCategoryRepository repository;

    @Autowired
    public WasteCategoryService(WasteCategoryRepository repository) {
        this.repository = repository;
    }

    public List<WasteCategoryEntity> getAllCategories() {
        return repository.findAll();
    }

    public Optional<WasteCategoryEntity> getCategoryById(Long id) {
        return repository.findById(id);
    }

    public WasteCategoryEntity createCategory(WasteCategoryEntity category) {
        return repository.save(category);
    }

    public Optional<WasteCategoryEntity> updateCategory(Long id, WasteCategoryEntity updatedCategory) {
        return repository.findById(id)
                .map(existingCategory -> {
                    existingCategory.setMethodName(updatedCategory.getMethodName());
                    existingCategory.setDescription(updatedCategory.getDescription());
                    existingCategory.setDisposalGuidelines(updatedCategory.getDisposalGuidelines());
                    existingCategory.setRecyclingTips(updatedCategory.getRecyclingTips());
                    return repository.save(existingCategory);
                });
    }

    public boolean deleteCategory(Long id) {
        return repository.findById(id)
                .map(category -> {
                    repository.delete(category);
                    return true;
                })
                .orElse(false);
    }

}
