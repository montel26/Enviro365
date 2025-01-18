package com.enviro.assessment.grad001.MontelWood.Service;


import com.enviro.assessment.grad001.MontelWood.Model.WasteCategoryEntity;
import com.enviro.assessment.grad001.MontelWood.Repository.WasteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WasteService {

    //Simple dependency injection
    @Autowired
    WasteCategoryRepository repository;

    public List<WasteCategoryEntity> getAllCategory(){
        return repository.findAll();
    }

    public Optional<WasteCategoryEntity> getAllCategoryId(Long Id){
        return repository.findById(Id);
    }

    public WasteCategoryEntity saveCategory(WasteCategoryEntity category){
        return repository.save(category);

    }

    public void deleteCategory(Long Id){
        if (!repository.existsById(Id)) {
            throw new RuntimeException("Category not found with ID: " + Id);
        }repository.deleteById(Id);
    }
}
