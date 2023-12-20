package com.example.layeredarchitecture.controller;

import com.example.layeredarchitecture.Dao.Custom.QuaryDAO;
import com.example.layeredarchitecture.Dao.Custom.OrdersDAO;
import com.example.layeredarchitecture.Dao.Custom.impl.QueryDAOimpl;
import com.example.layeredarchitecture.Dao.Custom.impl.ordersDAOimpl;
import com.example.layeredarchitecture.model.CustomDto;
import com.example.layeredarchitecture.view.tdm.CustomTM;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewOrderFormController {

    public AnchorPane root;
    public TableView tblOrders;
    public TableColumn colDate;
    public TableColumn colOid;
    public TableColumn colCusId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colIcode;
    public TableColumn colDesc;
    public TableColumn colQty;
    public ComboBox cmbDate;
    public ComboBox cmbOid;
    public ComboBox cmbCusId;
    QuaryDAO dao =  new QueryDAOimpl();
    OrdersDAO Odao = new ordersDAOimpl();
    public void initialize() throws SQLException, ClassNotFoundException {
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colOid.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCusId.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("cusName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colIcode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        initUI();
        loadData("date",cmbDate);
        loadData("customerId",cmbCusId);
        loadData("oid",cmbOid);
    }

    private void loadData(String sql,ComboBox cmb) throws ClassNotFoundException {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            ArrayList<String> List = Odao.getData(sql);

            for (String id : List) {
                obList.add(id);
            }
            cmb.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void initUI() throws SQLException, ClassNotFoundException {
       cmbOid.setValue(null);
       cmbDate.setValue(null);
       cmbCusId.setValue(null);
        cmbOid.setDisable(false);
        cmbCusId.setDisable(false);
        cmbDate.setDisable(false);
        loadAllOrders();
    }
    public void navigateToHome(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/com/example/layeredarchitecture/main-form.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    public void loadAllOrders() throws SQLException, ClassNotFoundException {
        tblOrders.getItems().clear();
        ArrayList<CustomDto> odList = dao.getAll();
        LoadTable(odList);
    }
    public void onActionBtnReset(ActionEvent event) throws SQLException, ClassNotFoundException {
        initUI();
    }

    public void onActionCmbDate(ActionEvent event) throws SQLException, ClassNotFoundException {
        setCmbActions(cmbDate,cmbCusId,cmbOid,"O.date");
    }

    private void LoadTable(ArrayList<CustomDto> filtered) {
        tblOrders.getItems().clear();
        for (CustomDto dto:filtered) {

            tblOrders.getItems().add(new CustomTM(
                    dto.getDate(),
                    dto.getOrderId(),
                    dto.getCusId(),
                    dto.getCusName(),
                    dto.getAddress(),
                    dto.getItemCode(),
                    dto.getDesc(),
                    dto.getQty()
            ));
        }
    }

    public void onActionCmbOid(ActionEvent event) throws SQLException, ClassNotFoundException {
        setCmbActions(cmbOid,cmbDate,cmbCusId,"O.oid");
    }

    public void onActionCmbCusId(ActionEvent event) throws SQLException, ClassNotFoundException {
        setCmbActions(cmbCusId,cmbDate,cmbOid,"O.customerId");
    }

    public void setCmbActions(ComboBox selected,ComboBox ot1,ComboBox ot2,String code) throws SQLException, ClassNotFoundException {
        if(selected.getValue()!= null){
            ot1.setDisable(true);
            ot2.setDisable(true);
        }
        ArrayList <CustomDto> Filtered = dao.filter(selected.getValue(),code);
        LoadTable(Filtered);
    }
}
