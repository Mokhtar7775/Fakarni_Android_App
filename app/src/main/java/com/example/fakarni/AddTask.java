package com.example.fakarni;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.taskati.R;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import java.util.Calendar;


public class AddTask extends AppCompatActivity {
    private MaterialTimePicker picker;
    private Calendar calendar;

    Database db = new Database(this);

    EditText labelText,dateText,timeText,noteText;
    Button Date;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        calendar = Calendar.getInstance();

        //Date
        Date = findViewById(R.id.calendarButton);
        Date.setOnClickListener(view -> showDatePicker());

        //Time
        Button Time = findViewById(R.id.timeButton);
        Time.setOnClickListener(view -> showTimePicker());

        //Edit text
        labelText = findViewById(R.id.labelText);
        dateText = findViewById(R.id.dateText);
        timeText = findViewById(R.id.timeText);
        noteText = findViewById(R.id.noteText);

        //Save
        Button save = findViewById(R.id.saveButton);
        save.setOnClickListener(v -> {

            if (labelText.getText().toString().isEmpty()) {
                Toast.makeText(this,"Enter the name of the task",Toast.LENGTH_LONG).show();
            }
            else if(dateText.getText().toString().isEmpty()){
                Toast.makeText(this,"Enter the date of the task",Toast.LENGTH_LONG).show();
            }
            else if(timeText.getText().toString().isEmpty()){
                Toast.makeText(this,"Enter the time of the task",Toast.LENGTH_LONG).show();
            }
            else{

                db.insertTaskData(labelText.getText().toString(),dateText.getText().toString(),
                        timeText.getText().toString(),noteText.getText().toString(),LogIn.id);
                Toast.makeText(this,"Task has been set Successfully",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this,MainActivity.class);
                MainActivity.titleDate = date;
                startActivity(intent);

            }
        });

        //Cancel
        Button cancel = findViewById(R.id.cancelButton);
        cancel.setOnClickListener(v -> {});

    }

    private void showDatePicker() {
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        @SuppressLint("DefaultLocale") DatePickerDialog datePickerDialog = new DatePickerDialog(AddTask.this, (view, year1, month1, day1) -> {
            month1 = month1 +1;
            date = String.format("%d/%02d/%02d",year1,month1,day1);
            dateText.setText(date);
        },year,month,day);
        datePickerDialog.show();

    }
    @SuppressLint("DefaultLocale")
    private void showTimePicker() {
        picker = new MaterialTimePicker.Builder()
                //Setting the initial values

                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Task Time")
                .build();

        picker.show(getSupportFragmentManager(), "Task");

        picker.addOnPositiveButtonClickListener(view -> {
            if (picker.getHour() > 12) {
                timeText.setText(String.format("%02d : %02d PM",picker.getHour() - 12,picker.getMinute()));

            } else {
                timeText.setText(String.format("%02d : %02d AM",picker.getHour(),picker.getMinute()));
            }

            //The calendar holds the time that the user has set (the time in the picker)
            calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, picker.getHour());
            calendar.set(Calendar.HOUR_OF_DAY, picker.getMinute());
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
        });

    }


}