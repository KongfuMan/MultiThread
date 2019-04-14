package t1_NewThread;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 使用线程池创建多线程
//
public class UsingThreadPool {
    public static void main(String[] args) {
        //可以创建多种线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }

        executor.shutdown();


    }
}
