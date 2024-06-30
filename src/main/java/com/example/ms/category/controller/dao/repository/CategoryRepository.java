package com.example.ms.category.controller.dao.repository;

import com.example.ms.category.controller.dao.entity.CategoryEntity;
import com.example.ms.category.model.enums.CategoryStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
    List<CategoryEntity> findAllByParentAndStatus(Long id, CategoryStatus status);

    Optional<CategoryEntity> findByIdAndStatus(Long id, CategoryStatus status);
}
