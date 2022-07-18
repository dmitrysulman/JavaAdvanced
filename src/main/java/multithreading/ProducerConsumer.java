package multithreading;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class ProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
//        ArrayBlockingQueue
        WaitAndNotify waitAndNotify = new WaitAndNotify();

        Thread thread1 = new Thread(() -> {
            try {
                waitAndNotify.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                waitAndNotify.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}

class WaitAndNotify {
    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer thread started...");
            wait();
            System.out.println("Producer thread resumed...");
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(2000);
        Scanner sc = new Scanner(System.in);

        synchronized (this) {
            System.out.println("Waiting for return key pressed...");
            sc.nextLine();
            notify();
            Thread.sleep(5000);
        }
    }
}
