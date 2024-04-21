package org.example.bookmanagementsystem.service;

import org.example.bookmanagementsystem.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {
    Category addCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategory(int id);
    List<Category> getAllCategories();

}
