package com.kim9212.stackswip;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Intent intent= getIntent();
        String name= intent.getStringExtra("name");
        String msg= intent.getStringExtra("msg");

        getSupportActionBar().setTitle(name);
        getSupportActionBar().setSubtitle(msg);
    }
}
