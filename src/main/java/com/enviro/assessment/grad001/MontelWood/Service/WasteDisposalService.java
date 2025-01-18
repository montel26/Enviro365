package com.enviro.assessment.grad001.MontelWood.Service;

import com.enviro.assessment.grad001.MontelWood.Model.WasteDisposalEntity;
import com.enviro.assessment.grad001.MontelWood.Repository.DisposalGuidelineRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WasteDisposalService {

    private final DisposalGuidelineRepository repository;

    @Autowired
    public WasteDisposalService(DisposalGuidelineRepository repository) {
        this.repository = repository;
    }

    public List<WasteDisposalEntity> getAllDisposalMethods() {
        return repository.findAll();
    }

    public Optional<WasteDisposalEntity> getDisposalById(Long id) {
        return repository.findById(id);
    }

    public List<WasteDisposalEntity> getDisposalMethodsByCategory(Long categoryId) {
        return repository.findByWasteCategoryId(categoryId);
    }

    public WasteDisposalEntity createDisposalMethod(WasteDisposalEntity disposal) {
        return repository.save(disposal);
    }

    public Optional<WasteDisposalEntity> updateDisposalMethod(Long id, WasteDisposalEntity updatedDisposal) {
        return repository.findById(id)
                .map(existingDisposal -> {
                    existingDisposal.setMethodName(updatedDisposal.getMethodName());
                    existingDisposal.setDescription(updatedDisposal.getDescription());
                    existingDisposal.setWasteCategory(updatedDisposal.getWasteCategory());
                    existingDisposal.setSafetyPrecautions(updatedDisposal.getSafetyPrecautions());
                    existingDisposal.setEnvironmentalImpact(updatedDisposal.getEnvironmentalImpact());
                    return repository.save(existingDisposal);
                });
    }

    public boolean deleteDisposalMethod(Long id) {
        return repository.findById(id)
                .map(disposal -> {
                    repository.delete(disposal);
                    return true;
                })
                .orElse(false);
    }
}
