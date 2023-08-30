package com.hajamodel.modealice.Repository;

import com.hajamodel.modealice.DAO.DAOMethode;
import com.hajamodel.modealice.DAO.GenricDAO;
import com.hajamodel.modealice.DTO.UserDto;
import com.hajamodel.modealice.Model.User;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Repository
public class UserRepository implements DAOMethode<User> {


    @Override
    public boolean insert(User toInsert) throws SQLException {
        String sql = "INSERT INTO \"user\" (last_name, first_name, email, password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = GenricDAO.getInstance().getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, toInsert.getLastName());
            preparedStatement.setString(2, toInsert.getFirstName());
            preparedStatement.setString(3, toInsert.getEmail());
            preparedStatement.setString(4, toInsert.getPassword());
            int success = preparedStatement.executeUpdate();
            return success == 1;
        } catch (SQLException e) {
            throw e;
        }
    }


    @Override
    public Set<User>  findAll() throws SQLException {
        Set<User> user = new HashSet<>();
        String sql = "SELECT * FROM \"user\"";
        ResultSet resul = GenricDAO.getInstance().getResult(sql);
        while (resul.next()) {
            user.add(
                    new User(
                            resul.getInt("id"),
                            resul.getString("last_name"),
                            resul.getString("first_name"),
                            resul.getString("email"),
                            resul.getString("password")

                    )
            );
        }
        return user;


    }

    @Override
    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM \"user\" WHERE id = ?";
        try(PreparedStatement preparedStatement = GenricDAO.getInstance().getConnection().prepareStatement(sql)){
             preparedStatement.setInt(1,id);
             preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw e;
        }
    }
    @Override
    public User findById(int id) throws SQLException {
        String sql = "SELECT * FROM \"user\" WHERE id = ?";
        try (PreparedStatement preparedStatement = GenricDAO.getInstance().getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("last_name"),
                        resultSet.getString("first_name"),
                        resultSet.getString("email"),
                        resultSet.getString("password")

                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw e;
        }
    }




    @Override
    public void update(int id, User newValue) throws SQLException {
        String sql = "UPDATE \"user\" SET last_name = ?, first_name = ?, email = ?, password = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = GenricDAO.getInstance().getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, newValue.getLastName());
            preparedStatement.setString(2, newValue.getFirstName());
            preparedStatement.setString(3, newValue.getEmail());
            preparedStatement.setString(4, newValue.getPassword());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }

    }


    public UserDto finByIdDto(int id) throws SQLException {
        String sql = "SELECT * FROM \"user\" WHERE id = ?";
        try (PreparedStatement preparedStatement = GenricDAO.getInstance().getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new UserDto(

                        resultSet.getString("last_name"),
                        resultSet.getString("first_name")


                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw e;
        }
    }


}
