package com.example.ms.category.controller.service.concrete;

import com.example.ms.category.controller.dao.entity.CategoryEntity;
import com.example.ms.category.controller.dao.repository.CategoryRepository;
import com.example.ms.category.controller.service.abstraction.CategoryService;
import com.example.ms.category.model.request.CreateCategoryRequest;
import com.example.ms.category.model.response.CategoryDto;
import com.example.ms.category.model.response.CreateCategoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.ms.category.mapper.CategoryMapper.CATEGORY_MAPPER;
import static com.example.ms.category.model.enums.CategoryStatus.ACTIVE;
import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class CategoryServiceHandler implements CategoryService {
    CategoryRepository categoryRepository;

    @Override
    public CreateCategoryResponse createCategory(CreateCategoryRequest request){
        var entity = CATEGORY_MAPPER.buildCategoryEntity(request);
        entity = categoryRepository.save(entity);
        return CATEGORY_MAPPER.toCreateCategoryResponse(entity);
    }

    @Override
    public List<CategoryDto> getSubCategoriesByParentId(Long parentId){
        var entities = fetchActiveSubCategoriesIfExists(parentId);
        return entities.stream()
                .map(CATEGORY_MAPPER::toCategoryDto)
                .collect(Collectors.toList());
    }

    private CategoryEntity fetchActiveCategoryIfExists(Long id){
        return categoryRepository.findByIdAndStatus(id, ACTIVE)
                .orElseThrow(() -> new RuntimeException("category not found with id: " + id));
    }

    private List<CategoryEntity> fetchActiveSubCategoriesIfExists(Long parentId){
        var categories = categoryRepository.findAllByParentAndStatus(parentId, ACTIVE);
        if(categories.isEmpty()) throw new RuntimeException("No active subcategories found with parentId: " + parentId);
        return categories;
    }
}
