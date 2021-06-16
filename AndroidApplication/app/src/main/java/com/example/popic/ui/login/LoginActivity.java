package com.example.popic.ui.login;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.popic.R;
import com.example.popic.ui.JsonTask;
import com.example.popic.ui.main.MainActivity;
import com.example.popic.ui.register.RegisterActivity;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button login;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        login = findViewById(R.id.login_button_login);
        register = findViewById(R.id.login_button_register);

        login.setOnClickListener(v -> {
            //if (requestLogin(username.getText().toString(), password.getText().toString()) == 0) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("id", username.getText().toString());
                startActivity(intent);
            //}
        });

        register.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }

    private int requestLogin(String mUsername, String mPassword) {
        if (mUsername.equals("") || mPassword.equals("")) {
            return -3; // username 또는 Password 가 입력되지 않음.
        }

        try {
            JSONObject jsonObject = new JsonTask().execute("test/" + mUsername).get();
            if (jsonObject == null) return -4; // 서버 에러

            boolean success = jsonObject.getBoolean("success");
            if (success) {
                if (((JSONObject) jsonObject.getJSONArray("result").get(0)).get("TEACHER_PW").equals(mPassword)) {
                    return 0; // 로그인 성공
                }
                else return -1;
            } else {
                return -2; // 패스워드가 틀림
            }
        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
