package t1_NewThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

// Integer means the type of return value of call function
public class ImplCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("executing");
        return 1;
    }

    public static void main(String[] args) {
        ImplCallable d = new ImplCallable();

        //FutureTask implements Runnable interface
        FutureTask<Integer> futureTask = new FutureTask<Integer>(d);
        Thread t = new Thread(futureTask);
        t.start();
        try {
            Integer res = futureTask.get();
            System.out.println("result is "+ res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
