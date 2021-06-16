package com.example.popic.ui.createEduclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.popic.R;
import com.example.popic.ui.JsonTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class CreateEduclassActivity extends AppCompatActivity {
    private EditText educlassName, educlassDescription;
    private Button createEduclass;
    private JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_educlass);

        educlassName = findViewById(R.id.createEduclass_edittext_educlassName);
        educlassDescription = findViewById(R.id.createEduclass_edittext_description);

        createEduclass = findViewById(R.id.createEduclass_button_createEduclass);
        createEduclass.setOnClickListener(v -> {
            if (requestCreateEduclass(educlassName.getText().toString(), educlassDescription.getText().toString()) == 0) {
                Intent intent = new Intent(this, ShowCodeActivity.class);
                try {
                    intent.putExtra("code", jsonObject.getString("code"));
                    startActivity(new Intent(this, ShowCodeActivity.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
    }

    private int requestCreateEduclass(String mName, String mDescription) {
        jsonObject = null;
        try {
            jsonObject = new JsonTask().execute("createEduclass/" + mName + "/" + mDescription).get();
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