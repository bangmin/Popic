package com.example.popic.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import com.example.popic.ui.main.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonTask extends AsyncTask<String, String, JSONObject> {
    @Override
    protected JSONObject doInBackground(String... strings) {
        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader = null;

        try {
            URL url = new URL("http://34.64.149.59:3000/" + strings[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();

            // POST로 이미지 데이터 전송
            if (strings.length > 1) {
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestProperty("content-type", "application/x-www-form-urlencoded");

                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 1; i < strings.length; i += 2) {
                    stringBuilder.append(strings[i]).append("=").append(strings[i + 1]);
                }

                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(bufferedOutputStream, "UTF-8"));
                bufferedWriter.write(stringBuilder.toString());
                bufferedWriter.flush();
                bufferedWriter.close();
                bufferedOutputStream.close();
            }

            httpURLConnection.connect();

            InputStream stream = httpURLConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(stream));

            StringBuilder stringBuffer = new StringBuilder();
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line).append("\n");
            }

            if (stringBuffer.length() == 0) return null;

            return new JSONObject(stringBuffer.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}