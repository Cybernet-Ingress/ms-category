package com.example.ms.category.controller.dao.repository;

import com.example.ms.category.controller.dao.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
}
