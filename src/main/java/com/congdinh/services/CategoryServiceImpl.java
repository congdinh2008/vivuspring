package com.congdinh.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.congdinh.dao.CategoryDAO;
import com.congdinh.dto.category.CategoryDTO;
import com.congdinh.entities.Category;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryDAO categoryDAO;

    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public List<CategoryDTO> getAll() {
        var categories = categoryDAO.getAll();
        return categories.stream().map(category -> {
            var categoryDTO = new CategoryDTO();
            categoryDTO.setId(category.getId());
            categoryDTO.setName(category.getName());
            categoryDTO.setDescription(category.getDescription());
            return categoryDTO;
        }).toList();
    }

    @Override
    public List<CategoryDTO> search(String keyword, int page, int size) {
        var categories = categoryDAO.search(keyword, page, size);
        return categories.stream().map(category -> {
            var categoryDTO = new CategoryDTO();
            categoryDTO.setId(category.getId());
            categoryDTO.setName(category.getName());
            categoryDTO.setDescription(category.getDescription());
            return categoryDTO;
        }).toList();
    }

    @Override
    public Optional<CategoryDTO> getById(Long id) {
        var category = categoryDAO.getById(id);
        if (category == null) {
            return Optional.empty();
        }
        var categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());

        return Optional.of(categoryDTO);
    }

    @Override
    public void create(CategoryDTO category) {
        if (category == null) {
            throw new IllegalArgumentException("Category is required");
        }

        var existingCategory = categoryDAO.getById(category.getId());
        if (existingCategory != null) {
            throw new IllegalArgumentException("Category with id " + category.getId() + " already exists");
        }

        var newCategory = new Category();
        newCategory.setName(category.getName());
        newCategory.setDescription(category.getDescription());

        categoryDAO.create(newCategory);
    }

    @Override
    public void update(CategoryDTO category) {
        if (category == null) {
            throw new IllegalArgumentException("Category is required");
        }

        var existingCategory = categoryDAO.getById(category.getId());
        if (existingCategory == null) {
            throw new IllegalArgumentException("Category with id " + category.getId() + " does not exist");
        }

        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());

        categoryDAO.update(existingCategory);
    }

    @Override
    public void delete(Long id) {
        var existingCategory = categoryDAO.getById(id);
        if (existingCategory == null) {
            throw new IllegalArgumentException("Category with id " + id + " does not exist");
        }

        categoryDAO.delete(id);
    }

    @Override
    public void delete(CategoryDTO category) {
        if (category == null) {
            throw new IllegalArgumentException("Category is required");
        }

        var existingCategory = categoryDAO.getById(category.getId());
        if (existingCategory == null) {
            throw new IllegalArgumentException("Category with id " + category.getId() + " does not exist");
        }

        categoryDAO.delete(existingCategory);
    }
}
