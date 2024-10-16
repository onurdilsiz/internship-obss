package day4.function.labStudents;

import day4.function.labStreams.Person;

import javax.swing.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Student> people = Arrays.asList(
                new Student("John","CMPE", 25), new Student("Jane","CMPE",  12),
                new Student("Student","CMPE",  28), new Student("David" ,"EE",32),
                new Student("Julia","EE", 27), new Student("Mary","IE", 23));

        List<Student> studentsE = people.stream().filter(s -> s.getName().contains("e")).toList();
        System.out.println(studentsE);
        Map<String, Long> students = people.stream().collect(Collectors.groupingBy(Student::getDepartment,Collectors.counting()));
        System.out.println(students);
        Map<String, Double> studentsaverage = people.stream().collect(Collectors.groupingBy(Student::getDepartment,Collectors.averagingDouble(Student::getGrade)));
        System.out.println(studentsaverage);

        people.stream()
                .collect(Collectors.groupingBy(Student::getDepartment, Collectors.averagingDouble(Student::getGrade)))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry-> System.out.println(entry.getKey()+ "= "+ entry.getValue()));


        List<Student> studentsTop2 = people.stream().sorted(Comparator.comparingDouble(Student::getGrade).reversed()).limit(2).collect(Collectors.toList());
        System.out.println(studentsTop2);

        Map<String, Double> report6 = people.stream()
                .map(student -> {
                    if (student.getGrade() < 50) {
                        return new Student(student.getName(), student.getDepartment(), student.getGrade() + 10);
                    } else {
                        return student;
                    }
                })
                .collect(Collectors.groupingBy(Student::getDepartment, Collectors.averagingDouble(Student::getGrade)));
        System.out.println("\nRapor 6: Notu 50'den küçük olan öğrencilere 10 puan daha ekleseydik departman bazlı ortalama öğrenci puanları nasıl olurdu");
        report6.forEach((department, avg) -> System.out.println(department + ": " + avg));
    }
}
