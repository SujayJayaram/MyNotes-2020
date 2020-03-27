package com.suj.puzzlers.treesearch;

import java.util.*;
import java.util.function.Predicate;

/**
 * Created by sujayjayaram on 28/02/2016.
 */
public class NodeSearcher<T> {

    private final Predicate<Node<T>> match;

    // We cannot use a Queue/Deque interface for both these
    // purposes as Deque does not return 'pop()' when you call remove()!
    private final List<Node<T>> queue = new ArrayList<>();

    private final boolean depthFirst;

    private final Set<Node<T>> processedNodes = new HashSet<>();

    public NodeSearcher(Predicate<Node<T>> match, boolean depthFirst) {
        this.match = match;
        this.depthFirst = depthFirst;
    }

    /**
     * Searches the supplied tree
     * @param node the root of the tree to search
     * @return the first occurence that matches or null if nothing found
     */
    public Node<T> search(Node<T> node) {
        System.out.println("Processing node: " + node.getValue());

        if ( match.test(node) )
            return node;

        processedNodes.add(node);

        node.getChildren().forEach( child -> {
            if ( !processedNodes.contains(child) )
                queue.add(child);
        });

        if ( !queue.isEmpty() )
            return search( getNextQueueItem() );
        else
            return null;
    }

    public Node<T> getNextQueueItem() {
        if ( depthFirst )
            return queue.remove(queue.size()-1);
        else
            return queue.remove(0);
    }
}
