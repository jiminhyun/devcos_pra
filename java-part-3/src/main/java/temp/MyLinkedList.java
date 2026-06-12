package temp;

public class MyLinkedList {
    static class Node {
        Node prev;
        Node next;
        String data;

        public Node(String data) {
            this.data = data;
        }
    }
    //직접 구현

    private Node head;
    private Node tail;
    private int size = 0;

    void addLast(String data) {
        if(head == null) {
            head = tail = new Node(data);
        } else {
            Node newNode = new Node(data);
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }
    void printLinks() {
        Node cur = head;
        while (cur != null) {
            String p = (cur.prev == null) ? "null" : cur.prev.data;
            String n = (cur.next == null) ? "null" : cur.next.data;
            System.out.print("[" + p + " <- " + cur.data + " -> " + n + "] ");
            cur = cur.next;
        }
        System.out.println();
    }

    void addFirst(String data) {
        if(head == null) {
            head = tail = new Node(data);
        } else {
            Node newNode = new Node(data);
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    private Node nodeAt(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("범위 초과");
        }
        Node findNode = head;
        for (int i = 0; i < index; i++) {
            findNode = findNode.next;
        }
        return findNode;
    }

    String get(int index) {
        return nodeAt(index).data;
    }

    void insert(int index, String data) {
        if(index == 0) {
            addFirst(data);
        } else if (index == size) {
            addLast(data);
        } else if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("범위 초과");
        } else {
            Node prevNode = nodeAt(index).prev;
            Node nextNode = nodeAt(index);
            Node newNode = new Node(data);
            prevNode.next = newNode;
            nextNode.prev = newNode;
            newNode.prev = prevNode;
            newNode.next = nextNode;
        }
        size++;
    }

    void remove(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("범위 초과");
        }
        Node curNode = nodeAt(index);
        Node prevNode = curNode.prev;
        Node nextNode = curNode.next;
        if(index == 0) {
            curNode.next = null;
            if (size != 1) {
                nextNode.prev = null;
            } else {
                tail = null;
            }
            head = nextNode;
        } else if (index == size-1) {
            curNode.prev = null;
            prevNode.next = null;
            tail = prevNode;
        } else {
            curNode.next = curNode.prev = null;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }
        size--;
    }
}
