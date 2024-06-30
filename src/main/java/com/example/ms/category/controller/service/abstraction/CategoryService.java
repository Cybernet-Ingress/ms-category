package com.example.ms.category.controller.service.abstraction;

import com.example.ms.category.model.request.CreateCategoryRequest;
import com.example.ms.category.model.response.CreateCategoryResponse;

import java.util.List;

public interface CategoryService {
    CreateCategoryResponse createCategory(CreateCategoryRequest request);

    List<CreateCategoryResponse> getSubCategoriesByParentId(Long parentId);
}
