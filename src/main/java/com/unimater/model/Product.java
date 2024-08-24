package com.unimater.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Product implements Entity{

    private int id;
    private ProductType productType;
    private String description;
    private double value;

    public Product(ResultSet rs, Connection connection) throws SQLException {
        super();
        this.id = rs.getInt("id");
        this.productType = new ProductType(rs);
        this.description = rs.getString("description");
        this.value = rs.getDouble("value");
    }

    public Product(int id, ProductType productType, String description, double value) {
        this.id = id;
        this.productType = productType;
        this.description = description;
        this.value = value;
    }

    public Product() {

    }

    public int getId() {
        return id;
    }

    public ProductType getProductType() {
        return productType;
    }

    public String getDescription() {
        return description;
    }

    public double getValue() {
        return value;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Entity constructFromResultSet(ResultSet rs, Connection connection) throws SQLException {
        return new Product(rs, connection);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productType=" + productType +
                ", description='" + description + '\'' +
                ", value=" + value +
                '}';
    }
}