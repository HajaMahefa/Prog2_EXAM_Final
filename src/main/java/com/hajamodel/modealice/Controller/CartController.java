package com.hajamodel.modealice.Controller;

import com.hajamodel.modealice.Model.Cart;
import com.hajamodel.modealice.Service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/carts")
    public Set<Cart> getAll() {
        return cartService.findAll();
    }

    @PostMapping("/insertCart")
    public boolean insert(@RequestBody Cart cart) {
        return cartService.insert(cart);
    }

    @DeleteMapping("/deleteCart/{id}")
    public void deleteById(@PathVariable int id) {
        cartService.deleteById(id);
    }

    @GetMapping("/selectCartById/{id}")
    public Cart findById(@PathVariable int id) {
        return cartService.findById(id);
    }

    @PutMapping("/updateCart/{id}")
    public String updateCart(@PathVariable int id, @RequestBody Cart newCart  ) {
        return cartService.update(id,  newCart);
    }
}
