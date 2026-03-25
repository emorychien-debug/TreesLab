package edu.ttap.trees;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TreeTest {
    @Test
    public void emptyTreeSizeTest() {
        Tree<Integer> tree = new Tree<Integer>();
        assertEquals(0, tree.size());
    }

    @Test
    public void sampleTreeSizeTest() {
        assertEquals(9, Tree.makeSampleTree().size());
    }

    @Test
    public void emptyTreeContainsTest() {
        Tree<Integer> tree = new Tree<Integer>();
        assertFalse(tree.contains(7));
        assertFalse(tree.contains(4));
    }

    @Test
    public void sampleTreeContainsTest() {
        Tree<Integer> sampleTree = Tree.makeSampleTree();
        assertTrue(sampleTree.contains(6));
        assertFalse(sampleTree.contains(4));
    }

    @Test
    public void basicToListInorderTest() {
        // N.B., need to upcast the Integer[] array to avoid a ClassCastException that
        // arises from downcasting the result of toArray to Integer[].
        assertArrayEquals((Object[]) new Integer[] {1, 2, 3, 5, 6, 7, 8, 9, 10},
                          Tree.makeSampleTree().toListInorder().toArray());
    }

    @Test
    public void basicToListPreorderTest() {
        assertArrayEquals((Object[]) new Integer[] {5, 2, 1, 3, 8, 7, 6, 9, 10},
                          Tree.makeSampleTree().toListPreorder().toArray());
    }

    @Test
    public void basicToListPostorderTest() {
        assertArrayEquals((Object[]) new Integer[] {1, 3, 2, 6, 7, 10, 9, 8, 5},
                          Tree.makeSampleTree().toListPostorder().toArray());
    }

    @Test
    public void emptyTreeToStringTest() {
        Tree<Integer> tree = new Tree<Integer>();
        assertEquals("[]", tree.toString());
    }

    @Test
    public void sampleTreeToStringTest() {
        Tree<Integer> sampleTree = Tree.makeSampleTree();
        assertEquals("TODO: fille me in with the intended results of your toString implementation",
            sampleTree.toString());
    }

}