package ru.dpk.concurrency.task1;

import java.util.LinkedList;
import java.util.Queue;

// use wait, notify
public class BlockingQueue {
    private final Queue<Integer> queue;
    private final int size;

    public BlockingQueue(int size) {
        this.queue = new LinkedList<>();
        this.size = size;
    }

    // add el to queue
    public synchronized void enqueue(int i ) throws InterruptedException {

        while (queue.size() == size) {
            wait(); // пока очередь не освободится - ждем
        }
        queue.offer(i); // добавляем элемент, убедившись, что не получим исключение
        notifyAll(); // оповещаем все потоки, что добавился ээлемент
    }

    // for get el from queue
    // if queue is empty, method will block thread before new element was not created
    public synchronized int dequeue() throws InterruptedException {
        // если queue пустая, ждем новых элементов
        while (queue.isEmpty()) {
            wait();
        }

        int i = queue.peek(); // извлекаем наш элемент, предупреждаем об этом все наши потоки
        notifyAll();
        return i;
    }


    public int size() {
        return queue.size();
    }

    public static void main(String[] args) {

        BlockingQueue queue = new BlockingQueue(5);

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i = 0; i < 10; i++) {
                        System.out.println("thread producer adds: " + i);
                        queue.enqueue(i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    Thread consumer = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                for (int i =0; i < 10; i++) {
                    int point = queue.dequeue();
                    System.out.println("thread consumed deleted: " + i);

                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    });

    producer.start();
    consumer.start();

    try {
        producer.join();
        consumer.join();
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
    }
}
