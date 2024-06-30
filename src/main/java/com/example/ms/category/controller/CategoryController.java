package com.example.ms.category.controller;

import com.example.ms.category.controller.service.abstraction.CategoryService;
import com.example.ms.category.model.request.CreateCategoryRequest;
import com.example.ms.category.model.response.CreateCategoryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/categories")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class CategoryController {
    CategoryService categoryService;

    @PostMapping
    @ResponseStatus(CREATED)
    public CreateCategoryResponse createCategory(@Valid @RequestBody CreateCategoryRequest request){
        return categoryService.createCategory(request);
    }

    @GetMapping("/parent/{parentId}")
    public List<CreateCategoryResponse> getSubCategoriesByParentId(@PathVariable Long parentId){
        if(parentId == null) parentId = 0L;
        return categoryService.getSubCategoriesByParentId(parentId);
    }
}
