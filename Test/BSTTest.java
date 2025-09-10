import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {
    private BST<Integer> tree;


    @BeforeEach
    void setUp() {
        tree = new BST<>();
        int[] values = {45, 22, 77, 11, 30, 90, 15, 25, 88};
        for (int v : values) tree.insert(v);
    }

    @Test
    void testPreorder() {
        String output = captureOutput(() -> tree.preorder());
        assertEquals("45 22 11 15 30 25 77 90 88", output);
    }

    @Test
    void testInorder() {
        String output = captureOutput(() -> tree.inorder());
        assertEquals("11 15 22 25 30 45 77 88 90", output);
    }

    @Test
    void testPostorder() {
        String output = captureOutput(() -> tree.postorder());
        assertEquals("15 11 25 30 22 88 90 77 45", output);
    }

    @Test
    void testIsLeaf() {
        assertTrue(tree.isLeaf(15));
        assertTrue(tree.isLeaf(25));
        assertFalse(tree.isLeaf(22));
    }

    @Test
    void testIsInternal() {
        assertTrue(tree.isInternal(22));
        assertTrue(tree.isInternal(77));
        assertFalse(tree.isInternal(78));
    }

    @Test
    void testHeight() {
        assertEquals(3, tree.height());
    }

    @Test
    void testSum() {
        assertEquals(403, tree.sum());
    }

    @Test
    void testFindMin() {
        assertEquals(11, tree.findMin());
    }

    @Test
    void testFindMax() {
        assertEquals(90, tree.findMax());
    }
}