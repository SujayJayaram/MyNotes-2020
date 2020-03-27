package com.suj.puzzlers.treesearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sujayjayaram on 28/02/2016.
 */
public class Node<T> {
    private final T value;

    private final List<Node<T>> children;

    public Node(T value) {
        this.value = value;
        children = new ArrayList<>();
    }

    public T getValue() {
        return value;
    }

    public void addChildNode(Node<T> node){
        children.add(node);
    }

    public List<Node<T>> getChildren() {
        return new ArrayList<>(children);
    }
}
