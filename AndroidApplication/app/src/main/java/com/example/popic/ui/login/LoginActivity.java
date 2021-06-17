package com.example.popic.ui.login;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.popic.R;
import com.example.popic.ui.JsonTask;
import com.example.popic.ui.main.MainActivity;
import com.example.popic.ui.poem.PoemActivity;
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
    private RadioGroup radioGroup;
    private String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        login = findViewById(R.id.login_button_login);
        register = findViewById(R.id.login_button_register);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);

        login.setOnClickListener(v -> {
            if (requestLogin(username.getText().toString(), password.getText().toString(), state) == 0) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("id", username.getText().toString());
                intent.putExtra("state", state);
                startActivity(intent);
            //}
        });

        register.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });


    }

    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            if(i == R.id.rg_btn1){
                state = "teacher";
            } else if(i == R.id.rg_btn2){
                state = "student";
            } } };

    private int requestLogin(String mUsername, String mPassword, String state) {
        if (mUsername.equals("") || mPassword.equals("")) {
            return -3; // username 또는 Password 가 입력되지 않음.
        }

        try {
            JSONObject jsonObject = new JsonTask().execute(state + "/read/" + mUsername).get();
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
