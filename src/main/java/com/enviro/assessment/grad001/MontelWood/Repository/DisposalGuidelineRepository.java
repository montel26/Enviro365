package com.enviro.assessment.grad001.MontelWood.Repository;


import com.enviro.assessment.grad001.MontelWood.Model.WasteDisposalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisposalGuidelineRepository extends JpaRepository<WasteDisposalEntity,Long> {
    List<WasteDisposalEntity> findByWasteCategoryId(Long categoryId);
}
