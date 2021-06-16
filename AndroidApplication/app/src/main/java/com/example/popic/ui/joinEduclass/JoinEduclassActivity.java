package com.example.popic.ui.joinEduclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.popic.R;
import com.example.popic.ui.JsonTask;
import com.example.popic.ui.main.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class JoinEduclassActivity extends AppCompatActivity {
    EditText code;
    Button join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_educlass);

        if (getSupportActionBar() != null) getSupportActionBar().setTitle(R.string.app_name);

        code = findViewById(R.id.joineduclass_edittext_code);

        join = findViewById(R.id.joineduclass_button_join);
        join.setOnClickListener(v -> {
            if (requestJoinEduclass(code.getText().toString()) == 0) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private int requestJoinEduclass(String mCode) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JsonTask().execute("joinEduclass/" + mCode).get();
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