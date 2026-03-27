package edu.ttap.bsts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BinarySearchTreeTest {

    private BinarySearchTree<Integer> mkSampleTree() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        tree.insert(3);
        tree.insert(5);
        tree.insert(2);
        tree.insert(6);
        tree.insert(4);
        return tree;
    }

    @Test
    public void emptyTreeTest() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        assertEquals(0, tree.size());
    }

    @Test
    public void basicSizeInsertTest() {
        BinarySearchTree<Integer> tree = mkSampleTree();
        assertEquals(5, tree.size());
    }

    @Test
    public void basicContainsTest() {
        BinarySearchTree<Integer> tree = mkSampleTree();
        assertTrue(tree.contains(5));
        assertFalse(tree.contains(8));
    }

    @Test
    public void basicToStringTest() {
        assertEquals("[0, 5, 6, 7, 11]", mkSampleTree().toString());
    }

    @Test
    public void basicToListTest() {
        // N.B., need to upcast the Integer[] array to avoid a ClassCastException that
        // arises from downcasting the result of toArray to Integer[].
        assertArrayEquals((Object[]) new Integer[] {0, 5, 6, 7, 11},
                          mkSampleTree().toList().toArray());
    }

    @Test
    public void basicSortTest() {
        assertArrayEquals(
            (Object[]) new Integer[] {0, 5, 6, 7, 11},
            BinarySearchTree.<Integer> sort(
                java.util.Arrays.asList(0, 5, 6, 7, 11)).toArray());
    }

    @Test
    public void basicDeleteTest() {
        BinarySearchTree<Integer> tree = mkSampleTree();
        assertEquals(5, tree.size());
        assertTrue(tree.contains(6));
        tree.delete(6);
        assertEquals(4, tree.size());
        assertFalse(tree.contains(6));
    }
}
