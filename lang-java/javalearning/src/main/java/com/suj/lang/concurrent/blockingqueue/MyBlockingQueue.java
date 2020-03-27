package com.suj.lang.concurrent.blockingqueue;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by sujayjayaram on 22/01/2016.
 */
public class MyBlockingQueue<E> implements BlockingQueue<E> {

    // @GuardedBy("this")
    private final List<E> queue = new ArrayList<E>();
    private final int maxSize;

    public MyBlockingQueue(int maxSize){
        this.maxSize = maxSize;
    }

    // Blocks if necessary
    @Override
    public E take() throws InterruptedException {
        synchronized (queue) {
            do {
                if ( queue.size() == 0 )
                    wait(); // Wait for something to be added
                else {
                    E rv = queue.remove(0);

                    if ( queue.size() == (maxSize-1) )
                        queue.notifyAll(); // Tell other waiting threads that there is space created.

                    return rv;
                }
            } while(true);
        }
    }

    // Blocks if necessary
    @Override
    public void put(E e) throws InterruptedException {
        synchronized (queue) {
            do {
                if ( queue.size() > maxSize )
                    wait(); // Wait for some space to appear
                else {
                    queue.add(e);

                    if ( queue.size() == 1 )
                        queue.notifyAll(); // Tell other waiting threads that there is a new item

                    return;
                }
            } while(true);
        }
    }

    @Override
    public boolean offer(E e) {
        synchronized (queue) {
            if ( queue.size() > maxSize )
                return false;
            else {
                queue.add(e);

                if ( queue.size() == 1 )
                    queue.notifyAll(); // Tell other waiting threads that there is a new item

                return true;
            }
        }
    }

    // Preferabe to call offer()
    @Override
    public boolean add(E e) {
        synchronized (queue) {
            if ( queue.size() > maxSize )
                throw new IllegalStateException("No space available");
            else {
                queue.add(e);

                if ( queue.size() == 1 )
                    queue.notifyAll(); // Tell other waiting threads that there is a new item

                return true;
            }
        }
    }

    @Override
    public E remove() {
        synchronized (queue) {
            if ( queue.size() > 0 ) {
                E rv = queue.remove(0);

                if ( queue.size() == (maxSize - 1) )
                    queue.notifyAll(); // Tell other waiting threads that there is space created.

                return rv;
            }
            else
                return null;
        }

    }

    ///////////////////////////

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    ///////////////////////////////////////

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        return false;
    }

    @Override
    public Spliterator<E> spliterator() {
        return null;
    }

    @Override
    public Stream<E> stream() {
        return null;
    }

    @Override
    public Stream<E> parallelStream() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        return null;
    }

    @Override
    public int remainingCapacity() {
        return 0;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public int drainTo(Collection<? super E> c) {
        return 0;
    }

    @Override
    public int drainTo(Collection<? super E> c, int maxElements) {
        return 0;
    }
}
