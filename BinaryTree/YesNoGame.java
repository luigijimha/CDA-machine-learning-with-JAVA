package BinaryTree;
import java.util.Scanner;

public class YesNoGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;

        ABB tree = new ABB();
        StringNode A = new StringNode("¿Es un animal acuatico?");
        StringNode B = new StringNode("¿Puede volar?");
        StringNode C = new StringNode("¿Es un mamifero acuatico?");
        StringNode D = new StringNode("¿Tiene 4 patas");
        StringNode E = new StringNode("¿Es grande?");
        StringNode F = new StringNode("¿Es un tipo de pescado?");
        StringNode G = new StringNode("¿Es grande?");
        StringNode H = new StringNode("Pinguino");
        StringNode I = new StringNode("Perro");
        StringNode J = new StringNode("Pichon");
        StringNode K = new StringNode("Halcon");
        StringNode L = new StringNode("Pulpo");
        StringNode M = new StringNode("Salmon");
        StringNode N = new StringNode("Delfin");
        StringNode O = new StringNode("Ballena");

        // left = no, right = yes
        tree.setRoot(A);
        A.setLeft(B);
        A.setRight(C);
        B.setLeft(D);
        B.setRight(E);
        C.setLeft(F);
        C.setRight(G);
        D.setLeft(H);
        D.setRight(I);
        E.setLeft(J);
        E.setRight(K);
        F.setLeft(L);
        F.setRight(M);
        G.setLeft(N);
        G.setRight(O);

        System.out.println("Bienvenido al juego Yes/No");
        System.out.println("Piensa en un animal y voy a intentar adivinar de qué animal se trata");
        System.out.println("Cuando realize preguntas me vas a responder con y (yes) o n (no)");

        GameLoop:
        while(true) {
            System.out.print(tree.getCurrent().getData() + " (y/n) ");
            input = sc.nextLine();
            if(input.equals("y")){
                tree.setCurrent(tree.getCurrent().getRight());
            } else if (input.equals("n")) {
                tree.setCurrent(tree.getCurrent().getLeft());
            } else {
                System.out.println("Escribe y (yes) o n (no) para contestar");
            }

            if(tree.getCurrent().getLeft() == null && tree.getCurrent().getRight() == null) {
                while(true) {
                    System.out.print("El animal en el que estas pensando es " + tree.getCurrent().getData() + "? (y/n) ");
                    input = sc.nextLine();
                    if(input.equals("y")){
                        System.out.print("Buen juego, escribe 'y' si quieres jugar otra vez: ");
                        input = sc.nextLine();
                        if(input.equals("y")) {
                            tree.setCurrent(tree.getRoot());
                            break;
                        } else {
                            break GameLoop;
                        }
                    } else if (input.equals("n")) {
                        System.out.print("¿En qué animal estabas pensando? ");
                        String animal = sc.nextLine();
                        System.out.print("¿Qué pregunta formularias para diferenciar a un " + animal + " de un " + tree.getCurrent().getData() + "? ");
                        String question = sc.nextLine();
                        System.out.print("Escribe 'y' si responderias a esta pregunta con un sí para indicar que es un " + animal + ": ");
                        input = sc.nextLine();
                        if(input.equals("y")) {
                            StringNode right = new StringNode(animal);
                            StringNode left = new StringNode((String) tree.getCurrent().getData());
                            StringNode current = (StringNode) tree.getCurrent();
                            current.setData(question);
                            current.setLeft(left);
                            current.setRight(right);
                        } else {
                            StringNode right = new StringNode((String) tree.getCurrent().getData());
                            StringNode left = new StringNode(animal);
                            StringNode current = (StringNode) tree.getCurrent();
                            current.setData(question);
                            current.setLeft(left);
                            current.setRight(right);
                        }
                        System.out.print("Buen juego, escribe 'y' si quieres jugar otra vez: ");
                        input = sc.nextLine();
                        if(input.equals("y")) {
                            tree.setCurrent(tree.getRoot());
                            break;
                        } else {
                            break GameLoop;
                        }
                    } else {
                        System.out.println("Escribe y (yes) o n (no) para contestar");
                    }
                }
            }
        }
        sc.close();
    }
}
