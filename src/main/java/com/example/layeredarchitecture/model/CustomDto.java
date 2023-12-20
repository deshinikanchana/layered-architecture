package com.example.layeredarchitecture.model;

import java.sql.Date;

public class CustomDto {
    private Date date;
    private String orderId;
    private String cusId;
    private String cusName;
    private String address;
    private String itemCode;
    private String desc;
    private int qty;

    public CustomDto() {
    }

    public CustomDto(Date date, String orderId, String cusId, String cusName, String address, String itemCode, String desc, int qty) {
        this.date = date;
        this.orderId = orderId;
        this.cusId = cusId;
        this.cusName = cusName;
        this.address = address;
        this.itemCode = itemCode;
        this.desc = desc;
        this.qty = qty;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "CustomDto{" +
                "date=" + date +
                ", orderId='" + orderId + '\'' +
                ", cusId='" + cusId + '\'' +
                ", cusName='" + cusName + '\'' +
                ", address='" + address + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", desc='" + desc + '\'' +
                ", qty=" + qty +
                '}';
    }
}
