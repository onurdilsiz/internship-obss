package day4.challenge;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<City> cities = Arrays.asList(
                new City("İstanbul", 15000000),
                new City("Ankara", 5000000),
                new City("İzmir", 3000000),
                new City("Ankara", 4000000) // Aynı şehir birden fazla olabilir
        );

        Map<String, Integer> cityMap = cities.stream()
                .collect(Collectors.groupingBy(City::getCityName, Collectors.summingInt(City::getPopulation)));

        System.out.println(cityMap);
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 17, 19, 23, 24, 25);

        List<Integer> primeNumbers = numbers.stream()
                .filter(Prime::isPrime)
                .collect(Collectors.toList());

        System.out.println("Asal Sayılar: " + primeNumbers);
    }
    }

