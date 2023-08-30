package com.hajamodel.modealice.Repository;

import com.hajamodel.modealice.DAO.GenricDAO;
import com.hajamodel.modealice.Model.Order;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Repository
public class OrderRepository {

    public boolean insert(Order toInsert) throws SQLException {
        String sql = "INSERT INTO \"order\" (user_id, order_date, total_amount) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = GenricDAO.getInstance().getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, toInsert.getUserId());
            preparedStatement.setDate(2, Date.valueOf(toInsert.getOrderDate()));
            preparedStatement.setBigDecimal(3, toInsert.getTotalAmount());

            int success = preparedStatement.executeUpdate();
            return success == 1;
        } catch (SQLException e) {
            throw e;
        }
    }

    public Set<Order> findById(int userId) throws SQLException {
        Set<Order> orders = new HashSet<>();
        String sql = "SELECT * FROM \"order\" WHERE user_id = ?";
        try (PreparedStatement preparedStatement = GenricDAO.getInstance().getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                orders.add(
                        new Order(
                                resultSet.getInt("id"),
                                resultSet.getInt("user_id"),
                                resultSet.getString("order_date"),
                                resultSet.getBigDecimal("total_amount")
                        )
                );
            }
            return orders;
        } catch (SQLException e) {
            throw e;
        }
    }

    public Set<Order> findAll() throws SQLException {
        Set<Order> orders = new HashSet<>();
        String sql = "SELECT * FROM \"order\"";
        try (PreparedStatement preparedStatement = GenricDAO.getInstance().getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                orders.add(
                        new Order(
                                resultSet.getInt("id"),
                                resultSet.getInt("user_id"),
                                resultSet.getString("order_date"),
                                resultSet.getBigDecimal("total_amount")
                        )
                );
            }
            return orders;
        } catch (SQLException e) {
            throw e;
        }
    }

    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM \"order\" WHERE id = ?";
        try (PreparedStatement preparedStatement = GenricDAO.getInstance().getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected <= 0) {
                throw new RuntimeException("Deletion failed for Order with id " + id);
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    public void update(int id, Order newValue) throws SQLException {
        String sql = "UPDATE \"order\" SET user_id = ?, order_date = ?, total_amount = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = GenricDAO.getInstance().getConnection().prepareStatement(sql)) {
            preparedStatement.setDate(2, Date.valueOf(newValue.getOrderDate())); // Utiliser setDate pour les dates
            preparedStatement.setBigDecimal(3, newValue.getTotalAmount());
            preparedStatement.setInt(4, id); // Changer l'ordre des paramètres pour correspondre à la requête SQL
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
}
