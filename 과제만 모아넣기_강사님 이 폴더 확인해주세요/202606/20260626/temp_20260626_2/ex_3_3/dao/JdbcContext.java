package com.example.springtheory.temp_20260626_2.ex_3_3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcContext { //도전과제 5
    private SimpleConnectionMaker simpleConnectionMaker;

    public JdbcContext(SimpleConnectionMaker simpleConnectionMaker) {
        this.simpleConnectionMaker = simpleConnectionMaker;
    }

    public void jdbcContextWithStatementStrategy(StatementStrategy statementStrategy) throws SQLException, ClassNotFoundException {
        try (
                Connection conn = simpleConnectionMaker.makeNewConnection();
                PreparedStatement pstmt = statementStrategy.makeStatement(conn); // 변하는 부분을 전략(statementStrategy)에 위임
        ) {
            pstmt.executeUpdate();
        }
    }
}
