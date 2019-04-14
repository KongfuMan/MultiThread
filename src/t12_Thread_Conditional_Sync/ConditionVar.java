package t12_Thread_Conditional_Sync;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 条件变量实现线程的同步
public class ConditionVar {
    private int signal = 0;
    Lock lock = new ReentrantLock();
    Condition a = lock.newCondition();
    Condition b = lock.newCondition();
    Condition c = lock.newCondition();

    public void a(){
        lock.lock();
        while (signal != 0){
            try {
                a.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("a");
        signal = 1;
        b.signal();
        lock.unlock();
    }

    public void b(){
        lock.lock();
        while (signal != 1){
            try {
                b.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("b");
        signal = 2;
        c.signal();
        lock.unlock();
    }

    public void c(){
        lock.lock();
        while (signal != 2){
            try {
                c.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("c");
        signal = 0;
        a.signal();
        lock.unlock();
    }

    public static void main(String[] args) {
        ConditionVar d = new ConditionVar();
        TargetA A = new TargetA(d);
        TargetB B = new TargetB(d);
        TargetC C = new TargetC(d);

        Thread t1 = new Thread(A);
        Thread t2 = new Thread(B);
        Thread t3 = new Thread(C);

        t1.start();
        t2.start();
        t3.start();
    }

    private static class TargetA implements Runnable{
        private ConditionVar d;

        public TargetA(ConditionVar d) {
            this.d = d;
        }

        @Override
        public void run() {
            while (true){
                d.a();
            }

        }
    }

    private static class TargetB implements Runnable{
        private ConditionVar d;

        public TargetB(ConditionVar d) {
            this.d = d;
        }

        @Override
        public void run() {
            while (true){
                d.b();
            }
        }
    }

    private static class TargetC implements Runnable{
        private ConditionVar d;

        public TargetC(ConditionVar d) {
            this.d = d;
        }

        @Override
        public void run() {
            while (true){
                d.c();
            }
        }
    }
}

