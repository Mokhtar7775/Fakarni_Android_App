package com.example.fakarni;

import android.content.Intent;
import android.database.Cursor;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskati.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    FloatingActionButton add_btn, edit_btn, calender_btn;
    Animation rotateOpen, rotateClose, fromBottom, toBottom;
    boolean clicked = false;
    TextView no_data,dateOfDay,nameLabel;
    public Database MyDB;
    ArrayList<String> name, notes, time;
    MyAdaptor myAdaptor;
    static String titleDate, dateNow, DATE;
    static Bundle calendarBundle, pickerBundle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // logo of Action bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        dateNow = LocalDate.now().toString();

        calendarBundle = getIntent().getExtras();
        pickerBundle = getIntent().getExtras();

        if(!CalenderActivity.isClick){
            titleDate = dateNow.replace("-","/");
        }
        DATE=titleDate;

        //Textview
        dateOfDay = findViewById(R.id.textView);
        dateOfDay.setText(DATE);
        no_data = findViewById(R.id.noData);
        nameLabel = findViewById(R.id.nameLabel);
        nameLabel.setText(LogIn.name);

        // Buttons
        add_btn = findViewById(R.id.add_btn);
        edit_btn =  findViewById(R.id.edit_btn);
        calender_btn =  findViewById(R.id.calender_btn);

        //Animations
        rotateOpen = AnimationUtils.loadAnimation(this, R.anim.rotate_open_animation);
        rotateClose = AnimationUtils.loadAnimation(this, R.anim.rotate_close_animation);
        fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom_animation);
        toBottom = AnimationUtils.loadAnimation(this, R.anim.to_bottom_animation);


        //RecyclerVIEW
        RecyclerView recyclerView = findViewById(R.id.Rcv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Animation button
        add_btn.setOnClickListener(view -> onAddButtonClicked());

        //Add task Window
        edit_btn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddTask.class);
            startActivity(intent);

        });

        //Calender Window
        calender_btn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CalenderActivity.class);
            startActivity(intent);
        });


        //!!--------------------------------!!
        MyDB = new Database(MainActivity.this);
        name = new ArrayList<>();
        notes = new ArrayList<>();
        time = new ArrayList<>();
        storeDataInArrays(DATE);
        myAdaptor = new MyAdaptor(MainActivity.this,this,name, notes, time);
        recyclerView.setAdapter(myAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        //!!*-----------------------------------!!

    }
    void storeDataInArrays(String DATE){
        Cursor cursor = MyDB.readAllTaskData(DATE,LogIn.id);
        if(cursor.getCount() == 0){
            no_data.setVisibility(View.VISIBLE);
        }
        else{
            while (cursor.moveToNext()){
                no_data.setVisibility(View.INVISIBLE);
                name.add(cursor.getString(1));
                notes.add(cursor.getString(5));
                time.add(cursor.getString(3));
            }
        }
        MyDB.close();

    }
    private void onAddButtonClicked() {
        setVisibility(clicked);
        setAnimation(clicked);
        clicked = !clicked;
    }
    private void setVisibility(boolean clicked){
        if(!clicked){
            edit_btn.setVisibility(View.VISIBLE);
            calender_btn.setVisibility(View.VISIBLE);
        }
        else{
            edit_btn.setVisibility(View.INVISIBLE);
            calender_btn.setVisibility(View.INVISIBLE);
        }
    }

    private void setAnimation(boolean clicked){
        if(!clicked){
            edit_btn.startAnimation(fromBottom);
            calender_btn.startAnimation(fromBottom);
            add_btn.startAnimation(rotateOpen);
        }
        else{
            edit_btn.startAnimation(toBottom);
            calender_btn.startAnimation(toBottom);
            add_btn.startAnimation(rotateClose);
        }
    }
}




