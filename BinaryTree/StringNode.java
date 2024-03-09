package BinaryTree;

public class StringNode extends Node {
    public StringNode(String data) {
        this.data = data;
        this.right = null;
        this.left = null;
    }

    public void setData(String data) {
        this.data = data;
    }
}
