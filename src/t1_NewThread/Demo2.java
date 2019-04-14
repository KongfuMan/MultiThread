package t1_NewThread;

// new thread task by implementing runnable interface
// 它只是定义了线程需要执行的任务
// 将线程任务和线程进行分离
public class Demo2 implements Runnable{
    int i = 0;
    @Override
    public void run() {
        try {
            while(true){   //same as the Thread.currentThread().isInterrupted()
                if (i == 100){
                    throw new RuntimeException("123131");
                }
                i++;
                System.out.println("execute new Thread");
            }
        }catch (Exception e){
            System.out.println("caught Excpetion, continue run");
            while (true){
                System.out.println("Thread keep running after exception");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Demo2());
//        t1.setDaemon(true);   // 设置为deamon线程时，当该进程没有非守护线程时，会自动退出
        t1.start();
        Thread.sleep(10);
//        t1.stop();    //  如何终止一个线程？
//        t1.interrupt();
        System.out.println("Main Thread exit");

    }
}

