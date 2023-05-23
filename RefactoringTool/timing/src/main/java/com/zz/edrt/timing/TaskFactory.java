package com.zz.edrt.timing;

public class TaskFactory {
    public static Task createTask(String taskName) {
        if (taskName.equalsIgnoreCase("detect")) {
            return new DetectTask();
        } else if (taskName.equalsIgnoreCase("genData")) {
            return new GenDataTask();
        } else {
            throw new IllegalArgumentException("Invalid task name: " + taskName);
        }
    }
}

