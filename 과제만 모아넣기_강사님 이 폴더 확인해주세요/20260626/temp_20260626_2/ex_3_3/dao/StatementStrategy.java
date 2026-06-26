package com.example.springtheory.temp_20260626_2.ex_3_3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {
    PreparedStatement makeStatement(Connection conn) throws SQLException;
}
