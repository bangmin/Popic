package com.example.popic.ui.modifyPoem;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.popic.R;

public class ModifyPoemActivity extends AppCompatActivity {
    Button finish, deletePoem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_poem);

        finish = findViewById(R.id.modifyPoem_button_finish);
        finish.setOnClickListener(v -> finish());

        deletePoem = findViewById(R.id.modifyPoem_button_deletePoem);
        deletePoem.setOnClickListener(v -> finish());
    }
}