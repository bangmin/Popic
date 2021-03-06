package com.example.popic.ui.educlass;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.popic.R;
import com.example.popic.ui.JsonTask;
import com.example.popic.ui.educlass.listview.ListViewAdapter;
import com.example.popic.ui.leaveEduclass.LeaveEduclassActivity;
import com.example.popic.ui.showJoinRequest.ShowJoinRequestActivity;
import com.example.popic.ui.uploadPoem.UploadPoemActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class EduclassActivity extends AppCompatActivity {
    ListViewAdapter listViewAdapter;
    ListView listView;
    int pos_edu;
    private JSONObject jsonObject;

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
        String edu_id = String.valueOf(requestPoemList(getIntent().getStringExtra("edu_id"))); //???
        Log.d("ppp1", edu_id );
        Log.d("ppp2", getIntent().getStringExtra("edu_id"));
        if (requestPoemList(getIntent().getStringExtra("edu_id")) == 0) {
            String poem_name = null;
            String poem_description = null;
            int len = -1;
            try {
                len = jsonObject.getJSONArray("result").length() - 1;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            while(len >= 0){
                try {
                    poem_name = (String) ((JSONObject) jsonObject.getJSONArray("result").get(len)).get("POEM_TITLE");
                    poem_description = (String) ((JSONObject) jsonObject.getJSONArray("result").get(len)).get("POEM_DESCRIPTION");
                    listViewAdapter.addItem(poem_name, poem_description, len , getIntent().getStringExtra("edu_id"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                len = len -1;
            }
        }
        /*Intent intent = getIntent();
        pos_edu = intent.getExtras().getInt("position");
        if (pos_edu == 0) {
            listViewAdapter.addItem("님의 손길", "한용운", pos_edu);
            listViewAdapter.addItem("가는 길", "김소월", pos_edu);
        } else if (pos_edu == 1) {

            listViewAdapter.addItem("가을날 ", "노천명", pos_edu);
            listViewAdapter.addItem("꽃", "이육사", pos_edu);
        }*/

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
                intent2.putExtra("edu_id", getIntent().getExtras().getString("edu_id"));
                Log.d("TAG2-2", getIntent().getExtras().getString("edu_id"));
                startActivity(intent2);
                break;
            case R.id.menu_educlass_showJoinRequest:
                startActivity(new Intent(this, ShowJoinRequestActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private int requestPoemList(String edu_id) {
        jsonObject = null;
        try {
            jsonObject = new JsonTask().execute("poem/read/" + edu_id).get();
            jsonObject.getJSONArray("result").length();
            Log.d("TAG", String.valueOf(jsonObject.getJSONArray("result").length()));
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