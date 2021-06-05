package com.example.popic.ui.createEduclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import com.example.popic.R;

public class CreateEduclassActivity extends AppCompatActivity {
    Button createEduclass;
    TextView code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_educlass);

        code = findViewById(R.id.createEduclass_textview_code);

        createEduclass = findViewById(R.id.createEduclass_button_createEduclass);
        createEduclass.setOnClickListener(v -> {
            code.setText("<랜덤한 생성 코드를 보여주기>\n지금은 3초 후에 닫습니다.");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 3000);
        });
    }
}