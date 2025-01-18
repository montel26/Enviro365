package com.enviro.assessment.grad001.MontelWood.Service;

import com.enviro.assessment.grad001.MontelWood.Model.RecyclingTipEntity;
import com.enviro.assessment.grad001.MontelWood.Repository.RecyclingTipsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecycleTipService {
    private final RecyclingTipsRepository repository;

    @Autowired
    public RecycleTipService(RecyclingTipsRepository repository) {
        this.repository = repository;
    }

    public List<RecyclingTipEntity> getAllRecyclingProcesses() {
        return repository.findAll();
    }

    public Optional<RecyclingTipEntity> getRecyclingById(Long id) {
        return repository.findById(id);
    }

    public List<RecyclingTipEntity> getRecyclingProcessesByCategory(Long categoryId) {
        return repository.findByWasteCategoryId(categoryId);
    }

    public RecyclingTipEntity createRecyclingProcess(RecyclingTipEntity recycling) {
        return repository.save(recycling);
    }

    public Optional<RecyclingTipEntity> updateRecyclingProcess(Long id, RecyclingTipEntity updatedRecycling) {
        return repository.findById(id)
                .map(existingRecycling -> {
                    existingRecycling.setProcessName(updatedRecycling.getProcessName());
                    existingRecycling.setDescription(updatedRecycling.getDescription());
                    existingRecycling.setWasteCategory(updatedRecycling.getWasteCategory());
                    existingRecycling.setProcessingSteps(updatedRecycling.getProcessingSteps());
                    existingRecycling.setBenefitsDescription(updatedRecycling.getBenefitsDescription());
                    existingRecycling.setResourceSavings(updatedRecycling.getResourceSavings());
                    return repository.save(existingRecycling);
                });
    }

    public boolean deleteRecyclingProcess(Long id) {
        return repository.findById(id)
                .map(recycling -> {
                    repository.delete(recycling);
                    return true;
                })
                .orElse(false);
    }
}
