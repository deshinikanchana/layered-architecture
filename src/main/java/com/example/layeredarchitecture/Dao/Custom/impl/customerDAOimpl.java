package com.example.layeredarchitecture.Dao.Custom.impl;
import com.example.layeredarchitecture.Dao.Custom.CustomerDAO;
import com.example.layeredarchitecture.Dao.SQLUtill;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;


public class customerDAOimpl  implements CustomerDAO {

    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT * FROM Customer");
        ArrayList<CustomerDTO> allCustomer = new ArrayList<>();

        while (rst.next()) {
            CustomerDTO customerDTO = new CustomerDTO(
                    rst.getString("id"),
                    rst.getString("name"),
                    rst.getString("address"));
            allCustomer.add(customerDTO);
        }
        return allCustomer;
    }

    @Override
    public boolean Save(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)", dto.getId(),dto.getName(),dto.getAddress());

    }

    @Override
    public boolean Update(CustomerDTO dto) throws SQLException, ClassNotFoundException {

       SQLUtill.execute("UPDATE Customer SET name=?, address=? WHERE id=?", dto.getName(),dto.getAddress(),dto.getId());

        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
      ResultSet rst = SQLUtill.execute("SELECT id FROM Customer WHERE id=?",id);
      return rst.next();

    }

    @Override
    public void Delete(String id) throws SQLException, ClassNotFoundException {
        SQLUtill.execute("DELETE FROM Customer WHERE id=?",id);
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
      ResultSet rst = SQLUtill.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public CustomerDTO Search(String newValue) throws SQLException, ClassNotFoundException {

       ResultSet rst = SQLUtill.execute("SELECT * FROM Customer WHERE id=?",newValue);
        rst.next();
        return new CustomerDTO(newValue + "", rst.getString("name"), rst.getString("address"));

    }

    @Override
    public ArrayList<String> getData(String code) throws SQLException, ClassNotFoundException {
        return null;
    }
}
