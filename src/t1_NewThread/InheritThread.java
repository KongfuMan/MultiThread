package t1_NewThread;

public class InheritThread extends Thread {

    @Override
    public void run(){
        while (!interrupted()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getName() + " is executing");
        }
    }

    public static void main(String[] args) {
        InheritThread t = new InheritThread();
//        InheritThread t1 = new InheritThread();
//        t.setDaemon(true);
//        t1.setDaemon(true);
        t.start();
//        t1.start();
//        t.interrupt();
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
