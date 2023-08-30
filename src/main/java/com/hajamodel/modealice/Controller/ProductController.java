package com.hajamodel.modealice.Controller;

import com.hajamodel.modealice.Service.ProductService;
import com.hajamodel.modealice.Model.Product;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Set;

@AllArgsConstructor
@RestController
public class ProductController {
    private ProductService productService;

    @GetMapping("/products")
    public Set<Product> getAll() {
        return productService.findAll();
    }

    @PostMapping("/insertProduct")
    public String addProduct(@RequestBody Product product) {
      return productService.insert(product);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        return productService.delete(id);
    }

    @GetMapping("/selectProductById/{id}")
    public Product findProductById(@PathVariable int id) {
        return productService.findById(id);
    }

    @PutMapping("/updateProduct/{id}")
    public String updateProduct(@PathVariable int id, @RequestBody Product newProduct) {
        return productService.update(id, newProduct);
    }
}