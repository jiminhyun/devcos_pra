package homework;

public class MyTree {
    static class Node {
        int value;
        Node left, right;

        public Node(int value) {
            this.value = value;
        }
    }

    private Node root = null;

    public void insert(int value) {
        Node temp = root;
        if(root == null) {
            root = new Node(value);
        } else {
            while(temp != null) {
                if(value < temp.value) {
                    if(temp.left == null) {
                        temp.left = new Node(value);
                        return;
                    }
                    temp = temp.left;
                } else if(value > temp.value){
                    if(temp.right == null) {
                        temp.right = new Node(value);
                        return;
                    }
                    temp = temp.right;
                }
            }
        }
    }

    public void preOrder() {
        preOrder(root);
        System.out.println();
    }
    public void inOrder() {
        inOrder(root);
        System.out.println();
    }
    public void postOrder() {
        postOrder(root);
        System.out.println();
    }

    public void preOrder(Node n) {
        if(n == null) return;
        System.out.print(n.value+" ");
        preOrder(n.left);
        preOrder(n.right);
    }

    public void inOrder(Node n) {
        if(n == null) return;
        inOrder(n.left);
        System.out.print(n.value+" ");
        inOrder(n.right);
    }

    public void postOrder(Node n) {
        if(n == null) return;
        postOrder(n.left);
        postOrder(n.right);
        System.out.print(n.value+" ");
    }
}
