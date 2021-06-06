package com.example.popic.ui.createEduclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import com.example.popic.R;

public class CreateEduclassActivity extends AppCompatActivity {
    Button createEduclass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_educlass);

        createEduclass = findViewById(R.id.createEduclass_button_createEduclass);
        createEduclass.setOnClickListener(v -> {
            startActivity(new Intent(this, ShowCodeActivity.class));
            finish();
        });
    }
}