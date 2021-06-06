package com.example.popic.ui.uploadPicture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.popic.R;

public class UploadPictureActivity extends AppCompatActivity {
    Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_picture);

        finish = findViewById(R.id.uploadpicture_button_finish);
        finish.setOnClickListener(v -> finish());
    }

    private class GraphicView extends View {
        public GraphicView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
        }
    }
}