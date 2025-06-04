package com.rentbook.demo.busines.concretes;

import com.rentbook.demo.busines.abstracts.ICategoryService;
import com.rentbook.demo.dao.CategoryRepository;
import com.rentbook.demo.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryManager implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {

        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {

        return categoryRepository.findById(id);
    }

   public Category createCategory(Category category){
        return categoryRepository.save(category);
   }

   public Category updateCategory(Long id, Category updatedCategory){
        return categoryRepository.findById(id).map(category -> {
            category.setName(updatedCategory.getName());
            return categoryRepository.save(category);
        }).orElseThrow(()-> new RuntimeException("category not found"));
   }

   public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
   }
}













