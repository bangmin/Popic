package com.example.popic.ui.poem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.popic.R;
import com.example.popic.ui.modifyPoem.ModifyPoemActivity;
import com.example.popic.ui.picture.PictureActivity;
import com.example.popic.ui.uploadPicture.UploadPictureActivity;

public class PoemActivity extends AppCompatActivity {
    Button modifyPoem, uploadPicture, seePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem);

        modifyPoem = findViewById(R.id.poem_button_modifypoem);
        modifyPoem.setOnClickListener(v -> {
            startActivity(new Intent(this, ModifyPoemActivity.class));
        });

        uploadPicture = findViewById(R.id.poem_button_uploadpicture);
        uploadPicture.setOnClickListener(v -> {
            startActivity(new Intent(this, UploadPictureActivity.class));
        });

        seePicture = findViewById(R.id.poem_button_seepicture);
        seePicture.setOnClickListener(v -> {
            startActivity(new Intent(this, PictureActivity.class));
        });
    }
}