package collections;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExample {



    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue(10);

        Thread producer = createProducer(queue);
        Thread consumer = createConsumer(queue);

        producer.start();
        consumer.start();

        Thread.sleep(2000);
    }

    private static Thread createConsumer(BlockingQueue<Integer> queue) {
        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    Integer value = queue.take();
                    System.out.println("Consumer value is " + value + ". Queue size is " + queue.size());
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        consumer.setDaemon(true);
        return consumer;
    }

    private static Thread createProducer(BlockingQueue<Integer> queue) {
        Random random = new Random();

        Thread producer = new Thread(() -> {
            while (true) {
                try {
                    int value = random.nextInt(100);
                    System.out.println("Produced value = " + value + ". Queue size is " + queue.size());
                    queue.put(value);
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producer.setDaemon(true);
        return producer;
    }
}
