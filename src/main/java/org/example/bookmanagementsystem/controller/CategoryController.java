package org.example.bookmanagementsystem.controller;

import org.example.bookmanagementsystem.entity.Category;
import org.example.bookmanagementsystem.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/save")
    public Category addCategory(Category category) {
     return  categoryService.addCategory(category);
    }
    @PutMapping("update")
    public Category updateCategory(@RequestBody  Category category){
        return categoryService.updateCategory(category);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable int id){
        categoryService.deleteCategory(id);
    }
    @GetMapping("/getAll")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }
}
