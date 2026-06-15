package temp;

import java.util.ArrayDeque;

public class MyTreeMap {
    private static class Node {
        String key;
        Integer value;
        Node left, right;

        public Node(String key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root;
    int size = 0;

    public void put (String key, int value) {
        root = put(root, key, value);
    }

    public Node put(Node n, String key, int value) {
        if(n == null) {
            size++;
            return new Node(key, value);
        }
        if(key.compareTo(n.key) < 0) n.left = put(n.left, key, value);
        else if(key.compareTo(n.key) > 0) n.right = put(n.right, key, value);
        else n.value = value; //값 중복 시
        return n;
    }

    public Integer get(String key) {
        for (Node i= root; i != null;) {
            if(key.compareTo(i.key) == 0) {
                return i.value;
            } else if (key.compareTo(i.key) < 0) {
                i = i.left;
            } else {
                i = i.right;
            }

        }
        return null;
    }

    public void printSorted() {
        inOrder(root);
        System.out.println();
    }

    public void inOrder(Node n) {
        if(n == null) return;
        inOrder(n.left);
        System.out.print("["+n.key+"="+n.value+"]");
        inOrder(n.right);
    }

    public int getSize() {
        return size;
    }

    public boolean containsKey(String key) {
        for (Node i= root; i != null;) {
            if(key.compareTo(i.key) == 0) {
                return true;
            } else if (key.compareTo(i.key) < 0) {
                i = i.left;
            } else {
                i = i.right;
            }
        }
        return false;
    }

    public String firstKey() {
        for (Node i= root; i != null; i = i.left) {
            if(i.left == null) {
                return i.key;
            }
        }
        return null;
    }

    public String lastKey() {
        for (Node i= root; i != null; i = i.right) {
            if(i.right == null) {
                return i.key;
            }
        }
        return null;
    }

    public Integer remove(String key) {
        Integer check = get(key);
        if(check == null) return null;
        root = remove(root, key);
        size--;
        return check;
    }

    public Node remove(Node n, String key) {
        if(n == null) {
            return null;
        }
        if(key.compareTo(n.key) < 0) n.left = remove(n.left, key);
        else if(key.compareTo(n.key) > 0) n.right = remove(n.right, key);
        else { //검색 완료
            if(n.left == null) return n.right; //경우 2 1도가능
            if(n.right == null) return n.left; // 경우 2

            Node moveNode = n.right;
            while (moveNode.left != null) moveNode = moveNode.left;
            n.key = moveNode.key;
            n.value = moveNode.value;
            n.right = remove(n.right, moveNode.key); //옮길 노드의 값을 복사 후 중복되는 이 값을 밑에서 다시 삭제
        }
        return n;
    }
}
