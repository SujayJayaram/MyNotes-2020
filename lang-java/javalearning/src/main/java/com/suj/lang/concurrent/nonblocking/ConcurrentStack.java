package com.suj.lang.concurrent.nonblocking;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by sujayjayaram on 24/01/2016.
 */
// Non blocking Stack Using Treiber's Algorithm (Treiber, 1986).
// @ThreadSafe
public class ConcurrentStack <E> {
    AtomicReference<Node<E>> top = new AtomicReference<Node<E>>();
    public void push(E item) {
        Node<E> newHead = new Node<E>(item);
        Node<E> oldHead;
        do {
            oldHead = top.get();
            newHead.next = oldHead;
        } while (!top.compareAndSet(oldHead, newHead));
    }
    public E pop() {
        Node<E> oldHead;
        Node<E> newHead;
        do {
            oldHead = top.get();
            if (oldHead == null)
                return null;
            newHead = oldHead.next;

            // Set everything up so that this is the only atomic assignment,
            // and be prepared to retry the in failure case.
        } while (!top.compareAndSet(oldHead, newHead));
        return oldHead.item;
    }
    private static class Node <E> {
        public final E item;
        public Node<E> next;
        public Node(E item) {
            this.item = item;
        } }
}