package com.example.springtheory.temp_20260629;

import java.sql.SQLException;

public class DataService {
    private FileLogger fileLogger;

    public DataService(FileLogger fileLogger) {
        this.fileLogger = fileLogger;
    }

    String fetchWithRetry(FlakyService flaky) {
        int retryNum = 3;
        for (int attempt = 1; attempt <= retryNum; attempt++) {
            try {
                String message = flaky.fetch();
                fileLogger.log("INFO", attempt + "번째 시도 성공: "+message);
                return message;
            } catch (SQLException e) {
                fileLogger.log("WARN", attempt + "번째 시도 실패: "+e.getMessage());
            }
        }
        fileLogger.log("ERROR", "재시도 " + retryNum + "회 모두 실패");
        throw new RuntimeException("재시도 " + retryNum +"회 모두 실패했습니다.");
    }

    //그냥 넘기기
    void avoidByThrows(FlakyService f) throws SQLException {
        f.fetch();
    }

    //try로 잡고 넘기기
    void aVoidByRethrow(FlakyService f) throws SQLException {
        try {
            f.fetch();
        } catch (SQLException e) {
            fileLogger.log("WARN", "회피" +e.getMessage());
            throw e;
        }
    }

    //예외 전환
    void registerUser(String id) {
        try {
            insertUser(id);
        } catch (SQLException e) {
            if("23000".equals(e.getSQLState())) {
                fileLogger.log("ERROR", "아이디 중복: "+id);
                throw new DuplicateUserIdException(id, e);
            }
            fileLogger.log("ERROR", "db 오류");
            throw new RuntimeException("db 오류", e);
        }
    }

    private boolean insertUser(String id) throws SQLException {
        throw new SQLException("Duplicate entry", "23000"); //reason >>getCause()
    }

}

class DuplicateUserIdException extends RuntimeException {

    public DuplicateUserIdException(String id, Throwable cause) {
        super("이미 존재하는 아이디입니다: "+id, cause); //상위 클래스 생성자 참고
    }
}