package com.example.okhttp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Bundle;
import android.util.Log;

import okhttp3.*;

import java.io.File;
import java.io.IOException;

public class MainActivity extends Activity {

    private final static String TAG = "TestActivity";

    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    enqueue();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void enqueue() {
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    System.out.println(response.code());
                    System.out.println(response.body().string());
                }
            }


        });
    }



}
