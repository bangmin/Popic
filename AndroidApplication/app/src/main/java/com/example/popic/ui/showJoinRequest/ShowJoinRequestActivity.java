package com.example.popic.ui.showJoinRequest;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.popic.R;
import com.example.popic.ui.showJoinRequest.listview.ListViewAdapter;

public class ShowJoinRequestActivity extends AppCompatActivity {
    com.example.popic.ui.showJoinRequest.listview.ListViewAdapter listViewAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_join_request);

        if (getSupportActionBar() != null) getSupportActionBar().setTitle(R.string.app_name);

        listViewAdapter = new ListViewAdapter();
        listView = findViewById(R.id.showJoinRequest_listview);

        TextView textView = new TextView(this);
        textView.setText("가입 신청 목록");
        listView.addHeaderView(textView);

        listView.setAdapter(listViewAdapter);

        listViewAdapter.addItem("김철수");
        listViewAdapter.addItem("이영희");
    }
}