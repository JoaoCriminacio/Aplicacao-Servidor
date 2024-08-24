package com.unimater.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Sale implements Entity{

    private int id;
    private List<SaleItem> saleItems;
    private Timestamp insert_at;

    public Sale(ResultSet rs, Connection connection) throws SQLException {
        super();
        this.id = rs.getInt("id");
        this.saleItems = new ArrayList<>();
        this.insert_at = rs.getTimestamp("insert_at");
    }

    public Sale(int id, List<SaleItem> saleItems, Timestamp insertAt) {
        this.id = id;
        this.saleItems = saleItems;
        this.insert_at = insertAt;
    }

    public Sale() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }

    public Timestamp getInsertAt() {
        return insert_at;
    }

    public void setInsertAt(Timestamp insertAt) {
        this.insert_at = insertAt;
    }

    @Override
    public Entity constructFromResultSet(ResultSet rs, Connection connection) throws SQLException {
        return new Sale(rs, connection);
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", saleItems=" + saleItems +
                ", insertAt=" + insert_at +
                '}';
    }
}