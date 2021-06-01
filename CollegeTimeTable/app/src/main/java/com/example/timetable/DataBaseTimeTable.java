package com.example.timetable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.security.PublicKey;
import java.sql.Date;
import java.util.ArrayList;

public class DataBaseTimeTable extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "CollegeTimeTable.db";


    public DataBaseTimeTable(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
        onUpgrade(db, 1, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLES = "CREATE TABLE GROUPS(   groId INTEGER PRIMARY KEY,   name TEXT,   course INTEGER,   edfId INTEGER,   grtId INTEGER,   deleted INTEGER );";
        db.execSQL(CREATE_TABLES);

        CREATE_TABLES = "CREATE TABLE WORKERS(   worId INTEGER PRIMARY KEY,   worker TEXT,   deleted INTEGER ); ";
        db.execSQL(CREATE_TABLES);

        CREATE_TABLES = "CREATE TABLE ROOMS(   rooId INTEGER PRIMARY KEY,   room TEXT,   deleted INTEGER );";
        db.execSQL(CREATE_TABLES);

        CREATE_TABLES = "CREATE TABLE LESSONFORMS(   lfoId INTEGER PRIMARY KEY,   name TEXT ); ";
        db.execSQL(CREATE_TABLES);

        CREATE_TABLES = "CREATE TABLE EDU_COMPONENTS(   gecId INTEGER PRIMARY KEY,   groId INTEGER,   eduComponent TEXT,   deleted INTEGER );";
        db.execSQL(CREATE_TABLES);

        CREATE_TABLES = "CREATE TABLE TIMETABLE(   ttaId INTEGER PRIMARY key,   lessonDate text,   ttdId integer,   lessonTime TEXT,   groID INTEGER,   subgroup Integer,   worId Integer,   rooId integer,   gecId Integer,   lfoId Integer,   notes Text,   lessonType Integer,   deleted Integer);";
        db.execSQL(CREATE_TABLES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table IF EXISTS GROUPS");
        db.execSQL("drop table IF EXISTS WORKERS");
        db.execSQL("drop table IF EXISTS ROOMS");
        db.execSQL("drop table IF EXISTS LESSONFORMS");
        db.execSQL("drop table IF EXISTS EDU_COMPONENTS");
        db.execSQL("drop table IF EXISTS TIMETABLE");
        onCreate(db);
    }

    public ArrayList<TimeTableRec> GetTimeTable() {
        ArrayList<TimeTableRec> list = new ArrayList<TimeTableRec>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "Select t.lessonDate,t.lessonTime,e.eduComponent, l.name, w.worker " +
                "from TIMETABLE t " +
                "inner join EDU_COMPONENTS e on(t.gecId = e.gecId) " +
                "inner join LESSONFORMS l on(l.lfoId = t.lfoId) " +
                "inner join WORKERS w on(w.worId = t.worId)" +
                "order by(1) ";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                TimeTableRec tt = new TimeTableRec();
                tt.setDate(cursor.getString(0));
                tt.setTime(cursor.getString(1));
                tt.setComponent(cursor.getString(2));
                tt.setName(cursor.getString(3));
                tt.setWorker(cursor.getString(4));
                list.add(tt);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public void fillTimeTable(TimeTable t,boolean f) {
        SQLiteDatabase db = this.getWritableDatabase();
        if(f){
            db.execSQL("drop table IF EXISTS TIMETABLE");
            db.execSQL("CREATE TABLE TIMETABLE(   ttaId INTEGER PRIMARY key,   lessonDate text,   ttdId integer,   lessonTime TEXT,   groID INTEGER,   subgroup Integer,   worId Integer,   rooId integer,   gecId Integer,   lfoId Integer,   notes Text,   lessonType Integer,   deleted Integer);");
        }else {
            ContentValues c = new ContentValues();
            c.put("ttaId", t.getTtaId());
            c.put("lessonDate", t.getLessonDate());
            c.put("lessonTime", t.getLessonTime());
            c.put("groID", t.getGroId());
            c.put("subgroup", t.getSubGroup());
            c.put("worId", t.getWorId());
            c.put("rooId", t.getRooId());
            c.put("gecId", t.getGecId());
            c.put("lfoId", t.getLfoId());
            c.put("notes", t.getNotes());
            c.put("lessonType", t.getLessonType());
            c.put("deleted", t.getDeleted());
            c.put("ttdId", t.getTtdId());
            db.insert("TIMETABLE", null, c);
            db.close();
        }
    }

    public void fillGroupsTable(Group g) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("groId", g.getGroId());
        c.put("name", g.getName());
        c.put("course", g.getCourse());
        c.put("edfId", g.getEdfId());
        c.put("grtId", g.getGrtId());
        c.put("deleted", g.getDeleted());
        db.insert("Groups", null, c);
        db.close();
    }

    public void fillWorkersTable(Worker w) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("worker", w.getWorker());
        c.put("worId", w.getWorId());
        c.put("deleted", w.getDeleted());
        db.insert("WORKERS", null, c);
        db.close();
    }

    public void fillRoomsTable(Room r) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("rooId", r.getRooId());
        c.put("room", r.getRoom());
        c.put("deleted", r.getDeleted());
        db.insert("ROOMS", null, c);
        db.close();
    }

    public void fillLessonForms(LessonForm lf) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("lfoId", lf.getLfoId());
        c.put("name", lf.getName());
        db.insert("LESSONFORMS", null, c);
        db.close();
    }

    public void fillEdu_Components(Group_edu_component e) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("eduComponent", e.getEduComponent());
        c.put("deleted", e.getDeleted());
        c.put("gecId", e.getGecId());
        c.put("groId", e.getGroId());
        db.insert("EDU_COMPONENTS", null, c);
        db.close();
    }

    public ArrayList<Group> getAllGroup() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Group> list = new ArrayList<Group>();
        String sql = "Select * from Groups";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                Group g = new Group();
                g.setGroId(Integer.parseInt(cursor.getString(0)));
                g.setName(cursor.getString(1));
                g.setCourse(Integer.parseInt(cursor.getString(2)));
                g.setEdfId(Integer.parseInt(cursor.getString(3)));
                g.setGrtId(Integer.parseInt(cursor.getString(4)));
                g.setDeleted(Integer.parseInt(cursor.getString(5)));
                list.add(g);
            } while (cursor.moveToNext());
        }
        return list;
    }

