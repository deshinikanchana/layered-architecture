package com.example.layeredarchitecture.Dao.Custom.impl;
import com.example.layeredarchitecture.Dao.Custom.OrdersDAO;
import com.example.layeredarchitecture.Dao.SQLUtill;
import com.example.layeredarchitecture.model.OrderDTO;

import java.sql.*;
import java.util.ArrayList;

public class ordersDAOimpl implements OrdersDAO {

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }

    @Override
    public boolean exist(String orderId) throws SQLException, ClassNotFoundException {
       ResultSet rst = SQLUtill.execute("SELECT oid FROM `Orders` WHERE oid=?",orderId);
       return rst.next();
    }

    @Override
    public boolean Save(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)",dto.getOrderId(),Date.valueOf(dto.getOrderDate()),dto.getCustomerId());
    }

    @Override
    public OrderDTO Search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getData(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT DISTINCT " + code + " from Orders;");
        ArrayList<String> data = new ArrayList<>();
        while(rst.next()){
            data.add(String.valueOf(rst.getObject(1)));
        }
        return data;
    }

    @Override
    public void Delete(String id) throws SQLException, ClassNotFoundException {}

    @Override
    public ArrayList<OrderDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean Update(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }
}
