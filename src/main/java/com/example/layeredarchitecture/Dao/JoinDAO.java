package com.example.layeredarchitecture.Dao;

import com.example.layeredarchitecture.model.CustomDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface JoinDAO<T> {
    ArrayList<CustomDto> getAll(String ... Val) throws SQLException, ClassNotFoundException;
     ArrayList<CustomDto> SetValues(ResultSet rst) throws SQLException;
     ArrayList<CustomDto> filter(Object value,String col) throws SQLException, ClassNotFoundException;
}