//    public Group_edu_component GetComponent(int gecId){
//        Group_edu_component c = new Group_edu_component();
//        SQLiteDatabase db = this.getReadableDatabase();
//        String s = "select gecId, groId, eduComponent, deleted from EDU_COMPONENTS where (gecId = "+gecId+") ";
//        //String s = "select * from EDU_COMPONENTS";
//        Cursor cur = db.rawQuery(s,null);
//        if(cur.moveToFirst()){
//            c.setGecId(gecId);
//            c.setGroId(Integer.parseInt(cur.getString(1)));
//            c.setEduComponent(cur.getString(2));
//            c.setDeleted(Integer.parseInt(cur.getString(3)));
//        }
//        return c;
//    }
//
//    public Worker GetWorker(int worId){
//        Worker w =new Worker();
//        SQLiteDatabase db = this.getReadableDatabase();
//        String s = "select * from WORKERS where(worId = "+worId+")";
//        Cursor cur = db.rawQuery(s,null);
//        if(cur.moveToFirst()){
//            w.setWorld(worId);
//            w.setWorker(cur.getString(1));
//            w.setDeleted(Integer.parseInt(cur.getString(2)));
//        }
//        return w;
//    }
//
//    public LessonForm GetLessonForm(int lfoId){
//        LessonForm l = new LessonForm();
//        String s ="select * from LESSONFORMS where(lfoId = "+lfoId+")";
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cur = db.rawQuery(s,null);
//        if(cur.moveToFirst()){
//            l.setLfoId(lfoId);
//            l.setName(cur.getString(1));
//        }
//        return l;
//    }
//
//        public ArrayList<Worker> getAllWorkers(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        ArrayList<Worker> list = new ArrayList<Worker>();
//        String sql = "Select * from workers";
//        Cursor cursor = db.rawQuery(sql,null);
//        if(cursor.moveToFirst()){
//            do {
//                Worker w = new Worker();
//                w.setWorld(Integer.parseInt(cursor.getString(0)));
//                w.setWorker(cursor.getString(1));
//                w.setDeleted(Integer.parseInt(cursor.getString(2)));
//                list.add(w);
//            }while (cursor.moveToNext());
//        }
//        return list;
//    }
}
