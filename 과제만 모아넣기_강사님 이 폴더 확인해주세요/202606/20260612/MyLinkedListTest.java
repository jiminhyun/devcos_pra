package temp;

public class MyLinkedListTest {
    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();

        // --- Step 3 + 4 확인 ---
        list.addLast("가");
        list.addLast("나");
        list.addLast("다");
        System.out.print("addLast 후: ");
        list.printLinks();
        // 기대: [null <- 가 -> 나] [가 <- 나 -> 다] [나 <- 다 -> null]

        // --- Step 5 확인 ---
        list.addFirst("앞");
        System.out.print("addFirst 후: ");
        list.printLinks();
        // 기대: [null <- 앞 -> 가] [앞 <- 가 -> 나] [가 <- 나 -> 다] [나 <- 다 -> null]

        // --- Step 6 확인 ---
        System.out.println("get(2) = " + list.get(2));   // 기대: 나

        // --- Step 7 확인 ---
        list.insert(2, "끼움");
        System.out.print("insert 후: ");
        list.printLinks();
        // 기대: [null <- 앞 -> 가] [앞 <- 가 -> 끼움] [가 <- 끼움 -> 나] [끼움 <- 나 -> 다] [나 <- 다 -> null]
    }
}