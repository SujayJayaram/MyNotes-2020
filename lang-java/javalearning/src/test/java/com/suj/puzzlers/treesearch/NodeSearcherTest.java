package com.suj.puzzlers.treesearch;

import org.apache.hadoop.yarn.webapp.hamlet.HamletSpec;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Predicate;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by sujayjayaram on 28/02/2016.
 */
public class NodeSearcherTest {

    private Node<Integer> getSampleIntegerTree() {

        // Sample nodes
        Node<Integer> n1 = new Node<>(1);
        Node<Integer> n2 = new Node<>(2);
        Node<Integer> n3 = new Node<>(3);
        Node<Integer> n4 = new Node<>(4);
        Node<Integer> n5 = new Node<>(5);
        Node<Integer> n6 = new Node<>(6);
        Node<Integer> n7 = new Node<>(7);
        Node<Integer> n8 = new Node<>(8);

        // Wire up
        n1.addChildNode(n2);
        n1.addChildNode(n3);
        n1.addChildNode(n4);
        n2.addChildNode(n5);
        n5.addChildNode(n6);
        n2.addChildNode(n7);
        n7.addChildNode(n8);

        return n1;
    }

    @Test
    public void testDepthFirstSearch() throws Exception {
//        NodeSearcher<Integer> searcher = new NodeSearcher<>( i -> (i.getValue() > 3),
//                                                                true);

        NodeSearcher<Integer> searcher = new NodeSearcher<>( i -> (false),
                false);

        Node n = searcher.search(getSampleIntegerTree());
    }

    //@Test
    public void testInsertRetrieveOrder() throws Exception {
        // Sample nodes
        Node<Integer> n1 = new Node<>(1);
        Node<Integer> n2 = new Node<>(2);
        Node<Integer> n3 = new Node<>(3);
        Node<Integer> n4 = new Node<>(4);

        {
            Queue<Node<Integer>> queue = new LinkedList<>(); // Normal queue
            queue.add(n1);
            queue.add(n2);
            queue.add(n3);
            queue.add(n4);

            assertThat(queue.remove().getValue(), equalTo(1));
        }

        {
            ArrayDeque<Node<Integer>> queue = new ArrayDeque<>(); // Stack
            queue.add(n1);
            queue.add(n2);
            queue.add(n3);
            queue.add(n4);

            assertThat(queue.remove().getValue(), equalTo(1));
            assertThat(queue.pop().getValue(), equalTo(4));
        }
    }
}