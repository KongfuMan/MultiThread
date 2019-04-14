package t2_ThreadSafe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {
    private static int val = 0;
    private static Lock lock = new ReentrantLock();
    public static void next(){
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " is executing");
        ++val;
        if(true){   //注意抛出异常时候，不会释放锁
            throw new RuntimeException("throw exception");
        }
        lock.unlock();
    }

    public static class TaskA implements Runnable{
        @Override
        public void run(){
            try{
                next();
            }catch (Exception e){

            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new TaskA()).start();
        new Thread(new TaskA()).start();
    }



}
