import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Essentials {

    public static void main(String[] args) {

        Integer[] arr = {1,3,2,4, 2, 3, 4, 2, 4};

        List<Integer> list1 = Arrays.asList(arr);

        Collections.sort(list1);

        Arrays.sort(arr);

        // Functional stuff
        IntStream.range(0, 100).forEach(i -> {
            System.out.println(i);
        });


        list1.stream().forEach( s -> System.out.println(s));

        List<String> list2 = list1.stream()
                                    .map(Object::toString)
                                    .collect(Collectors.toList());

        list2.stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        Map<Integer, Long> mapOfCounts = list1.stream().collect(
                Collectors.groupingBy(
                        Function.identity(), Collectors.counting()
                )
        );

    }
}
