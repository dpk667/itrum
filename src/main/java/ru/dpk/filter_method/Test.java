package ru.dpk.filter_method;

import java.util.Arrays;


// интерфейс Filter, в котором один метод - Object apply(Object o).
interface Filter<T> {
    T apply(T o);
}


public class Test {

    //метод filter, который принимает на вход массив любого типа,
    //вторым арументом метод должен принимать клас, реализующий интерфейс Filter
    public static <T> T[] filter(T[] array, Filter<T> filter) {
        T[] result = Arrays.copyOf(array, array.length);
        for (int i = 0; i < array.length; i++) {
            result[i] = filter.apply(array[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        Integer[] numbers = {1, 2, 3, 4, 5};

        //объект filter, который возводит число в квадрат
        Filter<Integer> squareFilter = new Filter<Integer>() {
            @Override
            public Integer apply(Integer o) {
                return o * o;
            }
        };

        // применяем filter к массиву чисел и сохраняем результаты в новый массив
        Integer[] squaredNumbers = filter(numbers, squareFilter);

        System.out.println(Arrays.toString(squaredNumbers));
    }
}



