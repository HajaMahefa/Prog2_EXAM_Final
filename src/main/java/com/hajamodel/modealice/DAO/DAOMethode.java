package com.hajamodel.modealice.DAO;

import com.hajamodel.modealice.DTO.UserDto;

import java.sql.SQLException;
import java.util.Set;

public interface DAOMethode <T> {


    boolean insert(T toInsert) throws SQLException;

    Set<T> findAll () throws SQLException;

    void deleteById(int id) throws SQLException;

    T findById(int id) throws SQLException;

    //UserDto finByIdDto(int id) throws  SQLException;
    void update (int id, T newValue) throws SQLException;

}