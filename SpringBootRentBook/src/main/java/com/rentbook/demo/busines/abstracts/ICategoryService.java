package com.rentbook.demo.busines.abstracts;

import com.rentbook.demo.entity.Category;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;


public interface ICategoryService {

    List<Category> getAllCategories();

    Optional<Category> getCategoryById(@PathVariable Long id);

    Category createCategory(@RequestBody Category category);

    Category updateCategory(@PathVariable Long id, @RequestBody Category category);

    void deleteCategory(@PathVariable Long id);
}
