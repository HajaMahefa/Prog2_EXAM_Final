package com.hajamodel.modealice.Controller;

import com.hajamodel.modealice.Model.Order;
import com.hajamodel.modealice.Service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public Set<Order> getAll() {
        return orderService.findAll();
    }

    @PostMapping("/insertOrder")
    public String insert(@RequestBody Order order) {
        return orderService.insert(order);
    }

    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<String>  deleteById(@PathVariable int id) {
        return orderService.delete(id);
    }

    @GetMapping("/selectOrderById/{id}")
    public Set<Order> findById(@PathVariable int id) {
        return orderService.findById(id);
    }

    @PutMapping("/updateOrder/{id}")
    public String updateOrder(@PathVariable int id, @RequestBody Order newOrder) {
        return orderService.update(id, newOrder);
    }
}
