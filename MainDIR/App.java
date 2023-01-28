package repo1.MainDIR;

import repo1.task_manager.*;

public class App {
    public static void main(String[] args) {

        /*
         * Following was used to conduct a test for accuracy and working of TaskManager
         * String threadName = "Test Thread 1";
         * String url = "https://example.com";
         * new TaskManager(threadName, new Task(url, 11, 53, 00));
         * 
         * Task[] tasks = new Task[100];
         * for(int i=0;i<100;i++)
         * tasks[i] = new Task(url, 12, 18, (i+11)%60);
         * new TaskManager(threadName, tasks);
         * 
         * This code yielded an Event Closed message and 50 tasks were executed successfully.
         * Other 50 tasks were closed for reason undetermined.
         */

         // Following is the schedule
         new TaskManager("saturday 2:30 sub Physics", 
            new Task("meet.google.com/msk-daxs-bmk", 2, 30, 0)
         );
         new TaskManager("saturday 3:00 sub Mathematics", 
            new Task("meet.google.com/msk-daxs-bmk", 3, 30, 0)
         );
    }
}
