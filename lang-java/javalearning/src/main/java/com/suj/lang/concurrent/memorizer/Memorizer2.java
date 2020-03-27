package com.suj.lang.concurrent.memorizer;

import java.util.concurrent.*;

/**
 * Created by sujayjayaram on 22/01/2016.
 * This cache example shows how to atomically ensure only one
 * thread does the work BUT without any synchronisation!
 */
public class Memorizer2<A, V> implements Computable<A, V> {
    private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A, V> c;
    public Memorizer2(Computable<A, V> c) { this.c = c; }
    public V compute(final A arg) throws InterruptedException {
        while (true) {
            Future<V> f = cache.get(arg);
            if (f == null) {
                // Lambda example below is MUCH more elegant.
                //
                // Callable<V> eval = new Callable<V>() {
                //    public V call() throws InterruptedException {
                //        return c.compute(arg);
                //    }
                // };
                Callable<V> eval = () -> c.compute(arg);

                FutureTask<V> ft = new FutureTask<V>(eval);
                f = cache.putIfAbsent(arg, ft);
                if (f == null) {
                    f = ft;
                    ft.run();
                }
            }

            try {
                return f.get();
            } catch (CancellationException e) {
                cache.remove(arg, f);
            } catch (ExecutionException e) {
                throw launderThrowable(e.getCause());
            }
        } }

    /** If the Throwable is an Error, throw it; if it is a
     *  RuntimeException return it, otherwise throw IllegalStateException
     */
    public static RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException)
            return (RuntimeException) t;
        else if (t instanceof Error)
            throw (Error) t;
        else
            throw new IllegalStateException("Not unchecked", t);
    }
}