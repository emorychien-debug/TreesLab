package edu.ttap.bsts;

import java.util.ArrayList;
import java.util.List;

/**
 * A binary tree that satisifies the binary search tree invariant.
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

    ///// From the reading

    /**
     * A node of the binary search tree.
     */
    private static class Node<T> {
        public T value;
        public Node<T> left;
        public Node<T> right;

        /**
         * @param value the value of the node
         * @param left the left child of the node
         * @param right the right child of the node
         */
        public Node(T value, Node<T> left, Node<T> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        /**
         * @param value the value of the node
         */
        public Node(T value) {
            this(value, null, null);
        }
    }

    private Node<T> root;

    /**
     * Constructs a new empty binary search tree.
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * @param node the root of the tree
     * @return the number of elements in the specified tree
     */
    private int sizeH(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + sizeH(node.left) + sizeH(node.right);
        }
    }

    /**
     * @return the number of elements in this tree
     */
    public int size() {
        return sizeH(root);
    }

    ///// Part 1: Insertion

    /**
     * Inserts the given value into this binary search tree.
     * @param v the value to insert
     */
    public void insert(T v) {
        root = insertH(root, v);
    }
    private Node<T> insertH (Node<T> tree, T val){
        if (tree == null) {
            return new Node<T>(val);
        } else {
            if(val.compareTo(tree.value) < 0){
                return new Node<>(tree.value, insertH(tree.left, val), tree.right);
            } else {
                return new Node<>(tree.value, tree.left, insertH(tree.right, val));
            }
        }
    }

    ///// Part 2: Contains
   
    /**
     * @param v the value to find
     * @return true iff this tree contains <code>v</code>
     */
    // if tree is empty, returns false;
    // otherwise, if val we are looking for matches, return true;
    // if desired val is less recurse left; more recurse right
    public boolean contains(T v) {
        return containsH(v, root);
    }
    private boolean containsH(T v, Node<T> tree) {
        if(tree == null) {
            return false;
        }
        if(tree.value.compareTo(v) == 0) {
            return true;
        }
        if(tree.value.compareTo(v) < 0) {
            return containsH(v, tree.right);
        }
        return containsH(v, tree.left);
    }

    ///// Part 3: Ordered Traversals

    /**
     * @return the (linearized) string representation of this BST
     */
    @Override
    public String toString() {
        String stringified = new String();
        stringified = stringified + "[";
        if (root != null) {
            stringified += toStringH(root.left, true);
            stringified = stringified + root.value;
            stringified += toStringH(root.right, false);
        }
        stringified += "]";
        return stringified;
    }

    private String toStringH(Node<T> node, boolean left){
        if (node != null) {
            if (left){
                return toStringH(node.left, left) + node.value +", " + toStringH(node.right, left);
            }
            return toStringH(node.left, left) + ", " + node.value + toStringH(node.right, left);
        }
        return "";
    }

    /**
     * @return a list contains the elements of this BST in-order.
     */
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        toListH(list, root);
        return list;
    }

    private void toListH(List<T> elements, Node<T> cur) {
        if(cur == null) {
            return;
        }
        toListH(elements, cur.left);
        elements.add(cur.value);
        toListH(elements, cur.right);
    }

    ///// Part 4: BST Sorting

    /**
     * @param <T> the carrier type of the lists
     * @param lst the list to sort
     * @return a copy of <code>lst</code> but sorted
     * @implSpec <code>sort</code> runs in ___ time if the tree remains balanced. 
     */
    public static <T extends Comparable<? super T>> List<T> sort(List<T> lst) {
        if(lst == null) {
            return lst;
        }
        BinarySearchTree<T> bst = new BinarySearchTree<T>();
        for (T i : lst) {
            bst.insert(i);
        }
        return (bst.toList());
    }

    ///// Part 5: Deletion
  
    /*
     * The three cases of deletion are:
     * 1. The node has no children
     * 2. The node has one child
     * 3. The node has two children
     */

    /**
     * Modifies the tree by deleting the first occurrence of <code>value</code> found
     * in the tree.
     *
     * @param value the value to delete
     */
    public void delete(T value) {
        root = deleteH(value, root);
    }
    private Node<T> deleteH(T value, Node<T> node) {
        if(node.value.compareTo(value) == 0) {
            return deleteNode(node); // check num of children & act accordingly. return updated node.
        } else if(node.value.compareTo(value) < 0) {
            return new Node<T>(node.value, node.left, deleteH(value, node.right));
        } else {
            return new Node<T>(node.value, deleteH(value, node.left), node.right);
        }
    }
    private Node<T> deleteNode(Node<T> node) {
        if (node.right == null && node.left == null){ // has no children
            return null;
        } else if (node.left != null && node.right == null) { // has only left child
            return node.left;
        } else if(node.right != null && node.left == null) { // has only right child
            return node.right;
        } else { // has 2 children
            Node<T> cur = node.right; 
            Node<T> parent = node;
            while(cur.left != null) { //find smallest value from right of tree below value to be deleted
                parent = cur;
                cur = cur.left;
            }
            node.value = cur.value;
            if(parent == node) { 
                parent.right = null;
            } else {
                parent.left = null;
            }
            return node;
        }
    }
}
