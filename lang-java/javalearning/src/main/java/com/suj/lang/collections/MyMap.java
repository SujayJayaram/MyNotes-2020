package com.suj.lang.collections;

import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by sujayjayaram on 17/02/2016.
 * My attempt at implementimg a map!
 */
public class MyMap<K, V> implements Map<K, V> {
    static Logger log = Logger.getLogger(MyMap.class);

    private List<Entry<K,V>>[] buckets;

    public static class MapEntry<K, V> implements Entry<K,V> {

        private final K key;
        private V value;

        MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MapEntry<K, V> mapEntry = (MapEntry<K, V>) o;

            if (key != null ? !key.equals(mapEntry.key) : mapEntry.key != null) return false;
            return value != null ? value.equals(mapEntry.value) : mapEntry.value == null;
        }

        @Override
        public int hashCode() {
            // Just want the value for key
            int result = key != null ? key.hashCode() : 0;
            return result;
        }
    }

    private static final int NUM_BUCKETS = 16;

    // For buckets = new List[initialBuckets];
    @SuppressWarnings("unchecked")
    public MyMap() {
        // See http://stackoverflow.com/questions/529085/how-to-create-a-generic-array-in-java
        // Can't do: buckets = new List<Entry<K,V>>[initialBuckets];
        // See also http://www.angelikalanger.com/GenericsFAQ/JavaGenericsFAQ.html
        // Remember the line below is creating an ARRAY - its just that each element is of
        // type List!
        buckets = new List[NUM_BUCKETS];

        // When we resize we do:
        // buckets = Arrays.copyOf(buckets, buckets.length*2);
    }

    @Override
    public int size() {
        int size = 0;
//        for( int i = 0; i < buckets.length; i++ ){
//            if ( buckets[i] != null ){
//                List<Entry<K,V>> list = buckets[i];
//                for(Entry entry: list)
//                    size++;
//            }
//        }

        for(List<Entry<K,V>> list : buckets) {
            if ( list != null ){
                for(Entry<K, V> entry: list)
                    size++;
            }
        }

        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        if ( key == null )
            return false;

        int bucketNum = key.hashCode() % buckets.length;
        List<Entry<K,V>> list = buckets[bucketNum];
        if ( list != null ) {
            for (Entry<K, V> entry : list) {
                if (entry.hashCode() == key.hashCode()) { // Performance optimisation
                    if (entry.getKey().equals(key)) // Remember no null keys allowed
                        return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for(List<Entry<K,V>> list : buckets) {
            if ( list != null ){
                for(Entry<K, V> entry : list) {
                    V entryValue = entry.getValue();
                    if ( (entryValue != null) && entryValue.equals(value) )
                        return true;

                    if ( (entryValue == null) && (value == null) )
                        return true;

                    // Keep going
                }
            }
        }

        return false;
    }

    @Override
    public V get(Object key) {
        if ( key == null )
            throw new RuntimeException("Null value keys not allowed");

        int bucketNum = key.hashCode() % buckets.length;
        List<Entry<K,V>> list = buckets[bucketNum];
        for(Entry<K, V> entry : list) {
            if ( entry.hashCode() == key.hashCode() ) {
                if ( entry.getKey().equals(key) )
                    return entry.getValue();
            }
        }

        return null;
    }

    @Override
    public V put(K key, V value) {
        if ( key == null )
            throw new RuntimeException("Null value keys not allowed");

        int bucketNum = key.hashCode() % buckets.length;
        List<Entry<K,V>> list = buckets[bucketNum];

        V oldValue = null; // to store old value
        boolean foundOldValue = false;
        if ( list == null ) {
            list = new ArrayList<>();
            buckets[bucketNum] = list;
        }

        for(Entry<K, V> entry : list) {
            K entryKey = entry.getKey();
            if ( entryKey.equals(key) ) {
                oldValue = entry.getValue();
                foundOldValue = true;
                entry.setValue(value);
                break;
            }

            // Keep going
        }

        if ( foundOldValue != true ) {
            Entry<K,V> entry = new MapEntry<>(key, value);
            list.add(entry);
        }

        return oldValue;
    }

    @Override
    public V remove(Object key) {
        if ( key == null )
            throw new RuntimeException("Null value keys not allowed");

        int bucketNum = key.hashCode() % buckets.length;
        List<Entry<K,V>> list = buckets[bucketNum];
        Iterator<Entry<K,V>> iter = list.iterator();
        while(iter.hasNext()){
            Entry<K,V> myEntry = iter.next();
            if ( myEntry.getKey().equals(key) ) {
                iter.remove();
                return myEntry.getValue();
            }
        }

        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        Set<? extends Entry<? extends K, ? extends V>> set = m.entrySet();
        for(Entry<? extends K, ? extends V> entry : set){
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        buckets = new List[NUM_BUCKETS];
    }

    @Override
    public Set<K> keySet() {
        Set<K> rv = new HashSet<>();
        for(List<Entry<K,V>> list : buckets) {
            if ( list != null ){
                for(Entry<K, V> entry : list) {
                    rv.add(entry.getKey());
                }
            }
        }

        return rv;
    }

    @Override
    public Collection<V> values() {
        Collection<V> rv = new ArrayList<>();
        for(List<Entry<K,V>> list : buckets) {
            if ( list != null ){
                for(Entry<K, V> entry : list) {
                    rv.add(entry.getValue());
                }
            }
        }

        return rv;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> rv = new HashSet<>();
        for(List<Entry<K,V>> list : buckets) {
            if ( list != null ){
                for(Entry<K, V> entry : list) {
                    rv.add(entry);
                }
            }
        }

        return rv;
    }
}
