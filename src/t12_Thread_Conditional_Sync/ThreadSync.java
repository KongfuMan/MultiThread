package t12_Thread_Conditional_Sync;


// 条件同步的实现，实现了三个线程轮流执行
public class ThreadSync {
    private int signal;

    public synchronized void a(){
        while (signal != 0){    //这里为什么不能换成if? 因为执行wait()之后，线程等待，被唤醒后继续执行wait()下面的代码
            try {
                wait();//注意，当该线程再次被唤醒的时候，程序继续从下一行开始执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("a");
        signal = 1;
        notifyAll();
    }

    public synchronized void b(){
        while (signal != 1){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("b");
        signal = 2;
        notifyAll();
    }

    public synchronized void c(){
        while (signal != 2){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("c");
        signal = 0;
        notifyAll();
    }

    public static void main(String[] args) {
        ThreadSync d = new ThreadSync();
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
        private ThreadSync d;

        public TargetA(ThreadSync d) {
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
        private ThreadSync d;

        public TargetB(ThreadSync d) {
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
        private ThreadSync d;

        public TargetC(ThreadSync d) {
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

