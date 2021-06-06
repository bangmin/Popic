package com.example.popic.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.popic.R;
import com.example.popic.ui.changeUserInformation.ChangeUserInformationActivity;
import com.example.popic.ui.createEduclass.CreateEduclassActivity;
import com.example.popic.ui.joinEduclass.JoinEduclassActivity;
import com.example.popic.ui.main.listview.ListViewAdapter;

public class MainActivity extends AppCompatActivity {
    Button createEduclass, joinEduclass;
    ListView listView;
    ListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createEduclass = findViewById(R.id.main_button_createEduclass);
        createEduclass.setOnClickListener(v -> {
            startActivity(new Intent(this, CreateEduclassActivity.class));
        });

        joinEduclass = findViewById(R.id.main_button_joinEduclass);
        joinEduclass.setOnClickListener(v -> {
            startActivity(new Intent(this, JoinEduclassActivity.class));
        });


        if (getSupportActionBar() != null) getSupportActionBar().setTitle(R.string.app_name);


        listViewAdapter = new ListViewAdapter();
        listView = findViewById(R.id.main_listview_educlass);

        TextView textView = new TextView(this);
        textView.setText("에듀클래스 목록");
        listView.addHeaderView(textView);

        listView.setAdapter(listViewAdapter);
        
        listViewAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.seoul), "테스트 1", "테스트테스트테스트테스트테스트");
        listViewAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background), "테스트 2", "트테스트테스트테스트");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast toast = Toast.makeText(getApplicationContext(),"", Toast.LENGTH_LONG);

        switch(item.getItemId())
        {
            case R.id.menu_user_logout:
                toast.setText("로그아웃 했습니다.");
                toast.show();
                finish();
                break;
            case R.id.menu_user_deleteAccount:
                toast.setText("회원탈퇴했습니다.");
                toast.show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}