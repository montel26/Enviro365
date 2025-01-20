package com.enviro.assessment.grad001.MontelWood.Repository;

import com.enviro.assessment.grad001.MontelWood.Model.WasteCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing WasteCategoryEntity objects.
 * Extends JpaRepository to provide standard CRUD operations.
 * Inherits basic methods like findAll(), findById(), save(), delete(), etc.
 */

public interface WasteCategoryRepository extends JpaRepository<WasteCategoryEntity,Long> {
}
