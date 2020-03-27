package com.suj.lang.functional.streams1;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by sujayjayaram on 29/01/2016.
 * See http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
 */
public class Demo2 {
    // Monad = pipeline based class

    private void example1(){

        List<String> myList =
                Arrays.asList("a1", "a2", "b1", "c2", "c1");

        myList
                .stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);

// C1
// C2
    /////////////////////////////////////////////////////////////////
        Arrays.asList("a1", "a2", "a3")
                .stream()
                .findFirst()
                .ifPresent(System.out::println);  // a1

        /////////////////////////////////////////////////////////////////
        IntStream.range(1, 4)
                .forEach(System.out::println);

// 1
// 2
// 3
        /////////////////////////////////////////////////////////////////
        Arrays.stream(new int[] {1, 2, 3})
                .map(n -> 2 * n + 1)
                .average()
                .ifPresent(System.out::println);  // 5.0


        /////////////////////////////////////////////////////////////////
        Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);  // 3


        /////////////////////////////////////////////////////////////////

        //Primitive streams can be transformed to object streams via mapToObj():
        IntStream.range(1, 4)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);

// a1
// a2
// a3
        /////////////////////////////////////////////////////////////////

        //Here's a combined example: the stream of doubles is first mapped to an int stream and than mapped to an object stream of strings:
        Stream.of(1.0, 2.0, 3.0)
                .mapToInt(Double::intValue)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);

// a1
// a2
// a3

        /////////////////////////////////////////////////////////////////

        // This prints nothing as there is no terminal operation
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                });

        // This will print:
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));

        /*
        Executing this code snippet results in the desired output on the console:

filter:  d2
forEach: d2
filter:  a2
forEach: a2
filter:  b1
forEach: b1
filter:  b3
forEach: b3
filter:  c
forEach: c
The order of the result might be surprising.
A naive approach would be to execute the operations horizontally one after another
on all elements of the stream. But instead each element moves along the chain vertically.
The first string "d2" passes filter then forEach, only then the second string "a2" is processed.
         */
        /////////////////////////////////////////////////////////////////

        // anyMatch() is a short-circuiting terminal operation
        // in other words it stops when the first element (if any) is found to match
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });
        // map: d2
        // anyMatch: D2
        // map: a2
        // anyMatch: A2


        /////////////////////////////////////////////////////////////////

        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("A");
                })
                .forEach(s -> System.out.println("forEach: " + s));

// map:     d2
// filter:  D2
// map:     a2
// filter:  A2
// forEach: A2
// map:     b1
// filter:  B1
// map:     b3
// filter:  B3
// map:     c
// filter:  C

        // We can greatly reduce the actual number of executions if we change the order of the operations,
        // moving filter to the beginning of the chain:
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));

// filter:  d2
// filter:  a2
// map:     a2
// forEach: A2

        /////////////////////////////////////////////////////////////////

        Stream.of("d2", "a2", "b1", "b3", "c")
                .sorted((s1, s2) -> {
                    System.out.printf("sort: %s; %s\n", s1, s2);
                    return s1.compareTo(s2);
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));

        // *** We get MUCH better performance if we put the filter at the top
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .sorted((s1, s2) -> {
                    System.out.printf("sort: %s; %s\n", s1, s2);
                    return s1.compareTo(s2);
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));

        /////////////////////////////////////////////////////////////////

        // Java 8 streams cannot be reused. As soon as you call any terminal operation the stream is closed:
        Stream<String> stream =
                Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));

        stream.anyMatch(s -> true);    // ok
        stream.noneMatch(s -> true);   // exception

        // BUT THIS WORKS
        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));

        streamSupplier.get().anyMatch(s -> true);   // ok
        streamSupplier.get().noneMatch(s -> true);  // ok



        /////////////////////////////////////////////////////////////////

        /////////////////////////////////////////////////////////////////

        /////////////////////////////////////////////////////////////////
    }
}
