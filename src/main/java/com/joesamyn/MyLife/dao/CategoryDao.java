package com.joesamyn.MyLife.dao;

import com.joesamyn.MyLife.model.Category;

import java.util.List;

public interface CategoryDao {
    void save(Category category);
    void delete(Category category);
    List<Category> findAll();
    Category findById(Long id);
}
