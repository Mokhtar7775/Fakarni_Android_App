package com.example.fakarni;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.example.taskati.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class CalenderActivity extends AppCompatActivity {

    //Declarations of variables
    protected CalendarView calendarView ; //onCreate
    protected List<EventDay> events = new ArrayList<>() ; //setEvent()
    protected Calendar setEventsCalendar ; //setEvent()
    protected String[] y; //setEvent()
    protected Database database ; //setEvent()
    protected Calendar ClickedDayCalender; //onDayClick()
    protected static String clickedDate,clickedDate2; //onDayClick()
    protected Cursor cursor;
    public static boolean isClick = false;


    public void setEvent() {
        //Initialization of Variables
        database = new Database(this);
        cursor = database.readAllTaskData(LogIn.id);
        events = new ArrayList<>();

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            @SuppressLint("Range") String data = cursor.getString(2);
            y = data.split("/");
            int year = Integer.parseInt(y[0]);
            int month = Integer.parseInt(y[1]) - 1;
            int day = Integer.parseInt(y[2]);



            setEventsCalendar = Calendar.getInstance();
            setEventsCalendar.set(year, month, day);
            events.add(new EventDay(setEventsCalendar, R.drawable.scheduler_logo));
            calendarView.setEvents(events);
        }
    }
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDayClickListener(eventDay -> {
            isClick=true;

            ClickedDayCalender = eventDay.getCalendar();
            int year = ClickedDayCalender.get(Calendar.YEAR);
            int month = (ClickedDayCalender.get(Calendar.MONTH))+1;
            int day = ClickedDayCalender.get(Calendar.DATE);
            //date returned when a day clicked//
            clickedDate = year+"-"+month+"-"+ day;
            clickedDate2 = String.format("%d/%02d/%02d",year,month,day);

            Intent intent = new Intent(this, MainActivity.class);
            MainActivity.titleDate = clickedDate2;
            startActivity(intent);
        });
        setEvent();

    }
}