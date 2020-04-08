package com.top30javainterviewquestions.reversealinkedlist;

public class LinkedList<T> {

    Node<T> head;

    public LinkedList() {
        head = null;
    }

    public void add(T t) {
        Node<T> newNode = new Node<>(t);
        if ( head == null ) {
            head = newNode;
            return;
        }

        Node node = head;
        while(node.getNext() != null)
            node = node.getNext();


        node.setNext(newNode);
    }

    public T get(int index){
        if ( head == null )
            return null;

        int count = 0;
        Node<T> node = head;
        while(count != index) {
            if ( node.getNext() == null )
                return null;

            count++;
            node = node.getNext();
        }

        if ( node == null )
            return null;
        else
            return node.getValue();
    }

    public void reverse() {
        if ( head == null )
            return;

        // Go from
        // head -> a -> b -> null
        // to
        // null <- head <- a <- b (new head)

        Node<T> newNext = null;
        Node<T> currNode = head; // start from head...

        do {
            // 1. get the 'next' from currNode before we trash it
            Node<T> newPrev = currNode.getNext();

            // 2. Set the new next
            currNode.setNext(newNext);

            // 3. Line up for next time.....
            head = currNode;
            newNext = currNode;
            currNode = newPrev;
        }
        while(currNode != null);
    }
}
