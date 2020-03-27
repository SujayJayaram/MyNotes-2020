package com.freddieb.codility.recipes;

import java.util.function.IntFunction;

/**
 * Created by sujayjayaram on 21/01/2017.
 */
public class IntFunctionTests {
    public static void main(String[] args) {
        {
            // By defining this interface with a myApply method
            // it can be assigned via a lambda as below (no class
            // required to implement this).
            MyIntMethod sujInt = z -> z + 3;
            System.out.println("TTT " + sujInt.myApply(6)); // TTT 9
        }

        {
            // IntFunction<R> defines an object that has an apply() method that takes
            // an int and returns an <R> instance (in this case it returns a MyIntMethod
            // which is the 'y->x+y' part of the 'x -> y -> x + y' definition).

            // We close over x here. Sum is a fn that returns a fn.
            IntFunction<MyIntMethod> fnToFn = x -> y -> x + y;
            MyIntMethod sum10 = fnToFn.apply(10); // apply() is a method on IntFunction
            sum10.myApply(4); //yields 14
        }


        {
            IntFunction<MyIntMethod> fnToFn = x -> y -> 20;
            MyIntMethod sum10 = fnToFn.apply(10);
            System.out.println("AAA " + sum10.myApply(4)); // AAA 20
        }
    }

    // By defining this interface with a single method
    // it can be assigned to a lambda. No @Functional
    // annotation required.
    interface MyIntMethod {
        int myApply(int val);
    }
}
