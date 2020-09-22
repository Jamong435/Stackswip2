package com.kim9212.stackswip;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailTeamActivity extends AppCompatActivity {

    TextView DTtitle;
    TextView DTarea;
    TextView DTmsg;
    TextView DTemail;
    TextView DTmember;
    TextView DTperiod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_team);

        DTtitle=findViewById(R.id.TD_title);
        DTarea=findViewById(R.id.TD_area);
        DTmsg=findViewById(R.id.TD_msg);
        DTemail=findViewById(R.id.TD_email);
        DTmember=findViewById(R.id.TD_member);
        DTperiod=findViewById(R.id.TD_period);

        Intent intent=getIntent();
        String title=intent.getStringExtra("Title");
        String area=intent.getStringExtra("Area");
        String msg=intent.getStringExtra("Msg");
        String email=intent.getStringExtra("Email");
        String member=intent.getStringExtra("Member");
        String period=intent.getStringExtra("Period");

        DTtitle.setText(title);
        DTarea.setText(area);
        DTmsg.setText(msg);
        DTemail.setText(email);
        DTmember.setText(member);
        DTperiod.setText(period);

    }
}
