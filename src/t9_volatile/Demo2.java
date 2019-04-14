package t9_volatile;

/*
* volatile 关键字保证了变量在各个线程之间的可见性
* */
public class Demo2 {
    public volatile boolean run = false;

    public static void main(String[] args) {
        Demo2 d = new Demo2();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++){
                    System.out.println(Thread.currentThread().getName()+ "正在执行第"+i+"次");
                    d.run = true;   //修改后立刻被刷新到主内存中
                }

                System.out.println(Thread.currentThread().getName()+ "退出");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!d.run){ //每次都从主内存中读取最新的run值， 如果不加volatile，则只是从线程的工作内存中读取
                }
                System.out.println(Thread.currentThread().getName()+ "正在执行");
            }
        }).start();
    }

}
