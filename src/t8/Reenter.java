package t8;

// 锁的重入，synchronized是可重入的
// 递归调用时，上层获得一个锁对象，调用其他函数时，默认获得该锁对象
public class Reenter {
    public synchronized void a(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("a");
        b();    // 这里不会产生死锁，这是重入锁的概念：可重入锁，也叫做递归锁，指的是同一线程外层函数获得锁之后 ，内层递归函数仍然有获取该锁的代码，但不受影响。
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void b(){

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("b");
    }

    public static void main(String[] args) {
        Reenter d = new Reenter();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                d.a();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                d.b();
            }
        });
        t1.start();
        t2.start();
    }
}
