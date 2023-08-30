package com.hajamodel.modealice.Service;

import com.hajamodel.modealice.Model.Cart;
import com.hajamodel.modealice.Repository.CartRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Set;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public boolean insert(Cart toInsert) {
        try {
            cartRepository.insert(toInsert);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Set<Cart> findAll() {
        try {
            return cartRepository.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(int id) {
        try {
            cartRepository.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cart findById(int id) {
        try {
            return cartRepository.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String update(int id, Cart newValue) {
        try {
            cartRepository.update(id, newValue);
            return "Update successful";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
