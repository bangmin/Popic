package com.example.popic.ui.createEduclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.popic.R;
import com.example.popic.ui.main.MainActivity;

public class ShowCodeActivity extends AppCompatActivity {
    Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_code);

        finish = findViewById(R.id.showCode_button_finish);
        finish.setOnClickListener(v -> {
            // MainActivity 가 스택에 있으면 그 위의 모든 액티비티를 종료시키고 새로 생성한 액티비티로 교체한다.
            // https://hashcode.co.kr/questions/441
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}