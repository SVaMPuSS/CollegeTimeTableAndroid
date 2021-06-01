package com.example.timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    JSONArray a;
    JSONArray a1;
    JSONArray a2;
    JSONArray a3;
    JSONArray a4;
    ArrayList<TimeTableRec> ListTable;
    TimeTableAdapter adapter;
    ArrayList<Group> Groups;
    GroupAdapter adapterGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBaseTimeTable MyTable = new DataBaseTimeTable(this);
        ((TextView) findViewById(R.id.choiceGroupsTextView)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.menuTextView)).setVisibility(View.GONE);
        ((Spinner) findViewById(R.id.groupsSpinner)).setVisibility(View.GONE);
        ((Button) findViewById(R.id.closeMenuButton)).setVisibility(View.GONE);

//
        JsonTask js1 = new JsonTask();
        js1.execute("http://188.68.31.11:8080/select_edu_components.php");
        JsonTask js2 = new JsonTask();
        js2.execute("http://188.68.31.11:8080/select_workers.php");
        JsonTask js3 = new JsonTask();
        js3.execute("http://188.68.31.11:8080/select_lesson_forms.php");
        JsonTask js4 = new JsonTask();
        js4.execute("http://188.68.31.11:8080/select_groups.php");
        Gson gson = new Gson();
        try {
//            a = new JSONArray(js.get());
            a1 = new JSONArray(js1.get());
            a2 = new JSONArray(js2.get());
            a3 = new JSONArray(js3.get());
            a4 = new JSONArray(js4.get());
//            if (a != null) {
//                for (int i = 0; i < a.length(); i++) {
//                    TimeTable t = gson.fromJson(a.get(i).toString(), TimeTable.class);
//                    MyTable.fillTimeTable(t);
//                }
//            }
            if (a1 != null) {
                for (int i = 0; i < a1.length(); i++) {
                    Group_edu_component g = gson.fromJson(a1.get(i).toString(), Group_edu_component.class);
                    MyTable.fillEdu_Components(g);
                }
            }
            if (a2 != null) {
                for (int i = 0; i < a2.length(); i++) {
                    Worker w = gson.fromJson(a2.get(i).toString(), Worker.class);
                    MyTable.fillWorkersTable(w);
                }
            }
            if (a3 != null) {
                for (int i = 0; i < a3.length(); i++) {
                    LessonForm l = gson.fromJson(a3.get(i).toString(), LessonForm.class);
                    MyTable.fillLessonForms(l);
                }
            }
            if (a4 != null) {
                for (int i = 0; i < a4.length(); i++) {
                    Group g = gson.fromJson(a4.get(i).toString(), Group.class);
                    MyTable.fillGroupsTable(g);
                }
            }

        } catch (JSONException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        Groups = MyTable.getAllGroup();
        adapterGroup = new GroupAdapter(this, 0, Groups);
        Spinner mys = (Spinner) findViewById(R.id.groupsSpinner);
        mys.setAdapter(adapterGroup);
        mys.setSelection(0);
        AdapterView.OnItemSelectedListener lis = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Group g = Groups.get(position);
                JsonTask js = new JsonTask();
                js.execute("http://188.68.31.11:8080/select_time_table.php?groId=" + g.getGroId());
                try {
                    Gson gson = new Gson();
                    a = new JSONArray(js.get());
                    if (a != null) {
                        MyTable.fillTimeTable(null,true);
                        for (int i = 0; i < a.length(); i++) {
                            TimeTable t = gson.fromJson(a.get(i).toString(), TimeTable.class);
                            MyTable.fillTimeTable(t,false);
                            ListTable = MyTable.GetTimeTable();
                            adapter = new TimeTableAdapter(view.getContext(), 0, ListTable);
                            ListView lv = findViewById(R.id.timeTableListView);
                            lv.setAdapter(adapter);
                        }
                    }
                } catch (JSONException | ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        mys.setOnItemSelectedListener(lis);

    }

    public void CloseMenuClick(View v) {
        ((TextView) findViewById(R.id.choiceGroupsTextView)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.menuTextView)).setVisibility(View.GONE);
        ((Spinner) findViewById(R.id.groupsSpinner)).setVisibility(View.GONE);
        v.setVisibility(View.GONE);
    }

    public void ShowMenuClick(View v) {
        ((TextView) findViewById(R.id.choiceGroupsTextView)).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.menuTextView)).setVisibility(View.VISIBLE);
        ((Spinner) findViewById(R.id.groupsSpinner)).setVisibility(View.VISIBLE);
        ((Button) findViewById(R.id.closeMenuButton)).setVisibility(View.VISIBLE);
    }

}