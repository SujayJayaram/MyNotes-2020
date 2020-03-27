package com.suj.lang.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by sujayjayaram on 27/01/2016.
 */
public class ComparatorDemo {

    public static void main(String[] args){
        System.out.println("Started test");

        List<UnsortableItem> list = Arrays.asList(new UnsortableItem(1, "ZZZ"),
                new UnsortableItem(3, "XXX"),
                new UnsortableItem(2, "YYY"),
                new UnsortableItem(4, "WWW"));

        System.out.println("Unsorted list: " + list);

        // UnsortableItem does not implement Comparator<UnsortableItem> so
        // we have to use this signature to the sort method.
        Collections.sort(list, new Comparator<UnsortableItem>() {
            @Override
            public int compare(UnsortableItem o1, UnsortableItem o2) {
                return 0;
            }
        });

        // Collections.sort(list, (o1, o2) -> o1.hashCode() - o2.hashCode()); // Just shown for illustration, there is an implicit return sgtatement here.

        System.out.println("Sorted list: " + list);

        SortableItem[] array = {new SortableItem(1, "ZZZ"),
                new SortableItem(3, "XXX"),
                new SortableItem(2, "YYY"),
                new SortableItem(4, "WWW")};

        System.out.println("Unsorted array: " + array);
        List<SortableItem> sortableItems = Arrays.asList(array);
        System.out.println("Unsorted sortableItems: " + sortableItems);
        Collections.sort(sortableItems);
        System.out.println("Sorted via list: " + sortableItems);

    }


}
