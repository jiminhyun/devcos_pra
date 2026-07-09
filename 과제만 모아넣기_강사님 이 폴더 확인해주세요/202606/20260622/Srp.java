import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Srp {
    // ❌ 나쁜 예: 바뀔 이유가 2개 (내용 규칙 변경 / 저장 방식 변경)
    class Journal {
        private ArrayList<String> entries = new ArrayList<>();
        void add(String text) { entries.add(text); }

        String getText() {
            StringBuilder sb = new StringBuilder();
            for (String et : entries) {
                sb.append("- ").append(et).append("\n");
            }
            return String.valueOf(sb);
        }
    }

    class JournalSaver  {

        void print(Journal jl) {
            System.out.println(jl.getText());
        }

        void saveToFile(String filename, Journal jl) {// ← 이게 두 번째 책임!
            File file = new File(filename+ ".txt");
            try (FileWriter fw = new FileWriter(file)){
                fw.write(jl.getText());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // 파일에 저장하는 코드...
        }
    }
}
