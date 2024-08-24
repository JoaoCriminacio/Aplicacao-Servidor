package com.unimater.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Entity {

    Entity constructFromResultSet(ResultSet rs, Connection connection) throws SQLException;
    int getId();
}