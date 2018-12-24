package concurrency.queue;

import javax.swing.plaf.TableHeaderUI;

public class ActiveMq {

    public static void main(String[] args) throws InterruptedException {
        Queue queue = new Queue();
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        Thread.sleep(1000);

//        producer.stop();
//        consumer.stop();

        producer.thread.interrupt();
        consumer.thread.interrupt();

    }
}
