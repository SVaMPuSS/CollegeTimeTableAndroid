package com.example.timetable;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.helper.widget.Layer;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class TimeTableAdapter extends ArrayAdapter<TimeTableRec> {
    public TimeTableAdapter(@NonNull Context context, int resource, @NonNull List<TimeTableRec> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TimeTableRec tt = getItem(position);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        String s = null;
        String ss = null;

        try {
            Date date = formatter.parse(tt.getDate());
            s = new SimpleDateFormat("dd.mm.yy").format(date);
            SimpleDateFormat format = new SimpleDateFormat("EEEE",Locale.forLanguageTag("ru-RU"));
            format.setTimeZone(TimeZone.getTimeZone("Russian"));
            ss = format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.my_list_item, null);
        TimeTableRec ttr = null;
        TextView v = convertView.findViewById(R.id.startDateTextView);
        if((position-1) >=0) {
            ttr = getItem(position-1);
            if (!ttr.getDate().equals(tt.getDate())) {
                v.setText(s + " - " + ss);
            }else
            {
                v.setVisibility(View.GONE);
            }
        }else {
            v.setText(s + " - " + ss);
        }

        TextView date = convertView.findViewById(R.id.startDateTextView);
        date.setText(s);

        TextView time = convertView.findViewById(R.id.timeLessonTextView);
        time.setText(tt.getTime());

        TextView notes = convertView.findViewById(R.id.lessonNotesTextView);
        notes.setText(tt.getComponent());

        TextView worker = convertView.findViewById(R.id.workerNameTextView);
        worker.setText(tt.getWorker());

        TextView lesf = convertView.findViewById(R.id.lessonFormTextView);
        lesf.setText(tt.getName());
        return convertView;
    }
}
