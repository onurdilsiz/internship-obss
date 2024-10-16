package day4.function.labStreams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("John", 25), new Person("Jane", 30), new Person("Jake", 28), new Person("David", 32), new Person("Julia", 27), new Person("Mary", 23));


        List<Person> filteredAndSortedPeople = people.stream()
                .filter(person -> person.getName().startsWith("J"))
                .sorted(Comparator.comparingInt(Person::getAge).reversed())
                .collect(Collectors.toList());

        System.out.println(filteredAndSortedPeople);

    }

}
