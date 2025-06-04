package com.rentbook.demo.controller;

import com.rentbook.demo.busines.abstracts.ICategoryService;
import com.rentbook.demo.busines.concretes.CategoryManager;
import com.rentbook.demo.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/categories")
public class CategoryController {

    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping("/findAll")
    public List<Category> getAllCategories() {
        return iCategoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Optional<Category> getCategoryById(@PathVariable Long id) {
        return iCategoryService.getCategoryById(id);
    }

    @PostMapping("/save")
    public Category createCategory(@RequestBody Category category) {
        return iCategoryService.createCategory(category);
    }

    @PutMapping("/update/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return iCategoryService.updateCategory(id, category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        iCategoryService.deleteCategory(id);
    }

}











