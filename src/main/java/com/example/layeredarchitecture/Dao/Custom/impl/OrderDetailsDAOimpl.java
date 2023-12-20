package com.example.layeredarchitecture.Dao.Custom.impl;

import com.example.layeredarchitecture.Dao.Custom.OrderDetailsDAO;
import com.example.layeredarchitecture.Dao.SQLUtill;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOimpl implements OrderDetailsDAO {


    @Override
    public boolean Save(OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)",dto.getOid(),dto.getItemCode(),dto.getUnitPrice(),dto.getQty());
    }

    @Override
    public boolean Update(OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }
    @Override
    public ArrayList<OrderDetailDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void Delete(String id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrderDetailDTO Search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getData(String code) throws SQLException, ClassNotFoundException {
        return null;
    }
}
