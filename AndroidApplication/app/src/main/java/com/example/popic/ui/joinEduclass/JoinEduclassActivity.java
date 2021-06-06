package com.example.popic.ui.joinEduclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.popic.R;
import com.example.popic.ui.main.MainActivity;

public class JoinEduclassActivity extends AppCompatActivity {
    Button join;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_educlass);

        if (getSupportActionBar() != null) getSupportActionBar().setTitle(R.string.app_name);

        join = findViewById(R.id.joineduclass_button_join);
        join.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}