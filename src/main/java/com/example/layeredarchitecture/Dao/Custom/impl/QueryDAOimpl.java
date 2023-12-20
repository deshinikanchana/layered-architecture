package com.example.layeredarchitecture.Dao.Custom.impl;

import com.example.layeredarchitecture.Dao.Custom.QuaryDAO;
import com.example.layeredarchitecture.Dao.SQLUtill;
import com.example.layeredarchitecture.model.CustomDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOimpl implements QuaryDAO {
    @Override
    public ArrayList<CustomDto> filter(Object value, String col) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT O.date,O.oid,C.id,C.name,C.address,I.code,I.description,OD.qty from Customer C join Orders O on C.id = O.customerId join OrderDetails OD on O.oid = OD.oid join Item I on OD.itemCode = I.code  where " + col + " = ? order by O.oid asc;",value);
        return SetValues(rst);
    }

    @Override
    public ArrayList<CustomDto> getAll(String... Val) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT O.date,O.oid,C.id,C.name,C.address,I.code,I.description,OD.qty from Customer C join Orders O on C.id = O.customerId join OrderDetails OD on O.oid = OD.oid join Item I on OD.itemCode = I.code Order\n" +
                "by O.date asc;");
        return SetValues(rst);
    }

    @Override
    public ArrayList<CustomDto> SetValues(ResultSet rst) throws SQLException {
        ArrayList<CustomDto> List = new ArrayList<>();

        while (rst.next()) {
            CustomDto dto = new CustomDto(
                    rst.getDate(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getInt(8)
            );
            List.add(dto);
        }
        return List;
    }

}
