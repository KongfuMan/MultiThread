package t1_NewThread;

public class ImplRunnable implements Runnable{
    @Override
    public void run() {
        while(true){
            try {
                System.out.println("New thread is executed");
                wait();
            }catch (Exception e){

            }

        }
    }

    public static void main(String[] args) throws Exception {
        ImplRunnable newThread = new ImplRunnable();
        Thread thread = new Thread(newThread); // create thread and assign the task
        thread.start();
        synchronized (newThread){
            while(true){
                try {
                    System.out.println("Main thread is executed");
                    Thread.sleep(1000);
                }catch (Exception e){

                }
                newThread.notifyAll();
            }
        }

    }
}
