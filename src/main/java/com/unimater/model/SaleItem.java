package com.unimater.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SaleItem implements Entity{
    private int id;
    private Product product;
    private int quantity;
    private double percentualDiscount;

    public SaleItem(int id, Product product, int quantity, double percentualDiscount) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.percentualDiscount = percentualDiscount;
    }

    public SaleItem(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.quantity = rs.getInt("quantity");
        this.percentualDiscount = rs.getDouble("percentual_discount");
        this.product = new Product(rs.getInt("product_id"));
    }

    public SaleItem() {
    }

    @Override
    public Entity constructFromResultSet(ResultSet rs) throws SQLException {
        return new SaleItem(rs);
    }

    @Override
    public PreparedStatement prepareStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, getProduct().getId());
        preparedStatement.setInt(2, getQuantity());
        preparedStatement.setDouble(3, getPercentualDiscount());
        return preparedStatement;
    }
    public PreparedStatement prepareStatement(PreparedStatement preparedStatement, Sale sale) throws SQLException{
        preparedStatement = prepareStatement(preparedStatement);
        preparedStatement.setInt(4, sale.getId());
        return preparedStatement;
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

    public void setProduct(Product product) {
        this.product = product;
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
