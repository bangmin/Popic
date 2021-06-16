package com.example.popic.ui;

import android.content.Intent;
import android.os.AsyncTask;

import com.example.popic.ui.main.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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