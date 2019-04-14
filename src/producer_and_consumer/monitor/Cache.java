package producer_and_consumer.monitor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cache {
    private final static int SIZE = 100;
    private int count;
    private Lock lock;
    private Condition notFull;
    private Condition notEmpty;

    public Cache() {
        this.count = 0;
        this.lock = new ReentrantLock();
        this.notEmpty = lock.newCondition();
        this.notFull = lock.newCondition();
    }

    public void produce(){
        lock.lock();
        while(count == SIZE){
            try {
                System.out.println("容器已满，生产者阻塞");
                notFull.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count++;
        System.out.println("生产了一个，当前数量为： "+ count);
        notEmpty.signal();
        lock.unlock();
    }

    public void consume(){
        lock.lock();
        while(count == 0){
            try {
                System.out.println("容器已空，消费者阻塞");
                notEmpty.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        System.out.println("消费了一个，当前数量为： "+ count);
        notFull.signal();
        lock.unlock();
    }
}
