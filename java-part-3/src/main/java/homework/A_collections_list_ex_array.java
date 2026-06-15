package homework;


public class A_collections_list_ex_array {
    public static void main(String[] args) {
        MyArrayList list = new MyArrayList();

        // --- Step 2~4 확인 ---
        list.addLast("가");
        list.addLast("나");
        list.addLast("다");
        System.out.println("size = " + list.size());                 // 기대: 3
        System.out.println("0,1,2 = " + list.get(0) + ", "
                + list.get(1) + ", "
                + list.get(2));                // 기대: 가, 나, 다

        // --- Step 6 확인 ---
        list.addFirst("앞");
        System.out.println("addFirst 후 0,1 = " + list.get(0) + ", " + list.get(1)); // 기대: 앞, 가
        System.out.println("size = " + list.size());                 // 기대: 4
    }
}
