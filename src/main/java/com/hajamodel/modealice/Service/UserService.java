package com.hajamodel.modealice.Service;

import com.hajamodel.modealice.DAO.DAOMethode;
import com.hajamodel.modealice.DTO.UserDto;
import com.hajamodel.modealice.Model.User;
import com.hajamodel.modealice.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Set;

@Service
public class UserService implements DAOMethode<User> {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean insert(User toInsert) {
        try {
            userRepository.insert(toInsert);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<User> findAll() {
        try {
            return userRepository.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        try {
            userRepository.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delet user", e);
        }
    }

    @Override
    public User findById(int id) {
        try {
            return userRepository.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public UserDto finByIdDto(int id) throws SQLException{
        return userRepository.finByIdDto(id);
    }

    public void update(int id, User newValue) {
        try {
            userRepository.update(id, newValue);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update user", e);
        }
    }
}
