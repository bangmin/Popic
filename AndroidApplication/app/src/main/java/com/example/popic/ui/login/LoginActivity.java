package com.example.popic.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.popic.R;
import com.example.popic.ui.main.MainActivity;
import com.example.popic.ui.register.RegisterActivity;
import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {
    MaterialButton login, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login_button_login);
        register = findViewById(R.id.login_button_register);

        login.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
        });


        register.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }
}