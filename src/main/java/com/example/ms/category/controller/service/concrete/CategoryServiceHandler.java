package com.example.ms.category.controller.service.concrete;

import com.example.ms.category.controller.dao.entity.CategoryEntity;
import com.example.ms.category.controller.dao.repository.CategoryRepository;
import com.example.ms.category.controller.service.abstraction.CategoryService;
import com.example.ms.category.exception.NotFoundException;
import com.example.ms.category.model.request.CreateCategoryRequest;
import com.example.ms.category.model.response.CategoryDto;
import com.example.ms.category.model.response.CreateCategoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.ms.category.exception.ExceptionConstants.CATEGORY_NOT_FOUND_CODE;
import static com.example.ms.category.exception.ExceptionConstants.CATEGORY_NOT_FOUND_MESSAGE;
import static com.example.ms.category.exception.ExceptionConstants.SUBCATEGORY_NOT_FOUND_CODE;
import static com.example.ms.category.exception.ExceptionConstants.SUBCATEGORY_NOT_FOUND_MESSAGE;
import static com.example.ms.category.mapper.CategoryMapper.CATEGORY_MAPPER;
import static com.example.ms.category.model.enums.CategoryStatus.ACTIVE;
import static com.example.ms.category.model.enums.CategoryStatus.DELETED;
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
    public CreateCategoryResponse getCategoryById(Long id){
        var entity = fetchCategoryIfExists(id);
        return CATEGORY_MAPPER.toCreateCategoryResponse(entity);
    }

    @Override
    public List<CategoryDto> getSubCategoriesByParentId(Long parentId){
        var entities = fetchActiveSubCategoriesIfExists(parentId);
        return entities.stream()
                .map(CATEGORY_MAPPER::toCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public CreateCategoryResponse updateCategoryNameById(Long id, String name){
        var entity = fetchActiveCategoryIfExists(id);
        entity.setName(name);
        entity = categoryRepository.save(entity);
        return CATEGORY_MAPPER.toCreateCategoryResponse(entity);
    }

    @Override
    public void deleteCategoryNameById(Long id){
        var entity = fetchActiveCategoryIfExists(id);
        entity.setStatus(DELETED);
        categoryRepository.save(entity);
    }

    private CategoryEntity fetchCategoryIfExists(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND_CODE, String.format(CATEGORY_NOT_FOUND_MESSAGE, id)));
    }

    private CategoryEntity fetchActiveCategoryIfExists(Long id){
        return categoryRepository.findByIdAndStatus(id, ACTIVE)
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND_CODE, String.format(CATEGORY_NOT_FOUND_MESSAGE, id)));
    }

    private List<CategoryEntity> fetchActiveSubCategoriesIfExists(Long parentId){
        var categories = categoryRepository.findAllByParentAndStatus(parentId, ACTIVE);
        if(categories.isEmpty()) throw new NotFoundException(SUBCATEGORY_NOT_FOUND_CODE, String.format(SUBCATEGORY_NOT_FOUND_MESSAGE, parentId));
        return categories;
    }
}
