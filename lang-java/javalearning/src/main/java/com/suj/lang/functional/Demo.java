package com.suj.lang.functional;

import org.apache.commons.lang.math.IntRange;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by sujayjayaram on 02/02/2016.
 */
public class Demo {
    // http://blog.jooq.org/2014/06/13/java-8-friday-10-subtle-mistakes-when-using-the-streams-api/
    // http://zeroturnaround.com/rebellabs/java-parallel-streams-are-bad-for-your-health/
    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");

        // Shortcut
        list.forEach(System.out::println);

        IntStream.range(0, list.size())
                .forEach(nbr -> System.out.println("AAA " + list.get(nbr)));

        // Not necessarily in order!!!!
        // Parallel streams are processed by the parent thread that ordered
        // the operation and additionally by the threads in the default JVM’s
        // fork join pool: ForkJoinPool.common().
        // See http://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ForkJoinPool.ManagedBlocker.html
        IntStream.range(0, list.size()).parallel().forEach(
                nbr -> System.out.println("BBB " + list.get(nbr))
        );
    }
}
