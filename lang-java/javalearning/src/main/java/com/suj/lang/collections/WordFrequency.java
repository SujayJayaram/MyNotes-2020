package com.suj.lang.collections;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordFrequency {
    public static void main(String[] args) {
        WordFrequency obj = new WordFrequency();

        final Map<String, Double> map = obj.wordFrequency(Arrays.asList(
                "Hotels in Oakland",
                "Resorts near Sanfrancisco",
                "Restorants near Bay area",
                "Software Jobs in Oakland",
                "Hotels in Oakland Airport",
                "Resorts near Abudabi Airport",
                "Restorants near Australia",
                "Software Jobs in USA"));
        System.out.println("JAVA 8 : WordFrequency Example");
        System.out.println("===============================");
        map.entrySet().stream().forEach(obj.printit);
    }

    /**
     * print a Map.Entry
     */
    private static Consumer<Map.Entry<String, Double>> printit = w -> System.out
            .printf("word: %s score:%.2f%n", w.getKey(), w.getValue());

    /**
     * Build a Map of all words found in the list of strings along with their
     * relative frequency in the list.
     *
     * @param strings
     * @return
     */
    public Map<String, Double> wordFrequency(List<String> strings) {
        Stream<String> streams = strings.stream();

        // map each word to it total number of occurrences
        Map<String, Long> wordCount = streams.map(w -> w.split(" "))
                // return Stream<String[]>
                .flatMap(Arrays::stream)
                // flatten to Stream<String>
                .map(trimit)
                // strip non-alphanumerics and lowercase all
                .filter(isalpha)
                .collect(Collectors.groupingBy(Function.identity(),
                                Collectors.counting())); // map word strings to
        // count

        // total number of words in list
        Long wordTotal = wordCount.values().stream()
                .reduce(0L, (a, b) -> a + b);

        // convert total occurrences to a percentage of total words
        Map<String, Double> wordFreq = wordCount
                .entrySet()
                .stream()
                .collect(
                        Collectors.toMap(e -> e.getKey(),
                                e -> (100 * (e.getValue().doubleValue()))
                                        / wordTotal));

        List<Map.Entry<String, Double>> sorted = wordFreq.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());

        Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
        sorted.forEach(e -> sortedMap.put(e.getKey(), e.getValue()));
        return sortedMap;
    }

    private Function<String, String> trimit = s -> s.replaceAll("[^A-Za-z0-9]",
            "").toLowerCase();

    private Predicate<String> isalpha = s -> s.matches("[a-zA-Z]+")
            && s.length() > 2;
}