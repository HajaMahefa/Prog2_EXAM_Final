package com.hajamodel.modealice.Repository;

import com.hajamodel.modealice.DAO.DAOMethode;
import com.hajamodel.modealice.DAO.GenricDAO;
import com.hajamodel.modealice.Model.Cart;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Repository
public class CartRepository implements DAOMethode<Cart> {

    @Override
    public boolean insert(Cart cart) throws SQLException {
        String sql = "INSERT INTO Cart (user_id, creation_date, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = GenricDAO.getInstance().getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, cart.getUserId());
            preparedStatement.setTimestamp(2, new java.sql.Timestamp(cart.getCreationDate().getTime()));
            preparedStatement.setInt(3, cart.getQuantity());

            int success = preparedStatement.executeUpdate();
            return success == 1;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public Set<Cart> findAll() throws SQLException {
        Set<Cart> carts = new HashSet<>();
        String sql = "SELECT * FROM Cart";
        try (PreparedStatement preparedStatement = GenricDAO.getInstance().getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                carts.add(
                        new Cart(
                                resultSet.getInt("id"),
                                resultSet.getInt("user_id"),
                                resultSet.getTimestamp("creation_date"),
                                resultSet.getInt("quantity")
                        )
                );
            }
            return carts;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM Cart WHERE id = ?";
        try (PreparedStatement preparedStatement = GenricDAO.getInstance().getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected <= 0) {
                throw new RuntimeException("Deletion failed for Cart with id " + id);
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public Cart findById(int id) throws SQLException {
        String sql = "SELECT * FROM Cart WHERE id = ?";
        try (PreparedStatement preparedStatement = GenricDAO.getInstance().getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Cart(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getTimestamp("creation_date"),
                        resultSet.getInt("quantity")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage() + " id " + id + " not found");
        }
    }

    @Override
    public void update(int id, Cart newValue) throws SQLException {
        String sql = "UPDATE Cart SET user_id = ?, creation_date = ?, quantity = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = GenricDAO.getInstance().getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, newValue.getUserId());
            preparedStatement.setTimestamp(2, newValue.getCreationDate());
            preparedStatement.setInt(3, newValue.getQuantity());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

}
