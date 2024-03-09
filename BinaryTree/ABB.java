package BinaryTree;

public class ABB {
    private Object root;
    private Object current;

    public ABB() {
        this.root = null;
        this.current = null;
    }

    public ABB(int data) {
        this.root = new IntNode(data);
        this.current = this.root;
    }

    public ABB(String data) {
        this.root = new StringNode(data);
        this.current = this.root;
    }

    public boolean search(int data) {
        return IntNode.search(data, (Node) this.root);
    }

    public void printPreOrder() {
        Node.PreOrder((Node) this.root);
    }

    public void printInOrder() {
        IntNode.InOrder((Node)this.root);
    }

    public void printPosOrder() {
        IntNode.PosOrder((Node) this.root);
    }

    public Node getRoot() {
        return (Node) this.root;
    }

    public void setRoot(Node root) {
        if(this.current == null) this.current = root;
        this.root = root;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public int countNodes() {
        return Node.countNodes((Node) this.root);
    }

    public void printTree() {
        Node cast = (Node) this.root;
        cast.printTree("", true, "");
    }

    public Node getCurrent() {
        return (Node) this.current;
    }

    public void setCurrent(Node IntNode) {
        this.current = IntNode;
    }
}