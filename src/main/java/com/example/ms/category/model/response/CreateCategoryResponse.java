package com.example.ms.category.model.response;

import com.example.ms.category.model.enums.CategoryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@Builder
public class CreateCategoryResponse {
    Long id;

    String name;

    Long parent;

    Integer priority;

    LocalDateTime createdAt;

    CategoryStatus status;

    LocalDateTime updatedAt;
}
