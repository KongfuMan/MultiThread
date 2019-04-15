package t9_volatile;

//学习volatile的内存语义，写happens before 读
public class Demo3 {
    private int a;
    private volatile boolean flag;

    public void write(){
        a += 10;
        flag = true;
    }

    public void read(){
        if (flag){
            int b = a + 1;
            System.out.println(b);
        }
    }

    public static void main(String[] args) {
        Demo3 d = new Demo3();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                d.write();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                d.read();
            }
        }).start();


    }
}
