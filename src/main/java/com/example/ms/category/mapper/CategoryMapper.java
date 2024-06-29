package com.example.ms.category.mapper;

import com.example.ms.category.controller.dao.entity.CategoryEntity;
import com.example.ms.category.model.request.CreateCategoryRequest;
import com.example.ms.category.model.response.CreateCategoryResponse;

import java.time.LocalDateTime;

import static com.example.ms.category.model.enums.CategoryStatus.ACTIVE;

public enum CategoryMapper {
    CATEGORY_MAPPER;

    public CategoryEntity buildCategoryEntity(CreateCategoryRequest request){
        return CategoryEntity.builder()
                .name(request.getName())
                .parent(request.getParent())
                .priority(request.getPriority())
                .createdAt(LocalDateTime.now())
                .status(ACTIVE)
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public CreateCategoryResponse toCreateCategoryResponse(CategoryEntity entity){
        return CreateCategoryResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .parent(entity.getParent())
                .priority(entity.getPriority())
                .createdAt(entity.getCreatedAt())
                .status(entity.getStatus())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
