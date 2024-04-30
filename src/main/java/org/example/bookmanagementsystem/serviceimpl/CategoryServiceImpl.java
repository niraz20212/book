package org.example.bookmanagementsystem.serviceimpl;

import org.example.bookmanagementsystem.entity.Category;
import org.example.bookmanagementsystem.exceptionhandler.CustomException;
import org.example.bookmanagementsystem.repository.CategoryRepository;
import org.example.bookmanagementsystem.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category addCategory(Category category) {
        Category categorySaved = categoryRepository.findByName(category.getName());
        if (categorySaved != null) {
            throw new CustomException("Category already exists");
        }

        return categoryRepository.save(category);
    }



    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
