package com.top30javainterviewquestions;

public class Solution {

    public static void main(String[] args) {
        LinkedLint<Integer> list = new LinkedLint<>();

        int debugMe = 0;
    }

    static class Node<T> {
        T val;
        Node next;
    }

    static class LinkedLint<T> {
        Node<T> head;

        void add(T val) {
            Node<T> newNode = new Node();
            newNode.val = val;

            if ( head == null ) {
                head = newNode;
                return;
            }

            Node n = head;
            while( n != null ) {
                if ( n.next == null ) {
                    n.next = newNode;
                    return;
                }

                n = n.next;
            }

            throw new RuntimeException("Should never get here");
        }


        T get(int idx) {
            Node<T> n = head;
            while( (n != null) && (idx-- > 0) ) {
                n = n.next;
            }

            return (n != null) ? n.val : null;
        }

        void reverse() {
            if ( head == null )
                return;

            Node thisNode = head;
            Node newNext = null; // head will become tail
            Node newPrev = thisNode.next; // Will point to thisNode

            while(newPrev != null) {
                // Repoint
                thisNode.next = newNext;
                Node tmp = newPrev.next; // save the current prev
                newPrev.next = thisNode;


                // Slide down
                newNext = thisNode;
                thisNode = newPrev;
                newPrev = tmp;
            }

            head = thisNode;
        }
    }
}
