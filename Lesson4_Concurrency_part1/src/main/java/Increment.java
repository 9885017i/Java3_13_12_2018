import javax.swing.plaf.TableHeaderUI;
import java.awt.font.TextHitInfo;
import java.util.concurrent.atomic.AtomicInteger;

public class Increment {

    private AtomicInteger data = new AtomicInteger(0);
    private int value;

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public synchronized static void main(String[] args) throws InterruptedException {
//        synchronized (Increment.class) {
            Increment increment = new Increment();

            Thread t1 = new Thread(() -> {
                while (true) {
                    try {
                        increment.incrementValue();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "t1");
            t1.setDaemon(true);

            Thread t2 = new Thread(() -> {
                while (true) {
                    try {
                        increment.incrementValue();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "t2");
            t2.setDaemon(true);

            t1.start();
            t2.start();

            Thread.sleep(1000);
//        }
    }

    private synchronized void incrementValue() throws InterruptedException {
//        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " data = " + ++value);//value = value + 1;
            Thread.sleep(10);
            System.out.println(Thread.currentThread().getName() + " data = " + data.incrementAndGet());
//        }
    }

    private void incrementValue1() throws InterruptedException {
        synchronized (lock1) {
            lock1.wait();
            System.out.println(Thread.currentThread().getName() + " data = " + ++value);//value = value + 1;
            Thread.sleep(10);
            System.out.println(Thread.currentThread().getName() + " data = " + data.incrementAndGet());
        }
    }

    private void incrementValue2() throws InterruptedException {
        synchronized (lock2) {
        System.out.println(Thread.currentThread().getName() + " data = " + ++value);//value = value + 1;
        Thread.sleep(10);
        System.out.println(Thread.currentThread().getName() + " data = " + data.incrementAndGet());
        }
    }
}
