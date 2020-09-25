package com.kim9212.stackswip;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DeatilQuestionActivity extends AppCompatActivity {

//    보여지는 XML 문서
    TextView QDtitle;
    TextView QDquestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatil_question);

        QDtitle=findViewById(R.id.QD_title);
        QDquestion=findViewById(R.id.QD_question);

//        인테트로 가져온 객체들
        Intent intent=getIntent();
        String title=intent.getStringExtra("Title");
        String question=intent.getStringExtra("Question");

//        가져온 객체들 보여주기
        QDtitle.setText(title);
        QDquestion.setText(question);
    }
}
