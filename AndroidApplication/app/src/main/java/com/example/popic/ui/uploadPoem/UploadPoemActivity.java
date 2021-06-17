package com.example.popic.ui.uploadPoem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.popic.R;
import com.example.popic.ui.JsonTask;
import com.example.popic.ui.createEduclass.ShowCodeActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class UploadPoemActivity extends AppCompatActivity {
    private EditText poemName, poemContent, poemDescription;
    String edu_id;
    Button finish;
    private JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_poem);
        Intent intent = getIntent();

        poemName = findViewById(R.id.uploadPoem_editText_poemName);
        poemContent = findViewById(R.id.uploadPoem_editText_poemContent);
        poemDescription = findViewById(R.id.uploadPoem_editText_poemDescription);
        finish = findViewById(R.id.uploadPoem_button_finish);

        edu_id = intent.getExtras().getString("edu_id");

        Log.d("TAG2", edu_id);
        finish.setOnClickListener(v -> {
            if (requestUploadPoem(edu_id,  poemName.getText().toString(), poemContent.getText().toString(), poemDescription.getText().toString()) == 0) {
                finish();
            }
        });
    }
    private int requestUploadPoem(String edu_id, String poem_Name, String content, String poem_Description) {
        jsonObject = null;
        Log.d("TAG3", "poem/create/" + edu_id + "?content=" + content + "&title=" + poem_Name + "&description="+poem_Description);
        try {
            Log.d("TAG3", "poem/create/" + edu_id + "?content=" + content + "&title=" + poem_Name + "&description="+poem_Description);
            jsonObject = new JsonTask().execute("poem/create/" + edu_id + "?content=" + content + "&title=" + poem_Name + "&description="+poem_Description).get();
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