package setone;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompareVersionNumbers {

    // 51.2.3 > 51.2 > 14.9.3.4.5 etc
    static int compare(String version1, String version2){
        if ( isBlank(version1) && isBlank(version2) )
            return 0;

        if ( isBlank(version2) )
            return 1;

        if ( isBlank(version1) )
            return -1;

        /*
        String[] sArray = version1.split("\\.");
        Stream<String> stream = Arrays.stream(sArray);
        Stream<Integer> integerStream = stream.map(Integer::parseInt);
        List<Integer> collect = integerStream.collect(Collectors.toList());
        Integer[] objects = integerStream.toArray(Integer[]::new);
        */

        Integer[] arr1 = Arrays.stream(version1.split("\\.")).map(Integer::parseInt).toArray(Integer[]::new);
        Integer[] arr2 = Arrays.stream(version2.split("\\.")).map(Integer::parseInt).toArray(Integer[]::new);

        int len1 = arr1.length;
        int len2 = arr2.length;

        int maxLoops = (len1 < len2)? len1 : len2;

        for(int i = 0; i < maxLoops; i++) {
            if ( arr1[i] > arr2[i] )
                return 1;
            else if ( arr2[i] > arr1[i] )
                return -1;

        }

        if ( len1 == len2 )
            return 0;

        return (len1 < len2)? -1 : 1;
    }

    private static boolean isBlank(String s) {
        return ( (s == null) || s.trim().equals("") );
    }
}
