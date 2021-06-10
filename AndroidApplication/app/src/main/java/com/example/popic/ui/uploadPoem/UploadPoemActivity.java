package com.example.popic.ui.uploadPoem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.popic.R;

public class UploadPoemActivity extends AppCompatActivity {
    Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_poem);

        finish = findViewById(R.id.uploadPoem_button_finish);
        finish.setOnClickListener(v -> {
            finish();
        });
    }
}