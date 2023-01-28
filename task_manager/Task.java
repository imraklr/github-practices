package repo1.task_manager;

import java.time.LocalTime;

public class Task {
    private String link;
    private LocalTime localTime;

    public Task(String link, int hour, int minute, int second) {
        this.link = link;
        this.localTime = LocalTime.of(hour, minute, second);
    }

    public LocalTime getLocalTime() {
        return localTime;
    }
    
    public String getLink() {
        return link;
    }
}
