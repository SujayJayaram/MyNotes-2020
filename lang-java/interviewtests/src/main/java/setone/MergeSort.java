package setone;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MergeSort {

    public static void mergeSort(int[] arr, int n) {
        // Terminal condition
        if ( arr.length <= 1 )
            return;

        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        //merge(arr, 0, mid);
        //merge(arr, mid+1, arr.length);


        String[] s = new String[]{"fred", "dave"};

        List<String> strings = Arrays.asList(s);

        //strings.set
    }
}
