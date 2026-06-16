package homework;

public class MyHashMap {
    private static class Node {
        String key;
        Integer value;
        Node next;

        public Node(String key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }
    private final int capacity = 16;
    private int size;
    Node[] buckets;

    public MyHashMap() {
        buckets = new Node[capacity];
    }

    public int getIndex(String key) {
        return Math.abs(key.hashCode())%capacity;
    }

    public void put(String key, Integer value) {
        int idx = getIndex(key);
        Node curNode = buckets[idx];
        for (Node i = curNode; i != null; i=i.next) {
            if(key.equals(i.key)) {
                i.value = value;
                return;
            }
        }
        Node newNode = new Node(key, value);
        newNode.next = curNode;
        buckets[idx] = newNode;
        size++;
    }

    public Integer get(String key) {
        int idx = getIndex(key);
        Node curNode = buckets[idx];
        for (Node i = curNode; i != null; i=i.next) {
            if(key.equals(i.key)) {
                return i.value;
            }
        }
        return null;
    }

    public int getSize() {
        return size;
    }

    public boolean cotainsKey(String key) {
        int idx = getIndex(key);
        Node curNode = buckets[idx];
        for (Node i = curNode; i != null; i=i.next) {
            if(key.equals(i.key)) {
                return true;
            }
        }
        return false;
    }

    public Integer remove(String key) {
        int idx = getIndex(key);
        Node curNode = buckets[idx];
        Node prevNode = null;
        if(key.equals(curNode.key)) { //처음부터 찾았을 시
            size--;
            buckets[idx] = buckets[idx].next;
            return curNode.value;
        }
        while (curNode != null) {
            prevNode = curNode;
            curNode = curNode.next;
            if(key.equals(curNode.key)) {
                if(curNode.next == null) {
                    prevNode.next = null;
                }else {
                    prevNode.next = curNode.next;
                }
                size--;
                return curNode.value;
            }
        }
        return null;
    }
}
