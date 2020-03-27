package com.suj.lang.functional.rxjava;

import rx.Observable;
import rx.Subscriber;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sujayjayaram on 29/01/2016.
 * See https://github.com/ReactiveX/RxJava/wiki/How-To-Use-RxJava
 * look at https://github.com/ReactiveX/RxJava/wiki/Combining-Observables
 */
public class Demo1 {
    public static void main(String[] args) {
        Demo1 d = new Demo1();
        d.test();
    }

    public void test() {
        List<Integer> list = Arrays.asList(5, 6, 7, 8);

        for(Integer i : list) {
            System.out.println(i + " xxx");
        }

        list.forEach(i -> System.out.println(i + " yyy") );

        Observable<Integer> o1 = Observable.from(list);
        Observable<String> o2 = Observable.just("one object");
        Observable<Long> o3 = Observable.create(t -> {
                                            System.out.println("TEST");
                                            OnSubscribe(t);
        });

        // subscribe takes 3 functional args:
        // OnNext
        // OnError
        // OnComplete
        o3.subscribe(ev -> {
                    try {
                        System.out.println("RxJava Received: " + ev.toString());
                    } catch (NumberFormatException e) {
                    }
                },
                throwable -> System.out.println("Error: " + throwable.getMessage()),
                () -> {
                    System.out.println("RxJava All done");
                });
    }

    private void OnSubscribe(Subscriber<? super Long> subscriber) {
        subscriber.onNext(100L);
        subscriber.onNext(200L);
        subscriber.onCompleted();
    }

}
