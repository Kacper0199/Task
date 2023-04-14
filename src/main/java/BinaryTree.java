public class BinaryTree {
    private Node root;

    private static class Node {
        int value;
        Node left, right;

        Node(int val) {
            value = val;
            left = null;
            right = null;
        }
    }

    public void insert(String instructions, int value) {
        if (root == null) {
            root = new Node(value);
            return;
        }

        Node current = root;
        for (char instruction : instructions.toCharArray()) {
            if (instruction == 'L') {
                if (current.left == null) {
                    current.left = new Node(value);
                    return;
                } else {
                    current = current.left;
                }
            } else if (instruction == 'R') {
                if (current.right == null) {
                    current.right = new Node(value);
                    return;
                } else {
                    current = current.right;
                }
            }
        }
    }

    public int countLeafNodes() {
        return countLeafNodes(root);
    }

    private int countLeafNodes(Node node) {
        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            return 1;
        }

        int leftCount = countLeafNodes(node.left);
        int rightCount = countLeafNodes(node.right);

        return leftCount + rightCount;
    }

    public int maxPathLength() {
        return maxPathLength(root) - 1;
    }

    private int maxPathLength(Node node) {
        if (node == null)
            return 0;

        int leftPathLength = maxPathLength(node.left);
        int rightPathLength = maxPathLength(node.right);

        return 1 + Math.max(leftPathLength, rightPathLength);
    }

    public boolean equals(BinaryTree other) {
        return equals(root, other.root);
    }

    private boolean equals(Node node1, Node node2) {
        if (node1 == null && node2 == null)
            return true;

        if (node1 == null || node2 == null)
            return false;

        if (node1.value != node2.value)
            return false;

        return equals(node1.left, node2.left) && equals(node1.right, node2.right);
    }
}
