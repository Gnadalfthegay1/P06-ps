package com.myapplicationdev.android.p05_ps;

public class Task {
    private int _id;
    private String task, taskContent;
    private int sec;

    public Task(int _id, String task, String taskContent, int sec) {
        this._id = _id;
        this.task = task;
        this.taskContent = taskContent;
        this.sec = sec;
    }

    public int get_id() {
        return _id;
    }

    public String getTask() {
        return task;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public int getSec() {
        return sec;
    }
}
