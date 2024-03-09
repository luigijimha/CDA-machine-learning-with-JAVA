package ID3Tree;

import java.util.List;

public class Tree {
    private Node root;
    private Node current;

    public Tree(List<Integer> indexes, List<Integer> columns) {
        this.root = new Node(indexes, columns);
        this.current = this.root;
        this.root.setType(NodeType.RootNode);
    }

    /* -------------------------- setters & getters ------------------------ */
    public Node getRoot() {
        return this.root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getCurrent() {
        return this.current;
    }

    public void setCurrent(Node current) {
        this.current = current;
    }

    /**
     * special setter for current node. used to navigate from a parent node to one of its children nodes
     * @param option    key name of child node
     */
    public void navigateToOption(String option) {
        // if no key was found, a typo is assumed and setter is not triggered
        if (this.current.getChildren().containsKey(option)) {
            this.current = this.current.getChildren().get(option);
        }
    }

    public boolean isEmpty() {
        return (root == null);
    }

    /*public void print() {
        root.print("", true);
    }*/
}