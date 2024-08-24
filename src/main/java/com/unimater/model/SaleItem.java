package com.unimater.model;

import com.unimater.dao.ProductDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SaleItem implements Entity{
    private int id;
    private Product product;
    private int quantity;
    private double percentualDiscount;

    public SaleItem(ResultSet rs, Connection connection) throws SQLException {
        super();
        this.id = rs.getInt("id");
        this.product = getProduct(connection, id);
        this.quantity = rs.getInt("quantity");
        this.percentualDiscount = rs.getDouble("percentual_discount");
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

    private Product getProduct(Connection connection, int productId) {
        ProductDAO productDao = new ProductDAO(connection);
        return productDao.getById(productId);
    }

    @Override
    public Entity constructFromResultSet(ResultSet rs, Connection connection) throws SQLException {
        return new SaleItem(rs, connection);
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