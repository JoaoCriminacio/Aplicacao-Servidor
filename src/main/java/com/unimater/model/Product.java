package com.unimater.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Product implements Entity{

    private int id;
    private ProductType productType;
    private String description;
    private double value;

    public Product(int id, ProductType productType, String description, double value) {
        this.id = id;
        this.productType = productType;
        this.description = description;
        this.value = value;
    }

    public Product(ResultSet resultSet) throws SQLException{
        this.id = resultSet.getInt("id");
        this.description = resultSet.getString("description");
        this.value = resultSet.getDouble("value");
        this.productType = new ProductType(resultSet.getInt("product_type_id"));
    }

    public Product() {
    }

    public Product(int id) {
        this.id = id;
    }

    @Override
    public Entity constructFromResultSet(ResultSet rs) throws SQLException {
        return new Product(rs);
    }

    @Override
    public PreparedStatement prepareStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, this.getProductType().getId());
        preparedStatement.setString(2, getDescription());
        preparedStatement.setDouble(3, getValue());
        return preparedStatement;
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

    public void setProductType(ProductType productType) {
        this.productType = productType;
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
