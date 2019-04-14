package t2_ThreadSafe;

// 从线程优先级看线程饥饿问题
public class ThreadStarvation implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " is executing");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadStarvation());
        Thread t2 = new Thread(new ThreadStarvation());
        Thread t3 = new Thread(new ThreadStarvation());
        Thread t4 = new Thread(new ThreadStarvation());

        t1.setPriority(10);
        t2.setPriority(1);
        t3.setPriority(1);
        t4.setPriority(1);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
