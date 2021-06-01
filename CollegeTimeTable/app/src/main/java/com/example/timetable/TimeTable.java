package com.example.timetable;

public class TimeTable {
    private int ttaId;//ид таблицы
    private int ttdId;
    private int groId;//группа
    private int worId;//препод
    private int rooId;//кабинет
    private int gecId;//предмет
    private int lfoId;//форма обучения
    private String lessonDate;//дата
    private String lessonTime;//время
    private String notes;//подпись
    private int lessonType;//тип лекции
    private int deleted;//удалено / нет
    private int subGroup;//подгруппа
    public TimeTable() {
    }

    public int getTtaId() {
        return ttaId;
    }

    public void setTtaId(int ttaId) {
        this.ttaId = ttaId;
    }

    public int getTtdId() {
        return ttdId;
    }

    public void setTtdId(int ttdId) {
        this.ttdId = ttdId;
    }

    public int getGroId() {
        return groId;
    }

    public void setGroId(int groId) {
        this.groId = groId;
    }

    public int getWorId() {
        return worId;
    }

    public void setWorId(int worId) {
        this.worId = worId;
    }

    public int getRooId() {
        return rooId;
    }

    public void setRooId(int rooId) {
        this.rooId = rooId;
    }

    public int getGecId() {
        return gecId;
    }

    public void setGecId(int gecId) {
        this.gecId = gecId;
    }

    public int getLfoId() {
        return lfoId;
    }

    public void setLfoId(int lfoId) {
        this.lfoId = lfoId;
    }

    public String getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(String lessonDate) {
        this.lessonDate = lessonDate;
    }

    public String getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(String lessonTime) {
        this.lessonTime = lessonTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getLessonType() {
        return lessonType;
    }

    public void setLessonType(int lessonType) {
        this.lessonType = lessonType;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getSubGroup() {
        return subGroup;
    }

    public void setSubGroup(int subGroup) {
        this.subGroup = subGroup;
    }

    public TimeTable(int ttaId, int ttdId, int groId, int worId, int rooId, int gecId, int lfoId, String lessonDate, String lessonTime, String notes, int lessonType, int deleted, int subGroup) {
        this.ttaId = ttaId;
        this.ttdId = ttdId;
        this.groId = groId;
        this.worId = worId;
        this.rooId = rooId;
        this.gecId = gecId;
        this.lfoId = lfoId;
        this.lessonDate = lessonDate;
        this.lessonTime = lessonTime;
        this.notes = notes;
        this.lessonType = lessonType;
        this.deleted = deleted;
        this.subGroup = subGroup;
    }
}
