package multithreading;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();

        MyThread myThread1 = new MyThread();
        myThread1.start();
        System.out.println("1");
        Thread.sleep(3000);
        System.out.println("2");
        System.out.println("Hello from Main!");
        myThread.interrupt();
        Thread thread = new Thread(new Runner());
        thread.start();
    }
}

class MyThread extends Thread {
    private volatile boolean running = true;

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("Hello from MyThread! " + i);
        }
    }
}

class Runner implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("Hello from MyThread Run! " + i);
        }
    }
}
