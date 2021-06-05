package com.example.popic.ui.changeUserInformation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.popic.R;

public class ChangeUserInformationActivity extends AppCompatActivity {
    Button changeInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_information);

        changeInformation = findViewById(R.id.changeUserInformation_button_changeInformation);

        changeInformation.setOnClickListener(v -> {
            finish();
        });
    }
}