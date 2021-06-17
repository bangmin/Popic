package com.example.popic.ui.uploadPicture;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.popic.R;
import com.example.popic.ui.ImageUtil;
import com.example.popic.ui.JsonTask;

import android.content.res.Configuration;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

//그림을 그리는 Canvas , Paint 객체를 사용하여 그려보자...

//그림판 앱....

public class UploadPictureActivity extends AppCompatActivity {
    Button pen, eraser, finish;
    int poemId;
    String studentId;
    DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_picture);

        drawView = new DrawView(this);
        ((LinearLayout) findViewById(R.id.uploadpicture_linearlayout_canvas)).addView(drawView);

        pen = findViewById(R.id.uploadPicture_button_pen);
        pen.setOnClickListener(v -> drawView.setType(0));

        eraser = findViewById(R.id.uploadPicture_button_eraser);
        eraser.setOnClickListener(v -> drawView.reset());

        finish = findViewById(R.id.uploadpicture_button_finish);
        finish.setOnClickListener(v -> {
            Bitmap bitmap = drawView.getBitmap();
            if (requestUploadPicture(poemId, studentId, ImageUtil.convert(bitmap)) == 0) {
                finish();
            }
            // int maxLogSize = 1000;
            // String veryLongString = ImageUtil.convert(bitmap);
            //
            // for (int i = 0; i <= veryLongString.length() / maxLogSize; i++) {
            //    int start = i * maxLogSize;
            //    int end = (i + 1) * maxLogSize;
            //    end = Math.min(end, veryLongString.length());
            //    Log.d("test", veryLongString.substring(start, end));
            // }
            // Log.d("test", ImageUtil.convert(bitmap));
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        //현재 디바이스의 방향성을 체크...

        switch (newConfig.orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                Toast.makeText(getApplicationContext(), "가로", Toast.LENGTH_SHORT).show();
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                Toast.makeText(getApplicationContext(), "세로", Toast.LENGTH_SHORT).show();
                return;
        }
    }


    private int requestUploadPicture(int mPoemId, String mStudentId, String base64) {
        Log.d("test", "requestUploadPicture: base64 length : " + base64.length());
        JSONObject jsonObject = null;
        try {
            jsonObject = new JsonTask().execute("picture/create/" + mPoemId + "/" + mStudentId, "image", base64).get();
            if (jsonObject != null) {
                Log.d("test", "requestUploadPicture: jsonObject: " + jsonObject.toString());
            } else {
                Log.d("test", "requestUploadPicture: jsonObject IS NULL");
            }


            if (jsonObject == null) return -404; // 서버 에러

            boolean success = jsonObject.getBoolean("success");
            if (success) {
                return 0;
            } else return -1;
        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
            return -1;
        }
    }
}