package com.example.popic.ui.createEduclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.popic.R;
import com.example.popic.ui.main.MainActivity;

import java.util.Random;

public class ShowCodeActivity extends AppCompatActivity {
    Button finish;
    TextView code;
    int max_num_value = 9;
    int min_num_value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_code);

        /*
        Random rnd = new Random();
        final String random_name_1 = String.valueOf((char) ((int) (rnd.nextInt(26))+97)); //영어 소문자 a-z
        final String random_name_2 = String.valueOf((char) ((int) (rnd.nextInt(26))+65)); //영어 대문자 A-Z
        int randomNum1 = rnd.nextInt(max_num_value - min_num_value + 1) + min_num_value;
        int randomNum2 = rnd.nextInt(max_num_value - min_num_value + 1) + min_num_value;
        int randomNum3 = rnd.nextInt(max_num_value - min_num_value + 1) + min_num_value;
        int randomNum4 = rnd.nextInt(max_num_value - min_num_value + 1) + min_num_value;
        code = findViewById(R.id.showCode_textview_code);
        code.setText(String.valueOf(random_name_1+random_name_2+randomNum1+randomNum2+randomNum3+randomNum4));
         */

        code = findViewById(R.id.showCode_textview_code);
        code.setText(getIntent().getStringExtra("code"));

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