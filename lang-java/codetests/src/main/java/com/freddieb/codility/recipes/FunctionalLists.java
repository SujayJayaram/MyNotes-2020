package com.freddieb.codility.recipes;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;

/**
 * Created by sujayjayaram on 06/01/2017.
 */
public class FunctionalLists {

    private static boolean isGreaterThanThree(int i){
        System.out.println("isGreaterThanThree called for " + i);
        return i > 3;
    }

    private boolean isEven(int i){
        System.out.println("isEven called for " + i);
        return i % 2 == 0;
    }

    private static Integer doubleIt(Integer i ) {
        System.out.println("doubleIt called for " + i);
        return i*2;
    }

    public static void main(String[] args) {

        // Note 5 is in here twice!
        Integer[] a = {1, 2, 3, 5, 4, 5, 6, 7, 8, 9};
        List<Integer> values = Arrays.asList(a);

//        System.out.println(
//                values.stream()
//                        .filter(f -> f > 3)
//                        .filter(f -> f % 2 == 0)
//                        .map(i -> 2 * i)
//                        .findFirst()
//        );

        FunctionalLists lt = new FunctionalLists();

        // Look at the output of this code and see how its lazily eavaluated
        //        isGreatherThanThree called for 1
        //        isGreatherThanThree called for 2
        //        isGreatherThanThree called for 3
        //        isGreatherThanThree called for 5
        //        isEven called for 5
        //        isGreatherThanThree called for 4
        //        isEven called for 4
        //        doubleIt called for 4
        //        Optional[8]
        System.out.println(
                values.stream()
                        .filter(FunctionalLists::isGreaterThanThree)
                        .filter(lt::isEven)
                        .map(FunctionalLists::doubleIt)
                        .findFirst()

        );

    }
}
