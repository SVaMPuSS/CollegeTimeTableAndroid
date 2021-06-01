package com.example.timetable;

public class Group {
    private int groId;
    private String name;
    private int course;
    private int edfId;
    private int grtId;
    private int deleted;

    public Group() {
    }

    public int getGroId() {
        return groId;
    }

    public void setGroId(int groId) {
        this.groId = groId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getEdfId() {
        return edfId;
    }

    public void setEdfId(int edfId) {
        this.edfId = edfId;
    }

    public int getGrtId() {
        return grtId;
    }

    public void setGrtId(int grtId) {
        this.grtId = grtId;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public Group(int groId, String name, int course, int edfId, int grtId, int deleted) {
        this.groId = groId;
        this.name = name;
        this.course = course;
        this.edfId = edfId;
        this.grtId = grtId;
        this.deleted = deleted;
    }
}
