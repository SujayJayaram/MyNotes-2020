package com.suj.lang.concurrent.completablefuture;

import rx.Observable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sujayjayaram on 29/01/2016.
 * See https://blog.logentries.com/2015/07/playing-with-java-8s-completable-futures/
 */
public class CountEventsDemo {
    public static CompletableFuture countEvents() {
        CompletableFuture result = new CompletableFuture<>();
        AtomicInteger count = new AtomicInteger();
        Observable.just("1", "2", "3", "err", "4").subscribe(ev -> {
                    try {
                        int x = Integer.parseInt(ev);
                        count.set(count.get() + x);
                    } catch (NumberFormatException e) {
                    }
                },
                throwable -> result.complete(0),
                () -> {
                    try {
                        //simulate io delay
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                    }
                    result.complete(count.get());
                }
        );
        return result;
    }

    public static void main(String[] args) throws Exception {

        CompletableFuture data = countEvents()
                .thenApply(count -> {
                    return count + "25";
                }).thenApply(transformed -> "data-" + transformed);

        try  {
            System.out.println(data.get()); // prints "data-1025"

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
