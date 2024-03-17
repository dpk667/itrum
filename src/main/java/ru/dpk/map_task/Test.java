package ru.dpk.map_task;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static <T> Map<T, Integer> countOfElements(T[] array) {
        Map<T, Integer> occurrences = new HashMap<>();


        // проходим по каждому элементу массива
        // получаем текущее количество вхождений элемента в Map
        // увеличиваем количество вхождений на 1 и добавляем обновленное значение в map
        for (int i = 0; i < array.length; i++) {
            T element = array[i];
            Integer count = occurrences.getOrDefault(element, 0);
            occurrences.put(element, count + 1);
        }

        return occurrences;
    }

    public static void main(String[] args) {
        Integer[] numbers = {3, 4, 5, 1, 2, 4, 5, 3, 2, 1, 1, 2, 3, 2, 1, 2, 3, 4, 4};

        Map<Integer, Integer> occurrences = countOfElements(numbers);
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
