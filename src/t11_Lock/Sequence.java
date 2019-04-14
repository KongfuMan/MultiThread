package t11_Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Lock的应用
public class Sequence {

    private int value;
    private Lock l = new ReentrantLock();

    public int next(){
        l.lock();
        int a = value++;
        l.unlock();
        return a;

    }


    public static void main(String[] args) {
        Sequence s = new Sequence();
        for(int i = 0; i < 3; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        System.out.println(Thread.currentThread().getName() + " " +s.next());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }
}
