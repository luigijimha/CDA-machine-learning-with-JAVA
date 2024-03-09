package ID3Tree;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        try {
            // load the data and print it
            CSVData.loadCSV("modify me");
            CSVData.printArrayList();

            // get all row indexes
            List<Integer> indexes = new ArrayList<>();
            for (int i = 1; i < CSVData.getRowSize(); ++i) {
                indexes.add(i);
            }
            // get all column indexes
            List<Integer> columns = new ArrayList<>();
            for (int i = 0; i < CSVData.getColumnSize()-1; ++i) {
                columns.add(i);
            }

            // build tree
            Tree tree = new Tree(indexes, columns);

            // start user loop
            Scanner sc = new Scanner(System.in);

            String input;
            while(true) {
                System.out.println();
                System.out.println("Tell me what's the weather and I'll tell you if can go out to play");
                while(tree.getCurrent().getType() != NodeType.LeafNode) {
                    System.out.print(tree.getCurrent().getData() + ": ");
                    input = sc.nextLine();
                    tree.navigateToOption(input);
                }
                System.out.println(tree.getCurrent().getData());
                System.out.print("Type \"y\" if you want to ask again: ");
                input = sc.nextLine();
                if(input.equals("y")) {
                    tree.setCurrent(tree.getRoot());
                } else {
                    break;
                }
            }
            
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("CSV file not found. verify you path is correct at Main.java");
        }
    }
}
