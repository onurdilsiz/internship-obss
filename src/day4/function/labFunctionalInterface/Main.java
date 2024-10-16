package day4.function.labFunctionalInterface;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static <String> List<String> filterList(List<String> list, ListFilter<String> filter) {
        List<String> filteredList = new ArrayList<>();
        for (String object : list) {
            if (filter.satisfiesCondition(object)) {
                filteredList.add(object);
            }
        }
        return filteredList;
    }
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("apple");
        strings.add("banana");
        strings.add("orange");
        strings.add("grape");
        strings.add("kiwi");

        // Filter strings shorter than 5 characters
        ListFilter<String> lengthFilter = s -> s.length() < 5;
        List<String> shortStrings = filterList(strings, lengthFilter);
        System.out.println(shortStrings);

        ListFilter<String> startFilter = s -> s.startsWith("a");
        List<String> aStrings = filterList(strings, startFilter);
        System.out.println(aStrings);

        ListFilter<String> includespp = s -> s.contains("pp");
        List<String> ppStrings = filterList(strings, includespp);
        System.out.println(ppStrings);

    }
}
