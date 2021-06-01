package com.example.timetable;

public class Group_edu_component {
    private int gecId;
    private int groId;
    private String eduComponent;
    private int deleted;

    public Group_edu_component() {
    }

    public Group_edu_component(int gecId, int groId, String eduComponent, int deleted) {
        this.gecId = gecId;
        this.groId = groId;
        this.eduComponent = eduComponent;
        this.deleted = deleted;
    }

    public int getGecId() {
        return gecId;
    }

    public void setGecId(int gecId) {
        this.gecId = gecId;
    }

    public int getGroId() {
        return groId;
    }

    public void setGroId(int groId) {
        this.groId = groId;
    }

    public String getEduComponent() {
        return eduComponent;
    }

    public void setEduComponent(String eduComponent) {
        this.eduComponent = eduComponent;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}
