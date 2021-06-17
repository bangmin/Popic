package com.example.popic.ui.register;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.popic.R;
import com.example.popic.ui.JsonTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class RegisterActivity extends AppCompatActivity {
    EditText id, password, passwordConfirm;
    Button register;
    private RadioGroup radioGroup;
    String state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        id = findViewById(R.id.register_edittext_id);
        password = findViewById(R.id.register_edittext_password);
        passwordConfirm = findViewById(R.id.register_edittext_passwordconfirm);

        register = findViewById(R.id.register_button_register);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);

        register.setOnClickListener(v -> {
            if (password.getText().toString().equals(passwordConfirm.getText().toString())) {
                if (requestRegister(id.getText().toString(), password.getText().toString(), state) == 0) {
                    finish();
                }
            }
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

    private int requestRegister(String mId, String mPassword, String state) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JsonTask().execute(state +"/"+ "create/" + mId + "/" + mPassword).get();
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