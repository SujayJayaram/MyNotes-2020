package com.suj.lang.collections;

import org.junit.Test;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;


/**
 * Created by sujayjayaram on 18/02/2016.
 */
public class MyMapTest {

    Map<String, Integer> getTestMap1() {
        Map<String, Integer> rv = new MyMap<>();
        IntStream.range(100, 200).forEach(i -> rv.put(Integer.toString(i), new Integer(i)) );
        return rv;
    }

    @Test
    public void testSize() throws Exception {
        Map<String, Integer> map = getTestMap1();
        assertThat(map.size(), equalTo(100));
    }

    @Test
    public void testIsEmpty() throws Exception {
        Map<String, Integer> map = getTestMap1();
        assertThat(map.isEmpty(), equalTo(false));
        map.clear();
        assertThat(map.isEmpty(), equalTo(true));
    }

    @Test
    public void testContainsKey() throws Exception {
        Map<String, Integer> map = getTestMap1();
        assertThat(map.containsKey("100"), equalTo(true));
        assertThat(map.containsKey("99"), equalTo(false));
    }

    @Test
    public void testContainsValue() throws Exception {
        Map<String, Integer> map = getTestMap1();
        assertThat(map.containsValue(100), equalTo(true));
        assertThat(map.containsValue(99), equalTo(false));
    }

    @Test
    public void testGet() throws Exception {
        Map<String, Integer> map = getTestMap1();
        assertThat(map.get("100"), equalTo(100));
    }

    @Test
    public void testRemove() throws Exception {
        Map<String, Integer> map = getTestMap1();
        map.remove("100");
        assertThat(map.size(), equalTo(99));
    }

    @Test
    public void testPutAll() throws Exception {
        Map<String, Integer> map = getTestMap1();
        Map<String, Integer> map2 = getTestMap1();

        map.remove("100");
        assertThat(map.size(), equalTo(99));
        assertThat(map.get("100"), equalTo(null));

        map.putAll(map2);
        assertThat(map.size(), equalTo(100));
        assertThat(map.get("100"), equalTo(100));
    }

    @Test
    public void testClear() throws Exception {
        Map<String, Integer> map = getTestMap1();
        assertThat(map.size(), equalTo(100));

        map.clear();
        assertThat(map.size(), equalTo(0));
    }

    @Test
    public void testKeySet() throws Exception {
        Map<String, Integer> map = getTestMap1();
        Set<String> set = map.keySet();
        assertThat(set.size(), equalTo(100));

        IntStream.range(100, 200).forEach(i -> {
            assertThat(set.contains(Integer.toString(i)), equalTo(true));
        });
    }

    @Test
    public void testValues() throws Exception {

        Map<String, Integer> map = getTestMap1();
        Collection<Integer> values = map.values();
        assertThat(values.size(), equalTo(100));
        IntStream.range(100, 200).forEach(i -> {
            assertThat(values.contains(new Integer(i)), equalTo(true));
        });
    }

    @Test
    public void testEntrySet() throws Exception {
        Map<String, Integer> map = getTestMap1();
        Set<Map.Entry<String, Integer>> set = map.entrySet();
        assertThat(set.size(), equalTo(100));

        IntStream.range(100, 200).forEach(i -> {
            Map.Entry e = new MyMap.MapEntry(Integer.toString(i), new Integer(i));
            assertThat(set.contains(e), equalTo(true));
        });
    }
}