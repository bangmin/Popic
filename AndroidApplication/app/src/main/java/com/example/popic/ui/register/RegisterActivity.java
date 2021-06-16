package com.example.popic.ui.register;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.popic.R;
import com.example.popic.ui.JsonTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class RegisterActivity extends AppCompatActivity {
    EditText id, password, passwordConfirm;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        id = findViewById(R.id.register_edittext_id);
        password = findViewById(R.id.register_edittext_password);
        passwordConfirm = findViewById(R.id.register_edittext_passwordconfirm);

        register = findViewById(R.id.register_button_register);

        register.setOnClickListener(v -> {
            if (!password.getText().toString().equals(passwordConfirm.getText().toString())) {
                if (requestRegister(id.getText().toString(), password.getText().toString()) == 0) {
                    finish();
                }
            }
        });
    }

    private int requestRegister(String mId, String mPassword) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JsonTask().execute("test2/" + mId + "/" + mPassword).get();
            if (jsonObject == null) return -4; // 서버 에러

            boolean success = jsonObject.getBoolean("success");
            if (success) return 0;
            else return -1;
        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
            return -1;
        }
    }
}