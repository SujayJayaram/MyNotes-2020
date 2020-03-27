package com.suj.lang.functional.countrycontinent;

import java.util.List;

/**
 * Created by sujayjayaram on 06/03/2016.
 */
public class Country {
    private final String name;
    private final int population;
    private final String continent;

    public Country(String name, int population, String continent) {
        this.name = name;
        this.population = population;
        this.continent = continent;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public String getContinent() {
        return continent;
    }

    public static void main(String... args) {
        List<Country> countries = null;
        String continent = "xxx";

        int sum = countries.stream()
                .filter(country -> country.getContinent().equals(continent))
                .mapToInt(country -> country.getPopulation())
                .sum();

        int altSum = countries.stream()
                .filter(country -> country.getContinent().equals(continent))
                .map(country -> country.getPopulation())
                .reduce(0, (a,b) -> a+b); // 0 = initial or default val, a = sum so far, b = next element
    }
}
