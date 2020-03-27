package com.suj.trees;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sujayjayaram on 05/12/2016.
 * Binary Search Tree
 */
//@SuppressWarnings("all")
public class BinarySearchTreeNode<T extends Comparable<T>> {
    private BinarySearchTreeNode<T> left;
    private BinarySearchTreeNode<T> right;
    private T val;

    public BinarySearchTreeNode(BinarySearchTreeNode<T> left, BinarySearchTreeNode<T> right, T val) {
        if ( val == null )
            throw new RuntimeException("Null values not allowed!");

        this.left = left;
        this.right = right;
        this.val = val;
    }

    // Creates a new balanced tree from the items using the medians of the sub arrays as the root/nodes
    public BinarySearchTreeNode(Class<T> cls, T[] array) {
        if ( (array == null) || array.length == 0 )
            throw new RuntimeException("Null values not allowed!");

        Arrays.sort(array);
        int idxMedian = array.length/2;

        // length 5 -> item 3 (idx = 2)
        // length 6 -> item 4 (idx = 3)
        // length 1 -> item 1 (idx = 0)
        // length 2 -> item 2 (idx = 1)

        val = array[idxMedian];
        if ( idxMedian > 0 ) {
            T[] arrayLHS = (T[]) Array.newInstance(cls, idxMedian);
            for(int i = 0; i < idxMedian; i++)
                arrayLHS[i] = array[i];

            this.left = new BinarySearchTreeNode<>(cls, arrayLHS);
        }

        if ( (idxMedian+1) < array.length ) {
            T[] arrayRHS = (T[]) Array.newInstance(cls, array.length - (idxMedian+1));
            for(int i = idxMedian+1; i < array.length; i++)
                arrayRHS[i - (idxMedian+1)] = array[i];

            this.right = new BinarySearchTreeNode<>(cls, arrayRHS);
        }
    }

    public BinarySearchTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinarySearchTreeNode<T> left) {
        this.left = left;
    }

    public BinarySearchTreeNode getRight() {
        return right;
    }

    public void setRight(BinarySearchTreeNode<T> right) {
        this.right = right;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        if ( val == null )
            throw new RuntimeException("Null values not allowed!");

        this.val = val;
    }

    public BinarySearchTreeNode<T> lookUp(T item) {
        if ( item == null )
            throw new RuntimeException("Null values not allowed!");

        if ( item.equals(val) )
            return this;

        if ( item.compareTo(val) < 0 ) {
            if ( left == null )
                return null;

            return left.lookUp(item);
        }
        else if ( item.compareTo(val) > 0 ) {
            if ( right == null )
                return null;

            return right.lookUp(item);
        }

        // compareTo() should never return 0 as there is an equality check above
        throw new RuntimeException("Should never get here!");
    }

    public void insert(T item){
        if ( item == null )
            throw new RuntimeException("Null values not allowed!");

        if ( item.compareTo(val) <= 0 ) {
            if ( left == null )
                left = new BinarySearchTreeNode<>(null, null, item);
            else
                left.insert(item);
        }
        else if ( item.compareTo(val) > 0 ) {
            if ( right == null ) {
                right = new BinarySearchTreeNode<>(null, null, item);
            }
            else {
                right.insert(item);
            }
        }
    }

    public int size() {
        return 1 +
                ( (left != null) ? left.size() : 0 ) +
                ( (right != null) ? right.size() : 0 );
    }

    public int maxDepth() {
        int depthLeft = 1 + ( (left != null) ? left.maxDepth() : 0 );
        int depthRight = 1 + ( (right != null) ? right.maxDepth() : 0 );

        return (depthLeft >= depthRight) ? depthLeft : depthRight;
    }

    public void printTree(){
        // Build up a list of lists. Each inner list is an array of elements at the same tree depth.
        // The list is from root node to the max depth.
        List<List<String>> listOfLists = new ArrayList<>();
        addInfo(0, listOfLists);

        for(List<String> list : listOfLists) {
            StringBuilder sb = new StringBuilder();
            for(String s : list)
                sb.append(s).append(", ");

            System.out.print(sb + "\r\n");
        }
    }

    /**
     * Adds this nodes information into the metadata about this tree
     * @param treeDepth the level for this node. Root is level zero
     * @param listOfLists the list structure used to contain this information.
     *                    Each item is a further list of 'val' objects.
     */
    private void addInfo(int treeDepth, List<List<String>> listOfLists) {
        List<String> list = getListForTreeDepth(treeDepth, listOfLists);
        list.add(val.toString());

        addChildNodeInfo(left, treeDepth + 1, listOfLists);
        addChildNodeInfo(right, treeDepth + 1, listOfLists);
    }

    private void addChildNodeInfo(BinarySearchTreeNode<T> child, int treeDepth, List<List<String>> listOfLists) {
        if ( child != null) {
            child.addInfo(treeDepth, listOfLists); // Recurse to the child
        }
        else {
            // No child present
            List<String> list = getListForTreeDepth(treeDepth, listOfLists);
            list.add("X");
        }
    }

    private List<String> getListForTreeDepth(int currentLevel, List<List<String>> listOfLists) {
        List<String> list = null;
        if ( listOfLists.size() > currentLevel)
            list = listOfLists.get(currentLevel);

        if ( list == null ) {
            list = new ArrayList<>();
            listOfLists.add(currentLevel, list);
        }
        return list;
    }

    public List<T> asList() {
        List<T> list = new ArrayList<>();

        if ( left != null )
            left.asList().forEach(e -> list.add(e));

        list.add(val);

        if ( right != null )
            right.asList().forEach(e -> list.add(e));

        return list;
    }
}
