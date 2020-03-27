package com.suj.lang.concurrent.nonblocking;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by sujayjayaram on 24/01/2016.
 */
// Insertion in the Michael Scott Non blocking Queue Algorithm (Michael and Scott, 1996).
    /*
    We need several tricks to develop this plan.
    The first is to ensure that the data structure is always in a consistent state,
    even in the middle of an multi step update. That way, if thread A is in the middle
    of a update when thread B arrives on  the scene, B can tell that an operation has
    been partially completed and knows not to try immediately to apply its own  update.
    Then  B  can  wait  (by  repeatedly  examining  the  queue  state)  until  A  finishes,
    so  that  the  two  don't  get  in  each  other's way.
While  this  trick  by  itself  would  suffice  to  let  threads  "take  turns"  accessing
the  data  structure  without  corrupting  it,  if  one thread failed in the middle of an
update, no thread would be able to access the queue at all. To make the algorithm  non
blocking, we must ensure that the failure of a thread does not prevent other threads
from making progress. Thus,  the  second  trick  is  to  make  sure  that  if  B  arrives
to  find  the  data  structure  in  the  middle  of  an  update  by  A,  enough  information
is  already  embodied  in  the  data  structure  for  B  to  finish  the  update  for  A.
If  B  "helps"  A  by  finishing  A's  operation, B can proceed with its own operation without
waiting for A. When A gets around to finishing its operation, it  will find that B already
did the job for it.
     */
// @ThreadSafe
public class LinkedQueue <E> {
    private static class Node <E> {
        final E item;
        final AtomicReference<Node<E>> next;
        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<Node<E>>(next);
        } }
    private final Node<E> dummy = new Node<E>(null, null);
    private final AtomicReference<Node<E>> head
            = new AtomicReference<Node<E>>(dummy);
    private final AtomicReference<Node<E>> tail
            = new AtomicReference<Node<E>>(dummy);
    public boolean put(E item) {
        Node<E> newNode = new Node<E>(item, null);
        while (true) {
            Node<E> curTail = tail.get();
            Node<E> tailNext = curTail.next.get();
            if (curTail == tail.get()) {
                if (tailNext != null) {
                    // Queue in intermediate state, advance tail
                    tail.compareAndSet(curTail, tailNext);
                } else {
                    // In quiescent state, try inserting new node
                    if (curTail.next.compareAndSet(null, newNode)) {
                        // Insertion succeeded, try advancing tail
                        tail.compareAndSet(curTail, newNode);
                        return true;
                    }
                } }




        } }
}
