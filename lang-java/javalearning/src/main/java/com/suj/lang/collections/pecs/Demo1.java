package com.suj.lang.collections.pecs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sujayjayaram on 29/01/2016.
 * Producer Extends Consumer Super
 */
public class Demo1 {
    // Remember in the case of
    // Integer EXTENDS Number
    // or
    // ? EXTENDS Number
    // we focus on the second param (e.g. Number) as this is the type
    // we will use in our producer or consumer code.

    // PRODUCER EXTENDS
    // Two ways of declaring
    public <E extends Number> List<E> getNumberList(List<E> list) {
        // Producer extends...
        E head = list.get(0);
        Number head2 = list.get(0);

        // These are both 'produced' number types.
        head.byteValue();
        head2.byteValue();

        return list;
    }

    // Can never do anything useful if we define this 'inline' as we can't
    // supply another param to the method.
    public List<? extends Number> getNumberListAlt() {
        return null;
    }

    // This will compile - it won't using bounded generics as
    // they assume we are not creating lists.
    public List<Integer> getNumberListSafe() {
        List<Integer> list = new ArrayList<>();
        return list;
    }

    // CONSUMER SUPER
    // NOTE THE FOLLOWING WILL NOT COMPILE
    // public void accept(List<E super Number> list) // need to use '?'
    // public <E super Number> void accept(List<E> list) // need to use '?'
    // // public <E super Number> void accept(List<E> list, E e2) // it needs to consume a known type
    public void accept(List<? super Number> list) {
        Number n = new Integer(5);
        list.add(n);
    }

    public void test(){
        List<Object> objList = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();

        // getNumberList(objList); - won't compile
        getNumberList(intList);

        accept(objList);
        // accept(intList); - won't compile
    }
}
