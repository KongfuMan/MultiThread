package t1_NewThread;

// 创建Runnable匿名类的方式
public class AnonymousClass {
    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run(){
                System.out.println("线程被执行");
            }
        };
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程被执行");
            }
        });
        t2.start();

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程被执行 runnable");
            }
        }){
            @Override
            public void run(){
                System.out.println("线程被执行 target");
            }
        };
        t3.start();
    }
}
