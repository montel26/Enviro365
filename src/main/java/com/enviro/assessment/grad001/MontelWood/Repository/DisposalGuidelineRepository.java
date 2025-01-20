package com.enviro.assessment.grad001.MontelWood.Repository;


import com.enviro.assessment.grad001.MontelWood.Model.WasteDisposalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing WasteDisposalEntity objects.
 * Extends JpaRepository to inherit standard CRUD operations and custom finder methods.
 */
public interface DisposalGuidelineRepository extends JpaRepository<WasteDisposalEntity,Long> {
    List<WasteDisposalEntity> findByWasteCategoryId(Long categoryId);
}
