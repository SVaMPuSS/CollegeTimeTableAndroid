package com.example.timetable;

public class Worker {
    private int worId;
    private String worker;
    private int deleted;

    public int getWorId() {
        return worId;
    }

    public void setWorld(int worId) {
        this.worId = worId;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public Worker(int worId, String worker, int deleted) {
        this.worId = worId;
        this.worker = worker;
        this.deleted = deleted;
    }

    public Worker() {
    }
}
