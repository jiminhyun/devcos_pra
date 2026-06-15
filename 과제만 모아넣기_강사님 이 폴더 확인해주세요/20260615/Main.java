package temp;

public class Main {
    static void main(String[] args) {
        /*MyHashMap map = new MyHashMap();
        map.put("apple", 1);
        map.put("banana", 2);
        System.out.println(map.get("apple"));    // 1
        System.out.println(map.get("cherry"));   // null
        map.remove("apple");
        System.out.println(map.get("apple"));    // null*/

        /*MyTree tree = new MyTree();
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int v : values) tree.insert(v);

        tree.preOrder();    // 전위: 50 30 20 40 70 60 80
        tree.inOrder();     // 중위: 20 30 40 50 60 70 80   ← 정렬되어 나옴!
        tree.postOrder();   // 후위: 20 40 30 60 80 70 50*/

        MyTreeMap map = new MyTreeMap();
        map.put("banana", 2);
        map.put("banana", 4);
        map.put("apple", 1);
        map.put("cherry", 3);

        map.printSorted();          // [apple=1] [banana=2] [cherry=3]  ← 넣은 순서와 무관하게 정렬!
        System.out.println(map.remove("apple"));
        System.out.println(map.get("banana"));   // 2
        System.out.println(map.firstKey());      // apple (가장 작은 키)
        System.out.println(map.lastKey());       // cherry (가장 큰 키)
    }
}
