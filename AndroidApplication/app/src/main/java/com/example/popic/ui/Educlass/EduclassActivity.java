package com.example.popic.ui.educlass;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

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
    int pos_edu;
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
        Intent intent = getIntent();
        pos_edu = intent.getExtras().getInt("position");
        if(pos_edu == 0){
            listViewAdapter.addItem("님의 손길", "한용운", pos_edu);
            listViewAdapter.addItem("가는 길", "김소월", pos_edu);
        }else if(pos_edu == 1){

            listViewAdapter.addItem("가을날 ", "노천명", pos_edu);
            listViewAdapter.addItem("꽃", "이육사", pos_edu);
        }

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
                Intent intent2 = new Intent(this, UploadPoemActivity.class);
                intent2.putExtra("position", pos_edu);
                startActivity(intent2);
                break;
            case R.id.menu_educlass_showJoinRequest:
                startActivity(new Intent(this, ShowJoinRequestActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}