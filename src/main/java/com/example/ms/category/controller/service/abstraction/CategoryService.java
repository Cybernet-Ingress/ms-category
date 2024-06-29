package com.example.ms.category.controller.service.abstraction;

import com.example.ms.category.model.request.CreateCategoryRequest;
import com.example.ms.category.model.response.CreateCategoryResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface CategoryService {
    CreateCategoryResponse createCategory(@Valid @RequestBody CreateCategoryRequest request);
}
