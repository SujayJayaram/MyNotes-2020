package com.suj.lang.generics;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sujayjayaram on 12/01/2016.
 * Import this class statically in order to use type inference so that the generics
 * parameter types need not be specified on the rhs. Actually we would use the diamond
 * operator nowadays.
 */
public class GenericFactory {

    public static <K,V> HashMap<K,V> newHashMap(K k, V v) {
        return new HashMap<K, V>();
    }

    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<E>();
    }


    public static void main(String[] args) {

        HashMap<Integer, String> map = GenericFactory.newHashMap(0, "Fred");


    }
}
