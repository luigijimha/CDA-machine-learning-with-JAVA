package ID3Tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CSVData {
    private static List<ArrayList<String>> data;
    private static int columnSize;
    private static int rowSize;

    /**
     * load and store CSV data
     * @param filepath                  route to csv data file
     * @throws FileNotFoundException    verify you filepath
     */
    public static void loadCSV(String filepath) throws FileNotFoundException{
        List<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        Scanner scan = new Scanner(new File(filepath));
        while (scan.hasNext()) {
            String line = scan.nextLine();
            ArrayList<String> lineArrayList = new ArrayList<String>(Arrays.asList(line.split(",")));
            data.add(lineArrayList);
        }
        scan.close();
        CSVData.data = data;
        CSVData.columnSize = data.get(0).size();
        CSVData.rowSize = data.size();
    }

    /**
     * prints CSV data table
     */
    public static void printArrayList() {
        int colnum = 0;
        List<Integer> maxcolwidths = new ArrayList<Integer>();
        for(ArrayList<String> row : CSVData.data) {
            for(String item : row) {
                if (maxcolwidths.size() <= colnum) {
                    maxcolwidths.add(item.length());
                } else if (item.length() > maxcolwidths.get(colnum)) {
                    maxcolwidths.set(colnum, item.length());
                }
                colnum++;
            }
        }
        colnum = 0;
        for(ArrayList<String> row : CSVData.data) {
            for(String item : row) {
                String format = " | %-" + maxcolwidths.get(colnum) + "s";
                System.out.printf(format, item);
                colnum++;
            }
            System.out.println();
        }
        colnum = 0;
        System.out.println();
        System.out.println();
    }

    /**
     * calculates the entropy given a possitive probability
     * @param pYes     possitive probability
     * @return         entropy of the option in the column
     */
    public static double calculateEntropy(double pYes) {
        double pNo = 1 - pYes;
        /*System.out.print("pYes: ");
        System.out.println(pYes);
        System.out.print("pNo: ");
        System.out.println(pNo);*/

        return -1 * pYes * (Math.log(pYes) / Math.log(2)) - pNo * (Math.log(pNo) / Math.log(2));
    }

    /**
     * calculates the gain given a specified group and column index
     * @param indexes    the list of row indexes to work with
     * @param index      index of the column to get entropy from
     * @return           total gain of the column
    */
    public static double calculateGain(List<Integer> indexes, int index) {
        // play index of data set (last index)
        int lastIndex = CSVData.columnSize - 1;

        // calculate probability for yes/no
        // total "yes" values in data segment
        int totalYes = 0;
        // map that stores total options and yes probability for each option
        // List is used instead of a pair structure 'cuz java doesn't have pair structures
        // index 0 stores total times option is repeated, index 1 stores total yes of option
        Map<String, List<Integer>> optionProbabilities = new HashMap<>();

        for(int i = 0; i < indexes.size(); ++i) {
            // play value of current row
            String playValue = CSVData.data.get(indexes.get(i)).get(lastIndex);
            // option value of current row
            String columnValue = CSVData.data.get(indexes.get(i)).get(index);

            // verify current option is registered in dictionary
            if (optionProbabilities.containsKey(columnValue)) {
                // update total repetitions of row
                int totalOption = optionProbabilities.get(columnValue).get(0);
                optionProbabilities.get(columnValue).set(0, totalOption + 1);
            // else add current option with 1 repetition and 0 yes values found
            } else {
                List<Integer> optionPair = new ArrayList<>();
                optionPair.add(1);
                optionPair.add(0);

                optionProbabilities.put(columnValue, optionPair);
            }
            // verify if play option of current row is Yes
            if("Yes".equals(playValue)) {
                // increase total Yes of data segment
                ++totalYes;
                // update total yes of option
                int totalYesOption = optionProbabilities.get(columnValue).get(1);
                optionProbabilities.get(columnValue).set(1, totalYesOption + 1);
            }
        }
        double pYes = (double) totalYes / indexes.size();

        // calculate column gain
        double gain = calculateEntropy(pYes);

        for (Map.Entry<String, List<Integer>> entry : optionProbabilities.entrySet()) {
            int totalOption = entry.getValue().get(0);
            int totalYesOption = entry.getValue().get(1);

            double pOption = (double) totalYesOption / totalOption;
            double columnEntropy = pOption != 1 && pOption != 0 ? calculateEntropy(pOption) : 0.0;
            double pTotalOption = (double) totalOption/indexes.size();
            gain -= pTotalOption * columnEntropy;
        }

        return gain;
    }

    /**
     * finds the column with the highest gain, returns -1 if it is a leaf node
     * @param indexes    row indexes of current data set
     * @param columns    column indexes of current data set
     * @return           column index with the highest gain
     */
    public static int getPivotColumn(List<Integer> indexes, List<Integer> columns) {
        int pivotColumn = -1;
        double pivotGain = 0;
        // verify current node is not a leaf
        int lastIndex = CSVData.columnSize - 1;
        int totalYes = 0;
        for(int i = 0; i < indexes.size(); ++i) {
            String playValue = CSVData.data.get(indexes.get(i)).get(lastIndex);
            if("Yes".equals(playValue)) ++totalYes;
        }
        // current node is leaf node
        if(totalYes == 0 || totalYes == indexes.size()) return pivotColumn;

        // find pivot column
        for(int i = 0; i < columns.size(); ++i) {
            double columnGain = CSVData.calculateGain(indexes, columns.get(i));
            if(columnGain > pivotGain) {
                pivotGain = columnGain;
                pivotColumn = columns.get(i);
            }
        }

        return pivotColumn;
    }

    /* ----------------------- getters ----------------------- */
    public static List<ArrayList<String>> getData() {
        return CSVData.data;
    }

    public static int getColumnSize() {
        return CSVData.columnSize;
    }

    public static int getRowSize() {
        return CSVData.rowSize;
    }
}
