package ru.dpk.stream_api.task2;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Student {
    private String name;
    private Map<String, Integer> grades;

    public Student(String name, Map<String, Integer> grades) {
        this.name = name;
        this.grades = grades;
    }

    public Map<String, Integer> getGrades() {
        return grades;
    }
}

//Используйте Parallel Stream для обработки данных и создания Map, где ключ - предмет, а значение - средняя оценка по всем студентам.
public class ParallelStreamCollectMapAdvancedExample {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Student1", Map.of("Math", 90, "Physics", 85)),
                new Student("Student2", Map.of("Math", 95, "Physics", 88)),
                new Student("Student3", Map.of("Math", 88, "Chemistry", 92)),
                new Student("Student4", Map.of("Physics", 78, "Chemistry", 85))
        );

        Stream<Map.Entry<String, Double>> avarageGrade = students.stream()// создаю поток
                .flatMap(student -> student.getGrades().entrySet().stream()) // получаю оценки студентов
                .collect(Collectors.groupingBy(Map.Entry::getKey, // группируем по ключу (предметам)
                        Collectors.averagingInt(Map.Entry::getValue))).entrySet().stream(); // вычисляем среднее значение

        avarageGrade.forEach(entry -> System.out.println(entry.getKey() + ": " +entry.getValue()));



    }
}