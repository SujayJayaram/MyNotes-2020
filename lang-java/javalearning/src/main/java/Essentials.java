import javax.xml.stream.events.Characters;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Essentials {

    public static void main(String[] args) {

        // Good problems to practice:
        // Reverse a string
        // **** Reverse a linked list
        // Compare application version numbers "13.2" v "12.9"


        // Arrays.sort();
        // Arrays.stream();
        // Arrays.copyOf()
        //
        // Collections.sort();
        //
        // Character.toString(digit)
        //
        // System.arraycopy();

        {
            Integer[] arr = {1, 3, 2, 4, 2, 3, 4, 2, 4};
            Arrays.sort(arr);

            List<Integer> list1 = Arrays.asList(arr);
            Collections.sort(list1);

            List<Integer> list2 = Arrays.stream(arr)
                                                .collect(Collectors.toList());
        }

        {
            int[] arr = {1, 3, 2, 4, 2, 3, 4, 2, 4};
            List<Integer> collect =  Arrays.stream(arr)
                                                    .boxed()
                                                    .collect(Collectors.toList());

            Integer[] arr2 = {1, 3, 2, 4, 2, 3, 4, 2, 4};
            List<Integer> collect2 =  Arrays.stream(arr2)
                    //.boxed()
                    .collect(Collectors.toList());
        }


        {
            String s = "fred";
            char[] chars = s.toCharArray();
        }

        // Functional stuff
        IntStream.range(0, 100).forEach(i -> {
            System.out.println(i);
        });


        List<Integer> list1 = new ArrayList<>();
        list1.stream().forEach( s -> System.out.println(s));

        List<String> list2 = list1.stream()
                                    .map(Object::toString)
                                    .collect(Collectors.toList());

        List<Integer> list3 = list2.stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        Map<Integer, Long> mapOfCounts = list1.stream().collect(
                Collectors.groupingBy(
                        Function.identity(), Collectors.counting()
                )
        );

    }
}
