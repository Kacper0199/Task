import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        BinaryTree tree = new BinaryTree();
        int[] values = {5, 7, 3, 2, 5, 1, 0, 2, 8, 5};
        String[] instructions = {"L", "R", "LL", "LLL", "LR", "RL", "RR", "RRL", "RRR", "RRRR"};

        for (int i=0; i < values.length; i++) {
            tree.insert(instructions[i], values[i]);
        }

        System.out.println("-----------------------------------------------");
        System.out.println("Count leaf nodes: ");
        System.out.println();

        int leafNodeCount = tree.countLeafNodes();
        System.out.println("Number of leaf nodes -> " + leafNodeCount);


        System.out.println();
        System.out.println("-----------------------------------------------");
        System.out.println("Max path from root to leaf: ");
        System.out.println();

        int maxPathLength = tree.maxPathLength();
        System.out.println("Largest number of edges from root to leaf -> " + maxPathLength);


        System.out.println();
        System.out.println("-----------------------------------------------");
        System.out.println("Equal trees: ");
        System.out.println();

        BinaryTree tree2 = new BinaryTree();
        for (int i=0; i < values.length; i++) {
            tree2.insert(instructions[i], values[i]);
        }

        boolean areEqual = tree.equals(tree2);
        System.out.println("tree == tree2 -> " + areEqual);
        System.out.println();

        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");

        System.out.println();
        System.out.println("-----------------------------------------------");
        System.out.println("Total values of product categories: ");
        System.out.println();
        Products prod = new Products();
        Map<String, Double> categoryValues = prod.getCategoryValues();
        System.out.println("Electronics total -> " + categoryValues.get("electronics"));
        System.out.println("Women's clothing total -> " + categoryValues.get("women's clothing"));
        System.out.println("Men's clothing total -> " + categoryValues.get("men's clothing"));
        System.out.println("Jewelery total -> " + categoryValues.get("jewelery"));

        System.out.println();
        System.out.println("-----------------------------------------------");
        System.out.println("Highest quantity and user: ");
        System.out.println();
        Carts carts = new Carts(0);
        Data data = new Data(0);
        int cartUserMax = carts.getMax();
        System.out.println("Highest quantity -> " + cartUserMax);
        Data maxOwner = new Data(carts.getMaxId()-1);
        System.out.print("Owner with highest quantity -> ");
        System.out.println(maxOwner.getFirstName() + " " + maxOwner.getLastName());

        System.out.println();
        System.out.println("-----------------------------------------------");
        System.out.println("Furthest living users: ");
        System.out.println();
        data.furthestUsers();
        Data[] furthestUsers = data.getFurthest();
        System.out.println("First furthest living user -> " + furthestUsers[0].getFirstName() + " " + furthestUsers[0].getLastName());
        System.out.println("Second furthest living user -> " + furthestUsers[1].getFirstName() + " " + furthestUsers[1].getLastName());
    }
}
