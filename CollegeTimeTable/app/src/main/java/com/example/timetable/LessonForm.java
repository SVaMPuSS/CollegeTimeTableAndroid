package com.example.timetable;

public class LessonForm {
    private int lfoId;
    private String name;

    public LessonForm() {
    }

    public int getLfoId() {
        return lfoId;
    }

    public void setLfoId(int lfoId) {
        this.lfoId = lfoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LessonForm(int lfoId, String name) {
        this.lfoId = lfoId;
        this.name = name;
    }
}
