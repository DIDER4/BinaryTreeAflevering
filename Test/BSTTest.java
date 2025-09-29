import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;

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

    @Test
    void testRemoveMin() {
        assertEquals(11, tree.removeMin());
        assertEquals(15, tree.findMin());
        assertEquals(8, tree.getSize());
    }

    @Test
    void testRemoveMax() {
        assertEquals(90, tree.removeMax());
        assertEquals(88, tree.findMax());
        assertEquals(8, tree.getSize());
    }

    @Test
    void testSearch() {
        assertTrue(tree.search(45));
        assertTrue(tree.search(11));
        assertTrue(tree.search(90));
        assertFalse(tree.search(100));
        assertFalse(tree.search(0));
    }

    @Test
    void testDelete() {
        // Slet et blad
        assertTrue(tree.delete(15));
        assertFalse(tree.search(15));
        assertEquals(8, tree.getSize());

        // Slet en node med tilhørende barn
        assertTrue(tree.delete(30));
        assertFalse(tree.search(30));
        assertEquals(7, tree.getSize());

        // Slet en node med 2 tilhørende børn
        assertTrue(tree.delete(22));
        assertFalse(tree.search(22));
        assertEquals(6, tree.getSize());

        // Slet root
        assertTrue(tree.delete(45));
        assertFalse(tree.search(45));
        assertEquals(5, tree.getSize());

        // Slet et ikke eksisterende element/node
        assertFalse(tree.delete(999));
        assertEquals(5, tree.getSize());
    }

    @Test
    void testGreaterThan() {
        ArrayList<Integer> result = tree.greaterThan(30);
        assertEquals(4, result.size());
        assertTrue(result.contains(45));
        assertTrue(result.contains(77));
        assertTrue(result.contains(88));
        assertTrue(result.contains(90));
    }

    @Test
    void testInorder() {
        tree.inorder();
    }

    @Test
    void testPreorder() {
        tree.preorder();
    }

    @Test
    void testPostorder() {
        tree.postorder();
    }

    @Test
    void testEmptyTree() {
        BST<Integer> emptyTree = new BST<>();
        assertTrue(emptyTree.isEmpty());
        assertEquals(0, emptyTree.getSize());
        assertEquals(-1, emptyTree.height());
        assertEquals(0, emptyTree.sum());
        assertNull(emptyTree.findMin());
        assertNull(emptyTree.findMax());
        assertNull(emptyTree.removeMin());
        assertNull(emptyTree.removeMax());
    }

    @Test
    void testNumberOfLeaves() {
        assertEquals(3, tree.numberOfLeaves()); // Træet har 3 blade

        // Test with empty tree
        BST<Integer> emptyTree = new BST<>();
        assertEquals(0, emptyTree.numberOfLeaves()); // Tomt træ har ingen blade

        // Test with single node tree
        BST<Integer> singleNodeTree = new BST<>();
        singleNodeTree.insert(42);
        assertEquals(1, singleNodeTree.numberOfLeaves()); // Single node har 1 blad
    }

    @Test
    void testHeightNodeCount() {
        assertEquals(1, tree.heightNodeCount(0));  // Root level
        assertEquals(2, tree.heightNodeCount(1));  // Level 1
        assertEquals(3, tree.heightNodeCount(2));  // Level 2
        assertEquals(3, tree.heightNodeCount(3));  // Level 3
        assertEquals(0, tree.heightNodeCount(4));  // Ingen noder på Level 4

        // Test med tomt træ
        BST<Integer> emptyTree = new BST<>();
        assertEquals(0, emptyTree.heightNodeCount(0));
    }
}