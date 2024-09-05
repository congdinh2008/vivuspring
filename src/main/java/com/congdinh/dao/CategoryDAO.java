package com.congdinh.dao;

import java.util.List;

import com.congdinh.entities.Category;

public interface CategoryDAO {
    List<Category> getAll();

    List<Category> search(String keyword, int page, int size);

    Category getById(Long id);

    void create(Category category);

    void update(Category category);

    void delete(Long id);

    void delete(Category category);
}
