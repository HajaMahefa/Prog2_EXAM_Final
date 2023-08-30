package com.hajamodel.modealice.Controller;

import com.hajamodel.modealice.Model.Category;
import com.hajamodel.modealice.Model.Product;
import com.hajamodel.modealice.Service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
@CrossOrigin
@RestController
public class CatergoryController {
    private final CategoryService categoryService;

    public CatergoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public Set<Category> getAll() {
        return categoryService.findAll();
    }

    @PostMapping("/insertCategory")
    public boolean insert(@RequestBody Category category) {
        return categoryService.insert(category);
    }

    @DeleteMapping("/deleteCategory/{id}")
    public String deleteById(@PathVariable int id) {
        return categoryService.deleteById(id);
    }

    @GetMapping("/selectCategoryById/{id}")
    public Category findById(@PathVariable int id) {
        return categoryService.findById(id);
    }

    @PutMapping("/updateCatergory/{id}")
    public String updateCategory(@PathVariable int id, @RequestBody Category newCategory  ) {
        return categoryService.update(id,  newCategory);
    }
}
