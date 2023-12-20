package com.example.layeredarchitecture.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> {
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

    boolean Save(T dto) throws SQLException, ClassNotFoundException;

    boolean Update(T dto) throws SQLException, ClassNotFoundException;

    boolean exist(String id) throws SQLException, ClassNotFoundException;

    void Delete(String id) throws SQLException, ClassNotFoundException;

    String generateId() throws SQLException, ClassNotFoundException;

    T Search(String newValue) throws SQLException, ClassNotFoundException;

    ArrayList<String> getData(String code) throws SQLException, ClassNotFoundException;

}
