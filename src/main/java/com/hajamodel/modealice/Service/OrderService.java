package com.hajamodel.modealice.Service;

import com.hajamodel.modealice.Model.Order;
import com.hajamodel.modealice.Repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Set;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String insert(Order toInsert) {
        try {
            boolean success = orderRepository.insert(toInsert);
            if (success) {
                return "Successfully inserted an order.";
            } else {
                return "Error while inserting an order.";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Order> findAll() {
        try {
            return orderRepository.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<String> delete(int id) {
        try {
            orderRepository.deleteById(id);
            return ResponseEntity.ok("Order deleted successfully!");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete order", e);
        }
    }

    public Set<Order> findById(int id) {
        try {
            return orderRepository.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch order by ID", e);
        }
    }

    public String update(int id, Order newValue) {
        try {
            orderRepository.update(id, newValue);
            return "Order updated successfully.";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
