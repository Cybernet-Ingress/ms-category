package com.example.ms.category.controller.service.concrete;

import com.example.ms.category.controller.dao.repository.CategoryRepository;
import com.example.ms.category.controller.service.abstraction.CategoryService;
import com.example.ms.category.model.request.CreateCategoryRequest;
import com.example.ms.category.model.response.CreateCategoryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import static com.example.ms.category.mapper.CategoryMapper.CATEGORY_MAPPER;
import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class CategoryServiceHandler implements CategoryService {
    CategoryRepository categoryRepository;
    public CreateCategoryResponse createCategory(@Valid @RequestBody CreateCategoryRequest request){
        var entity = CATEGORY_MAPPER.buildCategoryEntity(request);
        entity = categoryRepository.save(entity);
        return CATEGORY_MAPPER.toCreateCategoryResponse(entity);
    }
}
