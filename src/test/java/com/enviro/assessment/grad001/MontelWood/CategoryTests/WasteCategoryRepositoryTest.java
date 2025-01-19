package com.enviro.assessment.grad001.MontelWood.CategoryTests;

import com.enviro.assessment.grad001.MontelWood.Model.WasteCategoryEntity;
import com.enviro.assessment.grad001.MontelWood.Repository.WasteCategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class WasteCategoryRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private WasteCategoryRepository repository;




    @Test
    void findById_WhenNotExists_ShouldReturnEmpty() {
        Optional<WasteCategoryEntity> found = repository.findById(999L);
        assertTrue(found.isEmpty());
    }


}
