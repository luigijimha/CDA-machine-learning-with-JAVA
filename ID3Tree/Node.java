package ID3Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {
    private Map<String, Node> children;
    private String data;
    private NodeType type;

    public Node(List<Integer> indexes, List<Integer> columns) {
        int pivotColumn = CSVData.getPivotColumn(indexes, columns);
        
        // current node is leaf node
        if(pivotColumn == -1) {
            int lastIndex = CSVData.getColumnSize() - 1;
            int index = indexes.get(0);
            String answer = CSVData.getData().get(index).get(lastIndex);
            this.type = NodeType.LeafNode;
            this.children = null;
            this.data = "The answer is " + answer;
            return;
        }

        this.children = new HashMap<>();

        // separate all children indexes per column option
        Map<String, List<Integer>> childrenIndexes = new HashMap<>();
        for(int i = 0; i < indexes.size(); ++i) {
            int rowIndex = indexes.get(i);
            String columnOption = CSVData.getData().get(rowIndex).get(pivotColumn);

            // add index to column option
            if (childrenIndexes.containsKey(columnOption)) {
                childrenIndexes.get(columnOption).add(rowIndex);
            } else {
                List<Integer> indexList = new ArrayList<>();
                indexList.add(rowIndex);

                childrenIndexes.put(columnOption, indexList);
            }
        }

        // remove pivot column from columns
        columns.removeIf(column -> column.equals(pivotColumn));

        // generate options and children nodes
        String data = "what is the " + CSVData.getData().get(0).get(pivotColumn) + " today? (";
        String options = "";
        for (String option : childrenIndexes.keySet()) {
            List<Integer> childIndexes = childrenIndexes.get(option);
            this.children.put(option, new Node(childIndexes, columns));
            options += option + ", ";
        }
        options = options.substring(0, options.length() - 2);
        data += options + ")";

        this.data = data;
        this.type = NodeType.Branch;
    }

    /* ---------------------- setter & getters ------------------------- */
    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return this.data;
    }

    public Map<String, Node> getChildren() {
        return this.children;
    }

    public void addChildren(String option, Node child) {
        this.children.put(option, child);
    }

    public void removeChildren() {
        this.children.clear();
    }

    public void setType(NodeType type) {
        this.type = type;
    }
    
    public NodeType getType() {
        return this.type;
    }

    /*void print(String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "\\-- " : "|-- " + this.data));
        for(int i = 0; i < this.children.size() - 1; ++i) {
            this.children.get(i).print(prefix + (isTail ? "    " : "|   "), false);
        }
        if(this.children.size() > 0) {
            this.children.get(this.children.size() - 1).print(prefix + (isTail ? "    " : "|   "), true);
        }
    }*/
}