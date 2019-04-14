package t8;

public class Starvation {
    Object lock1 = new Object();
    Object lock2 = new Object();

    public void a(){
        synchronized (lock1){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2){
                System.out.println("Hello");
            }
        }
    }

    public void b(){
        synchronized (lock2){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock1){
                System.out.println("World");
            }
        }
    }

    public static void main(String[] args) {
        Starvation d = new Starvation();
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.a();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.b();
            }
        }).start();
    }
}
