package com.example.fakarni;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taskati.R;

public class LogIn extends AppCompatActivity {
    private TextView username,password;
    Button logIn;
    Database db;
    static int id;
    static String name;
    Cursor idCursor,nameCursor;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //Edit Text
        username =findViewById(R.id.userNameText);
        password = findViewById(R.id.passwordText);

        //TextView fp = findViewById(R.id.Forget);

        //Button
        logIn = findViewById(R.id.Login);

        //Text view
        TextView signUpBtn = findViewById(R.id.textSignUP);
        db= new Database(LogIn.this);



        logIn.setOnClickListener(view -> {
            if(db.checkU_P(username.getText().toString(),password.getText().toString())) {
                idCursor= db.getID(username.getText().toString());
                nameCursor = db.getName(username.getText().toString());

                if(idCursor != null) {
                    idCursor.moveToFirst();
                    nameCursor.moveToFirst();
                }
                assert idCursor != null;
                assert nameCursor != null;
                id = idCursor.getInt(idCursor.getColumnIndex("id"));
                name = nameCursor.getString(nameCursor.getColumnIndex("fName"));
                Intent intent = new Intent(LogIn.this, MainActivity.class);
                startActivity(intent);

            }

            else {
                Toast.makeText(LogIn.this,"Wrong username or password",Toast.LENGTH_SHORT).show();
            }
        });

        signUpBtn.setOnClickListener(view -> {
            Intent signup=new Intent(LogIn.this,SignUp.class);
            startActivity(signup);
        });
    }
}