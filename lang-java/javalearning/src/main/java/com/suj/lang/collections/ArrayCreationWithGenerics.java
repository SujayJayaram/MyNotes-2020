package com.suj.lang.collections;

import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by sujayjayaram on 17/02/2016.
 * My attempt at implementimg a map!
 */
public class ArrayCreationWithGenerics<K, V> implements Map<K, V> {
    static Logger log = Logger.getLogger(ArrayCreationWithGenerics.class);

    private static final int INITIAL_NUM_BUCKETS = 16;
    private static final float INITIAL_LOAD_FACTOR = 0.75f;

    private final int initialCapacity;
    private final float loadFactor;

    private List<Entry<K,V>>[] buckets;

    String[] buckets2;
    List<Integer>[] buckets3;

    class MapEntry<K, V> implements Entry<K,V> {

        private final K key;
        private final V value;

        MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return null;
        }

        @Override
        public V getValue() {
            return null;
        }

        @Override
        public V setValue(V value) {
            return null;
        }
    }

    public ArrayCreationWithGenerics(int initialCapacity, float loadFactor) {
        this.initialCapacity = initialCapacity;
        this.loadFactor = loadFactor;

        buckets2 = new String[initialCapacity];
        buckets3 = new List[10];

        // See http://stackoverflow.com/questions/529085/how-to-create-a-generic-array-in-java
        // Can't do: buckets = new List<Entry<K,V>>[initialCapacity];
        buckets = new List[initialCapacity];

        // buckets = (List<Entry<K,V>>)  Array.newInstance(List.class, initialCapacity);



       // Arrays.copyOf(buckets, buckets.size()*2);
    }

    public ArrayCreationWithGenerics(){
        this(INITIAL_NUM_BUCKETS, INITIAL_LOAD_FACTOR);
    }


    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
