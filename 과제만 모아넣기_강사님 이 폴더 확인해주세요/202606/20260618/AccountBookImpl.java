package temp3;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountBookImpl implements AccountBook{
    private final String DIR = "accountbook";
    private Scanner sc = new Scanner(System.in);

    public AccountBookImpl() {
        File folder = new File(DIR);
        if(!folder.exists()) folder.mkdir();
    }

    @Override
    public void addAccount() {//파일의 정보찾기는 단시간(주소 접근)에 가능하지만 파일 내용 중 중간 삭제, 수정 같은 경우 메모리에 다 적재하고 판단을 해야 하는지라서
        //그냥 새로 다시 쓰는게 편하고 보통 이렇게 한다.
        //이런 경우를 해결하기 위해서 나온 것이 데이터베이스다.
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        File file = new File(DIR, today + ".txt");
        StringBuilder sb = new StringBuilder();
        int sum = 0;
        if(file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ( (line = br.readLine()) != null ) {
                    if (!line.startsWith("합계")) {
                        sb.append(line).append("\n");
                    } else {
                        try{
                            sum += Integer.parseInt(line.substring(5, line.length()-1));
                        } catch (NumberFormatException e) {
                            System.out.println("예기치 못한 오류가 발생했습니다.");
                            return;
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        while (true) {
            System.out.print("항목 이름 > ");
            String imName = sc.nextLine().trim();
            sb.append(imName).append(" : ");

            int price;
            while (true) {
                try {
                    System.out.print("금액 > ");
                    price = Integer.parseInt(sc.nextLine().trim());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("형식을 지켜주세요 숫자만 입력해주세요");
                }
            }
            sb.append(price).append("원\n");
            sum += price;
            String temp;
             do {
                System.out.print("더 추가할까요? (y/n) > ");
                temp = sc.nextLine();
                if (temp.equals("n")) {
                    sb.append("합계 : ").append(sum).append("원");
                    try (FileWriter fw = new FileWriter(file, false)){
                        fw.write(String.valueOf(sb));
                        System.out.println(today +".txt에 저장 완료!\n"+sb);
                        return;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (temp.equals("y")) {
                    break;
                } else {
                    System.out.println("제대로 입력해주세요");
                }
             } while (true);
        }

    }

    @Override
    public void showAccount() {
        File folder = new File(DIR);
        String[] fileList = folder.list();

        if (fileList == null) {
            System.out.println("파일 없습니다.");
            return;
        }

        System.out.println("== 기록된 날짜 ==");
        for (String i : fileList) {
            if(i.endsWith(".txt")) {
                System.out.println(i.replace(".txt", ""));
            }
        }

        System.out.print("조회할 날짜 입력 > ");
        String temp = sc.nextLine().trim();
        File file = new File(DIR, temp +".txt");
        StringBuilder sb = new StringBuilder();
        if(file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                System.out.println("[" + temp + "]");
                String line;
                while ( (line = br.readLine()) != null ) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("없습니다.");
        }
    }

    @Override
    public void deleteAccount() {
        File folder = new File(DIR);
        String[] fileList = folder.list();

        if (fileList == null) {
            System.out.println("파일 없습니다.");
            return;
        }

        System.out.println("== 기록된 날짜 ==");
        for (String i : fileList) {
            if (i.endsWith(".txt")) {
                System.out.println(i.replace(".txt", ""));
            }
        }
        System.out.print("삭제할 날짜 입력 > ");
        File file = new File(DIR, sc.nextLine() + ".txt");
        if (file.exists()) {
            if (file.delete()) System.out.println("삭제되었습니다.");
            else System.out.println("삭제에 실패했습니다.");
        } else {
            System.out.println("날짜 없습니다.");
        }
    }
}
