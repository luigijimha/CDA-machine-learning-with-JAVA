package BinaryTree;

public abstract class Node {
    protected Object data;
    protected Node right;
    protected Node left;

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return this.left;
    }

    public Node getRight() {
        return this.right;
    }

    public Object getData() {
        return this.data;
    }

    public String printTree(String prefix, boolean isTail, String sb) {
        if(this.right != null) {
            this.right.printTree(prefix + (isTail ? "|   " : "    "), false, sb);
        }
        System.out.println(prefix + (isTail ? "\\-- " : "/-- ") + this.data);
        if(this.left != null) {
            this.right.printTree(prefix + (isTail ? "    " : "|   "), true, sb);
        }
        return sb;
    }

    public static void PreOrder(Node current) {
        if(current == null) return;
        System.out.print(current.data + " ");
        Node.PreOrder(current.left);
        Node.PreOrder(current.right);
    }

    public static void InOrder(Node current) {
        if(current == null) return;
        Node.InOrder(current.left);
        System.out.print(current.data + " ");
        Node.InOrder(current.right);
    }

    public static void PosOrder(Node current) {
        if(current == null) return;
        Node.PosOrder(current.left);
        Node.PosOrder(current.right);
        System.out.print(current.data + " ");
    }

    public static boolean search(Object data, Node current) {
        if(current == null) return false;
        return current.data == data || Node.search(data, current.left) || Node.search(data, current.right);
    }

    public static int countNodes(Node current) {
        if(current == null) return 0;
        return 1 + Node.countNodes(current.left) + Node.countNodes(current.right);
    }    
}