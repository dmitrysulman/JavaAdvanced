package multithreading;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
                System.out.println("Work completed " + Thread.currentThread().getId());
            });
        }
        System.out.println("Finish! " + Thread.currentThread().getId());
//        executorService.shutdownNow();

        executorService.shutdown();
        System.out.println("test1");
        System.out.println(executorService.awaitTermination(3000, TimeUnit.MILLISECONDS));
        System.out.println("test2");
    }
}
