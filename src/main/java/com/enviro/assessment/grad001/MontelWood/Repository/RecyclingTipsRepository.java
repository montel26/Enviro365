package com.enviro.assessment.grad001.MontelWood.Repository;

import com.enviro.assessment.grad001.MontelWood.Model.RecyclingTipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing RecyclingTipEntity objects.
 * Extends JpaRepository to inherit standard CRUD operations .
 */

public interface RecyclingTipsRepository extends JpaRepository<RecyclingTipEntity,Long> {
    List<RecyclingTipEntity> findByWasteCategoryId(Long categoryId);
}
