package com.example.popic.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.popic.R;
import com.example.popic.ui.JsonTask;
import com.example.popic.ui.createEduclass.CreateEduclassActivity;
import com.example.popic.ui.joinEduclass.JoinEduclassActivity;
import com.example.popic.ui.main.listview.ListViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab, createEduclass, joinEduclass;
    private TextView text1, text2;
    ListView listView;
    ListViewAdapter listViewAdapter;
    String state, id;
    private JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        createEduclass = findViewById(R.id.main_button_createEduclass);
        joinEduclass = findViewById(R.id.main_button_joinEduclass);

        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);

        fab.setOnClickListener(v -> {
            anim();
            Toast.makeText(this, "Button", Toast.LENGTH_SHORT).show();
        });
        createEduclass.setOnClickListener(v -> {
            anim();
            String id = getIntent().getExtras().getString("id");
            Toast.makeText(this, "Button1", Toast.LENGTH_SHORT).show();
            Intent intent2 = new Intent(this, CreateEduclassActivity.class);
            intent2.putExtra("id", id);
            startActivity(intent2);
        });


        joinEduclass.setOnClickListener(v -> {
            anim();
            Toast.makeText(this, "Button2", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, JoinEduclassActivity.class));
        });


        if (getSupportActionBar() != null) getSupportActionBar().setTitle(R.string.app_name);


        listViewAdapter = new ListViewAdapter();
        listView = findViewById(R.id.main_listview_educlass);

        TextView textView = new TextView(this);
        textView.setText("에듀클래스 목록");
        listView.addHeaderView(textView);

        listView.setAdapter(listViewAdapter);

        if (requestEduclassList(getIntent().getStringExtra("id")) == 0) {//??
            String edu_name = null;
            String edu_description = null;
            int edu_id = -1;
            int len = -1;
            try {
                len = jsonObject.getJSONArray("result").length() - 1;
                Log.d("길이", String.valueOf(len));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            while(len >= 0){
                try {
                    edu_name = (String) ((JSONObject) jsonObject.getJSONArray("result").get(len)).get("EDUCLASS_NAME");
                    edu_description = (String) ((JSONObject) jsonObject.getJSONArray("result").get(len)).get("EDUCLASS_DESCRIPTION");
                    edu_id = (int) ((JSONObject) jsonObject.getJSONArray("result").get(len)).get("EDUCLASS_ID");
                    String edu_id2 = String.valueOf (((JSONObject) jsonObject.getJSONArray("result").get(len)).get("EDUCLASS_ID"));
                    listViewAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.seoul), edu_name, edu_description, edu_id2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                len = len -1;
            }
        };
    }


    public void anim() {
        if (isFabOpen) {
            createEduclass.startAnimation(fab_close);
            joinEduclass.startAnimation(fab_close);
            text1.startAnimation(fab_close);
            text2.startAnimation(fab_close);
            createEduclass.setClickable(false);
            joinEduclass.setClickable(false);
            isFabOpen = false;
        } else {
            createEduclass.startAnimation(fab_open);
            joinEduclass.startAnimation(fab_open);
            text1.startAnimation(fab_open);
            text2.startAnimation(fab_open);
            createEduclass.setClickable(true);
            joinEduclass.setClickable(true);
            isFabOpen = true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG);

        switch (item.getItemId()) {
            case R.id.menu_main_logout:
                toast.setText("로그아웃 했습니다.");
                toast.show();
                finish();
                break;
            case R.id.menu_main_deleteAccount:
                Intent intent = getIntent();
                id = intent.getExtras().getString("id");
                state = intent.getExtras().getString("state"); //이거 위치가 중요. OnCreate에 두니 작동 x

                if (requestSignout(id, state) == 0) {
                    toast.setText("회원탈퇴했습니다.");
                    toast.show();
                    finish();
                    break;
                }

        }
        return super.onOptionsItemSelected(item);
    }


    private int requestEduclassList(String mId) {
        jsonObject = null;
        try {
            Log.w("name22", mId );
            jsonObject = new JsonTask().execute("educlass/read/" + mId).get();
            jsonObject.getJSONArray("result").length();
            Log.d("TAG", String.valueOf(jsonObject.getJSONArray("result").length()));
            Log.d("TAG", "requestEduclassList() called with: mId = [" + ((JSONObject) jsonObject.getJSONArray("result").get(2)).get("EDUCLASS_NAME") + "]");
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

    private int requestSignout(String mUsername, String state) {
        try {
            JSONObject jsonObject = new JsonTask().execute(state + "/delete/" + mUsername).get();
            if (jsonObject == null) return -4; // 서버 에러

            boolean success = jsonObject.getBoolean("success");
            if (success) {
                return 0; // 탈퇴 성공
            } else {
                return -2; // 탈퇴 실패
            }
        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
            return -1;
        }
    }
}