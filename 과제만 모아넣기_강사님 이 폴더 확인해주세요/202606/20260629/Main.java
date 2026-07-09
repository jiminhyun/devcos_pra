package com.example.springtheory.temp_20260629;

public class Main {
    static void main(String[] args) {
        //new FileLogger().log("INFO", "테스트");
        FileLogger logger = new FileLogger();
        DataService service = new DataService(logger);
        try {
            System.out.println("===== 1) 예외 복구: 재시도 (3번째에 성공) =====");
            System.out.print("최종 결과: ");
            service.fetchWithRetry(new FlakyService(2));
        }catch (RuntimeException e) {
            System.out.println("실패 통보: " +e.getMessage());
        }
        System.out.println();
        try {
            System.out.println("===== 2) 예외 복구 실패: 재시도 모두 실패 -> 통보 =====");
            System.out.print("최종 결과: ");
            service.fetchWithRetry(new FlakyService(99));
        }catch (RuntimeException e) {
            System.out.println("실패 통보: " +e.getMessage());
        }
        System.out.println();
        try {
            System.out.println("===== 3) 예외 전환: 아이디 중복 -> 의미 있는 예외 =====");
            service.registerUser("kim");
        }catch (DuplicateUserIdException e) {
            System.out.println("잡힘: " +e.getMessage());
            System.out.println("원인 보존: " + e.getCause());
        }
        System.out.println("\n" +
                "===== 로그 파일 위치 =====");
        System.out.println(logger.getLogFilePath());
    }
}
