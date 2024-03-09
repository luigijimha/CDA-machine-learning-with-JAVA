package BinaryTree;

public class IntTreeDriver {
    public static void main(String[] args) {
        System.out.println("Creating Tree");
        ABB tree = new ABB();

        System.out.print("Count nodes in empty tree: ");
        System.out.println(tree.countNodes());

        System.out.println("Create nodes with data 1");
        IntNode root = new IntNode(1);

        System.out.println("Set nodes as root");
        tree.setRoot(root);

        System.out.print("Count nodes in tree with only root added: ");
        System.out.println(tree.countNodes());
        IntNode Node2 = new IntNode(2);
        IntNode Node3 = new IntNode(3);
        IntNode Node4 = new IntNode(4);
        IntNode Node5 = new IntNode(5);
        IntNode Node6 = new IntNode(6);
        IntNode Node7 = new IntNode(7);

        root.setLeft(Node2);
        Node2.setLeft(Node4);
        Node2.setRight(Node5);
        Node5.setLeft(Node7);
        root.setRight(Node3);
        Node3.setRight(Node6);

        System.out.println("Set the current IntNode to be the root");
        tree.setCurrent(tree.getRoot());

        System.out.print("Display the current nodes: ");
        System.out.println(tree.getCurrent().getData());

        IntNode currentNode = (IntNode) tree.getCurrent();

        System.out.print("Count nodes in tree with 7 nodes added: ");
        System.out.println(tree.countNodes());

        System.out.print("In Order print: ");
        tree.printInOrder();

        System.out.print("\nPre Order print: ");
        tree.printPreOrder();

        System.out.print("\nPos Order print: ");
        tree.printPosOrder();

        System.out.println("\nDisplay the nodes as tree diagrams");
        tree.printTree();
    }
}
