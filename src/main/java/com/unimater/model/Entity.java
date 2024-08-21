package com.unimater.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Entity {

    Entity constructFromResultSet(ResultSet rs) throws SQLException;
    int getId();
}