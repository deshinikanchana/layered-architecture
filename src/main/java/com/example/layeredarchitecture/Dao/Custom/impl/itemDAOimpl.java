package com.example.layeredarchitecture.Dao.Custom.impl;

import com.example.layeredarchitecture.Dao.Custom.ItemDAO;
import com.example.layeredarchitecture.Dao.SQLUtill;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public class itemDAOimpl implements ItemDAO {

   @Override
   public ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException {

       ResultSet rst = SQLUtill.execute("SELECT * FROM Item");
        ArrayList<ItemDTO> itemList = new ArrayList<>();

        while (rst.next()) {
            ItemDTO Idto = new ItemDTO(
                    rst.getString("code"),
                    rst.getString("description"),
                    rst.getBigDecimal("unitPrice"),
                    rst.getInt("qtyOnHand")
            );
            itemList.add(Idto);
        }
        return itemList;
    }

    @Override
    public void Delete(String code) throws SQLException, ClassNotFoundException {
       SQLUtill.execute("DELETE FROM Item WHERE code=?",code);
    }

    @Override
    public boolean Save(ItemDTO dto) throws SQLException, ClassNotFoundException {
       return  SQLUtill.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",dto.getCode(),dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand());
    }


    @Override
    public boolean Update(ItemDTO dto) throws SQLException, ClassNotFoundException {
       return SQLUtill.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand(),dto.getCode());

    }
    @Override
    public boolean exist (String code) throws SQLException, ClassNotFoundException {
       ResultSet rst = SQLUtill.execute("SELECT code FROM Item WHERE code=?", code);
       return rst.next();
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }

    @Override
    public ItemDTO Search(String id) throws SQLException, ClassNotFoundException {
      ResultSet rst = SQLUtill.execute("SELECT * FROM Item WHERE code=?",id);
        rst.next();
        return new ItemDTO(id + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));


    }

    @Override
    public ArrayList<String> getData(String code) throws SQLException, ClassNotFoundException {
        return null;
    }

}
