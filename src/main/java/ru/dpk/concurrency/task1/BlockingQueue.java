package ru.dpk.concurrency.task1;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue {
    private final Queue<Integer> queue;
    private final int size;

    public BlockingQueue(int size) {
        this.queue = new LinkedList<>();
        this.size = size;
    }

    // Добавление элемента в очередь
    public synchronized void enqueue(int i) throws InterruptedException {
        while (queue.size() == size) {
            wait(); // Ждем, пока очередь не освободится
        }
        queue.offer(i); // Добавляем элемент в очередь
        notifyAll(); // Оповещаем ожидающие потоки
    }

    // Извлечение элемента из очереди
    public synchronized int dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // Ждем, пока очередь не заполнится
        }
        int i = queue.poll(); // Извлекаем элемент из очереди
        notifyAll(); // Оповещаем ожидающие потоки
        return i;
    }

    // Возвращает текущий размер очереди
    public int size() {
        return queue.size();
    }

    public static void main(String[] args) {
        BlockingQueue queue = new BlockingQueue(5);

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        System.out.println("thread producer adds: " + i);
                        queue.enqueue(i);
                    }
                    System.out.println("producer finished.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        int point = queue.dequeue();
                        System.out.println("thread consumer deleted: " + point);
                    }
                    System.out.println("consumer finished.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
