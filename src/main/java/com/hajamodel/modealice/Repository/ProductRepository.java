package com.hajamodel.modealice.Repository;

import com.hajamodel.modealice.DAO.DAOMethode;
import com.hajamodel.modealice.DAO.GenricDAO;
import com.hajamodel.modealice.Model.Cart;
import com.hajamodel.modealice.Model.Product;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Repository
public class ProductRepository implements DAOMethode<Product> {

    @Override
    public boolean insert(Product toInsert) throws SQLException {
        String sql = "INSERT INTO Product (name, description, price, category_id, size, gender, age, image_url, capacity) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = GenricDAO.getInstance().getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, toInsert.getName());
            preparedStatement.setString(2, toInsert.getDescription());
            preparedStatement.setFloat(3, toInsert.getPrice());
            preparedStatement.setInt(4, toInsert.getCategoryId());
            preparedStatement.setString(5, toInsert.getSize());
            preparedStatement.setString(6, toInsert.getGender());
            preparedStatement.setString(7, toInsert.getAge());
            preparedStatement.setString(8, toInsert.getImageUrl());
            preparedStatement.setInt(9, toInsert.getCapacity());

            int success = preparedStatement.executeUpdate();
            return success == 1;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public Set<Product> findAll() throws SQLException {
        Set<Product> products = new HashSet<>();
        String sql = "SELECT * FROM Product";
        try (PreparedStatement preparedStatement = GenricDAO.getInstance().getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                products.add(
                        new Product(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("description"),
                                resultSet.getFloat("price"),
                                resultSet.getInt("category_id"),
                                resultSet.getString("size"),
                                resultSet.getString("gender"),
                                resultSet.getString("age"),
                                resultSet.getString("image_url"),
                                resultSet.getInt("capacity")
                        )
                );
            }
            return products;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM Product WHERE id = ?";
        try (PreparedStatement preparedStatement = GenricDAO.getInstance().getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected <= 0) {
                throw new RuntimeException("Deletion failed for Product with id " + id);
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public Product findById(int id) throws SQLException {
        String sql = "SELECT * FROM Product WHERE id = ?";
        try (PreparedStatement preparedStatement = GenricDAO.getInstance().getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getFloat("price"),
                        resultSet.getInt("category_id"),
                        resultSet.getString("size"),
                        resultSet.getString("gender"),
                        resultSet.getString("age"),
                        resultSet.getString("image_url"),
                        resultSet.getInt("capacity")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage() + " id " + id + " not found");
        }
    }

    @Override
    public void update(int id, Product newValue) throws SQLException {
        String sql = "UPDATE Product SET name = ?, description = ?, price = ?, category_id = ?, size = ?, gender = ?, age = ?, image_url = ?, capacity = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = GenricDAO.getInstance().getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, newValue.getName());
            preparedStatement.setString(2, newValue.getDescription());
            preparedStatement.setFloat(3, newValue.getPrice());
            preparedStatement.setInt(4, newValue.getCategoryId());
            preparedStatement.setString(5, newValue.getSize());
            preparedStatement.setString(6, newValue.getGender());
            preparedStatement.setString(7, newValue.getAge());
            preparedStatement.setString(8, newValue.getImageUrl());
            preparedStatement.setInt(9, newValue.getCapacity());
            preparedStatement.setInt(10, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }


}