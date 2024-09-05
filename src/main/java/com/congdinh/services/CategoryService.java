package com.congdinh.services;

import java.util.List;
import java.util.Optional;

import com.congdinh.dto.category.CategoryDTO;

public interface CategoryService {
    List<CategoryDTO> getAll();

    List<CategoryDTO> search(String keyword, int page, int size);

    Optional<CategoryDTO> getById(Long id);

    void create(CategoryDTO category);

    void update(CategoryDTO category);

    void delete(Long id);

    void delete(CategoryDTO category);
}
