package com.example.popic.ui.educlass;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.popic.R;
import com.example.popic.ui.educlass.listview.ListViewAdapter;
import com.example.popic.ui.leaveEduclass.LeaveEduclassActivity;
import com.example.popic.ui.showJoinRequest.ShowJoinRequestActivity;
import com.example.popic.ui.uploadPoem.UploadPoemActivity;

public class EduclassActivity extends AppCompatActivity {
    ListViewAdapter listViewAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educlass);

        if (getSupportActionBar() != null) getSupportActionBar().setTitle(R.string.app_name);

        listViewAdapter = new ListViewAdapter();
        listView = findViewById(R.id.educlass_listview_poem);

        TextView textView = new TextView(this);
        textView.setText("시 목록");
        listView.addHeaderView(textView);

        listView.setAdapter(listViewAdapter);

        listViewAdapter.addItem("시 1", "테스트테스트테스트테스트테스트");
        listViewAdapter.addItem("시 2", "트테스트테스트테스트");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_educlass, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_educlass_leaveEduclass:
                startActivity(new Intent(this, LeaveEduclassActivity.class));
                break;
            case R.id.menu_educlass_uploadPoem:
                startActivity(new Intent(this, UploadPoemActivity.class));
                break;
            case R.id.menu_educlass_showJoinRequest:
                startActivity(new Intent(this, ShowJoinRequestActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}