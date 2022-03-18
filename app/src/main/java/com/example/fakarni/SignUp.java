package com.example.fakarni;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taskati.R;


public class SignUp extends AppCompatActivity {

    Database DATABASE = new Database(SignUp.this);
    EditText fName, lName, userName, password, rePassword;
    TextView text_male, text_female;
    Button register,login;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switchGender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fName = findViewById(R.id.text_fName);
        lName = findViewById(R.id.text_lName);
        userName = findViewById(R.id.text_userName);
        password = findViewById(R.id.text_password);
        rePassword = findViewById(R.id.text_rePassword);
        switchGender = findViewById(R.id.switch_gender);
        text_male = findViewById(R.id.textView_male);
        text_female = findViewById(R.id.textView_female);
        register = findViewById(R.id.button_add);
        login = findViewById(R.id.SignIn);

        switchGender.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                text_female.setTextSize(24);
                text_male.setTextSize(12);

            } else {
                text_male.setTextSize(26);
                text_female.setTextSize(12);

            }
        });

        login.setOnClickListener(view -> {
            Intent intent = new Intent(SignUp.this, LogIn.class);
            startActivity(intent);
        });

        register.setOnClickListener(view -> {
            if (fName.getText().toString().isEmpty()) {
                Toast.makeText(SignUp.this, "Please enter your first name", Toast.LENGTH_LONG).show();
            } else if (lName.getText().toString().isEmpty()) {
                Toast.makeText(SignUp.this, "Please enter your last name", Toast.LENGTH_LONG).show();
            } else if (userName.getText().toString().isEmpty()) {
                Toast.makeText(SignUp.this, "Please enter user name", Toast.LENGTH_LONG).show();
            } else if (DATABASE.userNameCheck(userName.getText().toString())) {
                Toast.makeText(SignUp.this, "This username is already exists", Toast.LENGTH_SHORT).show();
            } else if (password.getText().toString().isEmpty()) {
                Toast.makeText(SignUp.this, "Please enter password", Toast.LENGTH_LONG).show();
            } else if (rePassword.getText().toString().isEmpty()) {
                Toast.makeText(SignUp.this, "Please enter Re-Password", Toast.LENGTH_LONG).show();
            } else if (!password.getText().toString().equals(rePassword.getText().toString())) {
                Toast.makeText(SignUp.this, "Password does not match", Toast.LENGTH_LONG).show();
            } else {
                if (!switchGender.isChecked()) {
                    DATABASE.insertUsersData(fName.getText().toString(), lName.getText().toString(), userName.getText().toString(),
                            password.getText().toString(), "male");
                } else {
                    DATABASE.insertUsersData(fName.getText().toString(), lName.getText().toString(), userName.getText().toString(),
                            password.getText().toString(), "female");
                }
                Intent intent = new Intent(SignUp.this, LogIn.class);
                startActivity(intent);
            }
        });
    }
}

