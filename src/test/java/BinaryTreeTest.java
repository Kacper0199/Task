import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryTreeTest {

    @Test
    public void countLeafNodesTest() {
        // given
        BinaryTree tree = new BinaryTree();
        BinaryTree tree2 = new BinaryTree();
        BinaryTree tree3 = new BinaryTree();
        BinaryTree tree4 = new BinaryTree();
        int[] values = {5, 7, 3, 2, 5, 1, 0, 2, 8, 5};
        String[] instructions = {"L", "R", "LL", "LLL", "LR", "RL", "RR", "RRL", "RRR", "RRRR"};
        String[] instructions2 = {"L", "LL", "LLL"};

        // when
        for (int i=0; i < values.length; i++) {
            tree.insert(instructions[i], values[i]);
        }

        for (int i=0; i < 2; i++) {
            tree2.insert(instructions[i], values[i]);
        }

        for (int i=0; i < instructions2.length; i++) {
            tree3.insert(instructions2[i], values[i]);
        }

        tree4.insert("L", 1);

        // then
        assertEquals(tree.countLeafNodes(), 5);
        assertEquals(tree2.countLeafNodes(), 1);
        assertEquals(tree3.countLeafNodes(), 1);
        assertEquals(tree4.countLeafNodes(), 1);
    }

    @Test
    public void maxPathLengthTest() {
        // given
        BinaryTree tree = new BinaryTree();
        BinaryTree tree2 = new BinaryTree();
        int[] values = {5, 7, 3, 2, 5, 1, 0, 2, 8, 5};
        String[] instructions = {"L", "R", "LL", "LLL", "LR", "RL", "RR", "RRL", "RRR", "RRRR"};

        // when
        for (int i=0; i < values.length; i++) {
            tree.insert(instructions[i], values[i]);
        }

        tree2.insert("L", 5);

        // then
        assertEquals(tree.maxPathLength(), 4);
        assertEquals(tree2.maxPathLength(), 0);
    }

    @Test
    public void equalsTest() {
        // given
        BinaryTree tree = new BinaryTree();
        BinaryTree tree2 = new BinaryTree();
        BinaryTree tree3 = new BinaryTree();
        int[] values = {5, 7, 3, 2, 5, 1, 0, 2, 8, 5};
        String[] instructions = {"L", "R", "LL", "LLL", "LR", "RL", "RR", "RRL", "RRR", "RRRR"};

        // when
        for (int i=0; i < values.length; i++) {
            tree.insert(instructions[i], values[i]);
        }

        for (int i=0; i < values.length; i++) {
            tree2.insert(instructions[i], values[i]);
        }

        tree3.insert("L", 10);

        // then
        assertTrue(tree.equals(tree2));
        assertFalse(tree.equals(tree3));
    }
}
