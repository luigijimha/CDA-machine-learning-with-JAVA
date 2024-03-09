package BinaryTree;

public class StringTreeDriver {
    public static void main(String[] args) {
        StringNode root = new StringNode("1");
        StringNode Node2 = new StringNode("2");
        StringNode Node3 = new StringNode("3");
        StringNode Node4 = new StringNode("4");
        StringNode Node5 = new StringNode("5");
        StringNode Node6 = new StringNode("6");
        StringNode Node7 = new StringNode("7");
        ABB tree = new ABB();
        tree.setRoot(root);

        root.setLeft(Node2);
        Node2.setLeft(Node4);
        Node2.setRight(Node5);
        Node5.setLeft(Node7);
        root.setRight(Node3);
        Node3.setRight(Node6);

        System.out.print("Count nodes in tree with 7 nodes added: ");
        System.out.println(tree.countNodes());

        System.out.print("In Order print: ");
        tree.printInOrder();

        System.out.print("\nPre Order print: ");
        tree.printPreOrder();

        System.out.print("\nPos Order print: ");
        tree.printPosOrder();
    }
}