package com.example.popic.ui.picture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.example.popic.R;
import com.example.popic.ui.picture.gridview.GridViewAdapter;

public class PictureActivity extends AppCompatActivity {
    GridView picture;
    GridViewAdapter gridViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        gridViewAdapter = new GridViewAdapter();
        picture = findViewById(R.id.picture_gridview_picture);

        picture.setAdapter(gridViewAdapter);

        gridViewAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.seoul), "김철수");
        gridViewAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background), "이영희");
        gridViewAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background), "이영희");
        gridViewAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background), "이영희");
        gridViewAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background), "이영희");
    }
}