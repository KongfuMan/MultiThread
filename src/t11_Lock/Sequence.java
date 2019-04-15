package t11_Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Lock的应用:
// 如果一个线程获取了锁，但没有释放锁，其他线程会阻塞并等待获取锁
// 该线程释放锁是，会唤醒一个阻塞线程
public class Sequence {

    private int value;
    private Lock l = new ReentrantLock();

    public int next(){
        l.lock();
        int a = value++;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        l.unlock();
        return a;
    }

    public static class Task implements Runnable{
        private Sequence s;

        public Task(Sequence s) {
            this.s = s;
        }

        @Override
        public void run() {
            while (true){
                System.out.println(Thread.currentThread().getName() + " " +s.next());
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }

    public static void main(String[] args) {
        Sequence s = new Sequence();
        new Thread(new Task(s)).start();
        new Thread(new Task(s)).start();
        new Thread(new Task(s)).start();
        new Thread(new Task(s)).start();
    }
}
