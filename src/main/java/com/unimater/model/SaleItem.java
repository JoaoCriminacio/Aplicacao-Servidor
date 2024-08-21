package com.unimater.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SaleItem implements Entity{
    private int id;
    private Product product;
    private int quantity;
    private double percentualDiscount;

    public SaleItem(ResultSet rs) throws SQLException {
        super();
        this.id = rs.getInt("id");
        this.product = new Product(rs);
        this.quantity = rs.getInt("quantity");
        this.percentualDiscount = rs.getDouble("percentualDiscount");
    }

    public SaleItem(int id, Product product, int quantity, double percentualDiscount) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.percentualDiscount = percentualDiscount;
    }

    public SaleItem() {

    }

    public int getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPercentualDiscount() {
        return percentualDiscount;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPercentualDiscount(double percentualDiscount) {
        this.percentualDiscount = percentualDiscount;
    }

    @Override
    public Entity constructFromResultSet(ResultSet rs) throws SQLException {
        return new SaleItem(rs);
    }

    @Override
    public String toString() {
        return "SaleItem{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                ", percentualDiscount=" + percentualDiscount +
                '}';
    }
}