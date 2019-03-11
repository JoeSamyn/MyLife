package com.joesamyn.MyLife.service;

import com.joesamyn.MyLife.model.Category;

import java.util.List;

public interface CategoryService {
    void save(Category category);
    void delete(Category category);
    List<Category> findAll();
    Category findById(Long id);
}
