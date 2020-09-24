package com.kim9212.stackswip;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DeatilQuestionActivity extends AppCompatActivity {

    TextView QDtitle;
    TextView QDquestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatil_question);
        QDtitle=findViewById(R.id.QD_title);
        QDquestion=findViewById(R.id.QD_question);
        Intent intent=getIntent();
        String title=intent.getStringExtra("Title");
        String question=intent.getStringExtra("Question");
        QDtitle.setText(title);
        QDquestion.setText(question);
    }
}
