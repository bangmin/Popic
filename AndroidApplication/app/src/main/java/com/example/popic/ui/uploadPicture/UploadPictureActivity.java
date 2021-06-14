package com.example.popic.ui.uploadPicture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.popic.R;
import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Toast;

//그림을 그리는 Canvas , Paint 객체를 사용하여 그려보자...

//그림판 앱....

public class UploadPictureActivity extends AppCompatActivity {
    Button finish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_picture);

        finish = findViewById(R.id.uploadpicture_button_finish);
        finish.setOnClickListener(v -> finish());

        //디바이스 회전시 값 유지를 하기위한 코드
        Resources r = Resources.getSystem();
        Configuration config = r.getConfiguration();
        onConfigurationChanged(config);

        //onConfigurationChanged
        //오버라이드를 했을경우 회전시마다 onCreate메소드를 호출하므로
        // 재호출 하지 않도록 manifest에 속성두가지를 추가해주면된다..
//        android:configChanges="orientation|keyboardHidden|screenSize"
//        android:windowSoftInputMode="stateHidden|adjustPan"
        //actitvy가 여러개라면 모든 액티비티에 속성을 추가해주는것이 좋다...

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        //현재 디바이스의 방향성을 체크...

        switch(newConfig.orientation){
            case Configuration.ORIENTATION_LANDSCAPE:
                Toast.makeText(getApplicationContext(),"가로",Toast.LENGTH_SHORT).show();
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                Toast.makeText(getApplicationContext(),"세로",Toast.LENGTH_SHORT).show();
                return;
        }

    }

}