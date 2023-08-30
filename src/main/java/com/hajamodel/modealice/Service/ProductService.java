package com.hajamodel.modealice.Service;

import com.hajamodel.modealice.Model.Cart;
import com.hajamodel.modealice.Model.Product;
import com.hajamodel.modealice.Repository.CartRepository;
import com.hajamodel.modealice.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Set;
@Service
public class ProductService {
    private ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public String insert(Product toInsert){
        try {

            boolean success = productRepository.insert(toInsert);
            if (success){
                return "successfully";
            }else{
                return "erreur";
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Set<Product> findAll() {
        try {
            return  productRepository.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<String> delete(int id){
        try {
            productRepository.deleteById(id);
            return ResponseEntity.ok("Produit supprimé avec succès !");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delet user", e);
        }
    }

    public Product findById(int id) {
        try {
            return productRepository.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String update(int id, Product newValue){
        try {
            productRepository.update(id , newValue);
            return "update successfully";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
