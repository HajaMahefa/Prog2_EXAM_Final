package com.hajamodel.modealice.Service;

import com.hajamodel.modealice.Model.Category;
import com.hajamodel.modealice.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Set;

@Service
public class CategoryService  {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public boolean insert(Category toInsert) {
        try {
            categoryRepository.insert(toInsert);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Category> findAll() {
        try {
            return categoryRepository.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String deleteById(int id) {
        try {
            categoryRepository.deleteById(id);
            return "Remove successfully";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Category findById(int id) {
        try {
            return categoryRepository.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String update(int id, Category newValue) {
        try {
            categoryRepository.update(id, newValue);
            return "Success";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}