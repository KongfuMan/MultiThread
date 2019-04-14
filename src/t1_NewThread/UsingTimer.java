package t1_NewThread;

import java.util.Timer;
import java.util.TimerTask;

// 使用定时器去创建线程， Timer类
// spring 中的定时任务是 quartz
public class UsingTimer {
    public static void main(String[] args) {
        Timer timer = new Timer();

        //多种定时方式
        //anonymous class
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("time task is executing");
            }
        };
        timer.schedule(timerTask,5000,1000);
    }
}
