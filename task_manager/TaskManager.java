package repo1.task_manager;

import java.time.LocalTime;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/*
 * This thread has to be a daemon thread.
 * On exiting the application, threads are forced to exit/destroyed.
 */
public class TaskManager extends Thread {
    private Task task;

    public TaskManager(String threadName, Task task) {
        this.setName(threadName);
        this.setDaemon(true);
        this.task = task;
        /* On instantiation the start() method will be called so as to avoid boilerplate code
         * and it serves the purpose for launching the thread as soon as a task is assigned to it.
        */
        start();
    }

    // Avoiding boilerplate code by calling constructor
    public TaskManager(String threadName, Task ...tasks) {
        /*
         * Make first call to constrcutor - public TaskManager(String threadName, Task task)
         * to avoid using up extra memory.
         */
        this(threadName, tasks[0]);
        /*
         * No need to store TaskManager objects as they will be destroyed by GC automatically.
         */
        for(int i=1;i<tasks.length;i++)
            new TaskManager(threadName, tasks[i]);
    }

    @Override
    public synchronized void run() {
        LocalTime localTime = LocalTime.now();
        try {
            LocalTime arrival = task.getLocalTime();
            long convertedArrivalTime = arrival.toNanoOfDay();
            long convertedCurrentTime = localTime.toNanoOfDay();
            long timeDiff = convertedArrivalTime-convertedCurrentTime;

            System.out.println(convertedArrivalTime+", "+convertedCurrentTime+" = "+timeDiff);
            if(timeDiff<0)
                System.out.println("Event Closed");
            else if(timeDiff==0) {
                // Execute task
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
                    Desktop.getDesktop().browse(new URI(task.getLink()));
            }
            else {
                Thread.sleep((long)(timeDiff*1e-6));
                // Execute task
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
                    Desktop.getDesktop().browse(new URI(task.getLink()));
            }
        }catch(InterruptedException | IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        run();
    }
}
