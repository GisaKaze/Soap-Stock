package com.fredson.soap.soapexam.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ItemsModel {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String itemCode;
    private String status;
    private int price;

    @OneToOne
    private SuppliersModel supplier;

    public ItemsModel(int id, String name, String itemCode, String status, int price, SuppliersModel supplier) {
        super();
        this.id = id;
        this.name = name;
        this.itemCode = itemCode;
        this.status = status;
        this.price = price;
        this.supplier = supplier;
    }
    public ItemsModel(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return String.format("Item [id=%s, name=%s, itemCode=%s, status=%s, price=%s, supplier=%s]", id, name, itemCode, status, price, supplier);
    }
}

