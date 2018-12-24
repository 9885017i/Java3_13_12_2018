package concurrency.queue;

public class Queue {

    int n;
    private boolean valueSet = false;

    public synchronized int get() {
        while (!valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupt!!!");
                valueSet = false;
                return n;
            }
        }
        System.out.println(" Получено:" + n);
        valueSet = false;
        notifyAll();
        return n;
    }

    public synchronized void put(int n) {
        while (valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                valueSet = true;
                System.out.println("Interrupt!!!");
                return;
            }
        }
        System.out.println("Отправлено: " + n);
        this.n = n;
        valueSet = true;
        notifyAll();
    }
}
