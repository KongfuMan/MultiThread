package t12_Thread_Conditional_Sync;

import java.util.concurrent.Semaphore;

public class SemaphoreImpl {
    private static Semaphore A = new Semaphore(1);
    private static Semaphore B = new Semaphore(0);
    private static Semaphore C = new Semaphore(0);

    private static class TaskA implements Runnable{
        @Override
        public void run() {
            while(true){
                try {
                    A.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("A");
                B.release();
            }
        }
    }

    private static class TaskB implements Runnable{
        @Override
        public void run() {
            while(true){
                try {
                    B.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("B");
                C.release();
            }
        }
    }

    private static class TaskC implements Runnable{
        @Override
        public void run() {
            while(true){
                try {
                    C.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("C");
                A.release();
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new TaskA());
        Thread t2 = new Thread(new TaskB());
        Thread t3 = new Thread(new TaskC());
        t1.start();
        t2.start();
        t3.start();
    }


}
