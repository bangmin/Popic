package com.example.popic.ui.educlass;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.popic.R;
import com.example.popic.ui.educlass.listview.ListViewAdapter;

public class Educlass extends AppCompatActivity {
    ListViewAdapter listViewAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educlass);


        listViewAdapter = new ListViewAdapter();
        listView = findViewById(R.id.educlass_listview_poem);

        TextView textView = new TextView(this);
        textView.setText("시 목록");
        listView.addHeaderView(textView);

        listView.setAdapter(listViewAdapter);

        listViewAdapter.addItem("시 1", "테스트테스트테스트테스트테스트");
        listViewAdapter.addItem("시 2", "트테스트테스트테스트");
    }
}