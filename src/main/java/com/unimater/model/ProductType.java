package com.unimater.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductType implements Entity {

    private int id;
    private String description;

    public ProductType(ResultSet rs) throws SQLException {
        super();
        this.id = rs.getInt("id");
        this.description = rs.getString("description");
    }

    public ProductType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public ProductType() {

    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Entity constructFromResultSet(ResultSet rs) throws SQLException{
        return new ProductType(rs);
    }

    @Override
    public String toString() {
        return "ProductType{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}