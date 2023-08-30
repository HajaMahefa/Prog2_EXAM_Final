package com.hajamodel.modealice.Repository;

import com.hajamodel.modealice.DAO.DAOMethode;
import com.hajamodel.modealice.DAO.GenricDAO;
import com.hajamodel.modealice.Model.Category;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Repository
public class CategoryRepository implements DAOMethode<Category> {
    public boolean insert(Category toInsert) throws SQLException {
        String sql = "INSERT INTO Category (id, name) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = GenricDAO.getInstance().getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, toInsert.getId());
            preparedStatement.setString(2, toInsert.getName());
            int success = preparedStatement.executeUpdate();
            return success == 1;
        } catch (SQLException e) {
            throw e;
        }
    }

    public Set<Category> findAll() throws SQLException {
        Set<Category> categories = new HashSet<>();
        String sql = "SELECT * FROM Category";
        ResultSet resultSet = GenricDAO.getInstance().getResult(sql);
        while (resultSet.next()) {
            categories.add(
                    new Category(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                    )
            );
        }
        return categories;
    }

    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM Category WHERE id = ?";
        try (PreparedStatement preparedStatement = GenricDAO.getInstance().getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public Category findById(int id) throws SQLException {
        String sql = "SELECT * FROM Category WHERE id = ?";
        try (PreparedStatement preparedStatement = GenricDAO.getInstance().getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Category(
                        resultSet.getInt("id"),
                        resultSet.getString("name")

                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    public void update(int id, Category newValue) throws SQLException {
        String sql = "UPDATE Category SET name = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = GenricDAO.getInstance().getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, newValue.getName());
            preparedStatement.setInt(2, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating category failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw e;
        }
    }
}
