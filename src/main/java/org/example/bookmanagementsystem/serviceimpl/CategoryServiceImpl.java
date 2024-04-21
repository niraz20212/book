package org.example.bookmanagementsystem.serviceimpl;

import org.example.bookmanagementsystem.entity.Category;
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

        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        Optional<Category> categoryOptional = categoryRepository.findById(category.getId());
        if (categoryOptional.isPresent()) {
            Category categoryToUpdate = categoryOptional.get();
            categoryToUpdate.setName(category.getName());
            categoryToUpdate.setDescription(category.getDescription());
            return categoryRepository.save(categoryToUpdate);
        }else {
            throw new RuntimeException("Category not found");
        }

    }

    @Override
    public void deleteCategory(int id) {
   categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return  categoryRepository.findAll();
    }
}
