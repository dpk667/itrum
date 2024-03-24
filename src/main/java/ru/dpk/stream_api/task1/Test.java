package ru.dpk.stream_api.task1;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


        //Выведите результат: список трех самых дорогих продуктов и их общая стоимость.

        ///////////////////////////////////////////////////

        List<Order> sortedOrders = orders.stream()
                .sorted(Comparator.comparing(Order::getCost).reversed())
                .collect(Collectors.toList());

        List<Order> topThreeOrders = sortedOrders.stream()
                .limit(3)
                .collect(Collectors.toList());

        //  общая стоимость трех самых дорогих продуктов
        double totalCost = topThreeOrders.stream()
                .mapToDouble(Order::getCost)
                .sum();

        System.out.println("\n" + "Общая стоимость трех самых дорогих продуктов: " + totalCost);

    }
}