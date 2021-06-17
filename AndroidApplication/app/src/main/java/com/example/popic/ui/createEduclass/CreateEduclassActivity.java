package com.example.popic.ui.createEduclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.popic.R;
import com.example.popic.ui.JsonTask;
import com.example.popic.ui.main.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class CreateEduclassActivity extends AppCompatActivity {
    private EditText educlassName, educlassDescription;
    private Button createEduclass;
    private JSONObject jsonObject;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_educlass);

        educlassName = findViewById(R.id.createEduclass_edittext_educlassName);
        educlassDescription = findViewById(R.id.createEduclass_edittext_description);
        Intent intent = getIntent();
        id = intent.getExtras().getString("id");

        createEduclass = findViewById(R.id.createEduclass_button_createEduclass);
        createEduclass.setOnClickListener(v -> {
            if (requestCreateEduclass(id, educlassName.getText().toString(), educlassDescription.getText().toString()) == 0) {
                Intent intent2 = new Intent(this, ShowCodeActivity.class);
                try {
                    intent2.putExtra("code", jsonObject.getString("code"));
                    Log.d("code", "onCreate: ");
                    startActivity(new Intent(this, ShowCodeActivity.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
    }

    private int requestCreateEduclass(String id, String edu_Name, String mDescription) {
        jsonObject = null;
        try {
            Log.d("TAG", "educlass/create?teacher_id=" + id + "&name=" + edu_Name + "&description=" + mDescription + "&icon=[]");
            jsonObject = new JsonTask().execute("educlass/create?teacher_id=" + id + "&name=" + edu_Name + "&description=" + mDescription + "&icon=[]").get();
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