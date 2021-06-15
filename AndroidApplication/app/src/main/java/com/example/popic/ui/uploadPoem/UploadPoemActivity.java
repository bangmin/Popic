package com.example.popic.ui.uploadPoem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.popic.R;

public class UploadPoemActivity extends AppCompatActivity {
    Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_poem);
        Intent intent = getIntent();
        int pos = intent.getExtras().getInt("position"); //n번째 educlass
        Log.d("TAG", "onCreate() returned: " + pos);
        finish = findViewById(R.id.uploadPoem_button_finish);
        finish.setOnClickListener(v -> {
            finish();
        });
    }
}