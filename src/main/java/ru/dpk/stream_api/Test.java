package ru.dpk.stream_api;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {

        // создаем список заказов
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );


        // Группируйте заказы по продуктам. +
        // группируем заказы по продукту
        Map<String, List<Order>> orderByProduct = orders.stream().collect(Collectors.groupingBy(Order::getProduct));

        // вывод результата группировки
        for (Map.Entry<String, List<Order>> item : orderByProduct.entrySet()) {
            System.out.println(item.getKey()); // выводим наименование продукта
            for (Order order : item.getValue()) {
                System.out.println(order.getProduct()); // выводим инфу о каждом заказе этого продукта
            }
            System.out.println();
        }

        ////////////////////////////////////////////////////////////////////////////////////

        // Для каждого продукта найдите общую стоимость всех заказов.  +
        Map<String, Double> costByProduct = orders.stream() // создаем поток из заказов
                .collect(Collectors.groupingBy(Order::getProduct, // группируем по названию
                        Collectors.summingDouble(Order::getCost))); // суммируем стоимость по группе товаров

        System.out.println("общая стоимость заказов по группам");
        costByProduct.forEach((product, totalCost) -> System.out.println(product + ": " + totalCost));

        /////////////////////////////////////////////////////////////////////////////////////////////////

        System.out.println("\n");
        // Отсортируйте продукты по убыванию общей стоимости. +
        // создается поток
        // сортируем по ценам, выводим

        List<Order> sortProducts = orders.stream()
                .sorted(Comparator.comparing(Order::getCost).reversed())
                .collect(Collectors.toList());

        for (Order order : sortProducts) {
            System.out.println(order);
        }

        System.out.println("\n" + "Выберите три самых дорогих продукта");
        //////////////////////////
        //Выберите три самых дорогих продукта.
        sortProducts
                .stream()
                .limit(3)
                .forEach(System.out::println);


    }
}