package com.suj.lang.generics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static com.suj.lang.generics.GenericFactory.newArrayList;
import static com.suj.lang.generics.GenericFactory.newHashMap;
import static org.junit.Assert.*;

/**
 * Created by sujayjayaram on 16/02/2016.
 */
public class GenericFactoryTest {

    @Test
    public void testNewHashMap() throws Exception {
        HashMap<Integer, String> map1 = newHashMap();

        HashMap<Integer, String> map2 = new HashMap<>();

    }

    @Test
    public void testNewArrayList() throws Exception {
        ArrayList<String> list1 = newArrayList();

        ArrayList<String> list2 = new ArrayList<>();
    }
}