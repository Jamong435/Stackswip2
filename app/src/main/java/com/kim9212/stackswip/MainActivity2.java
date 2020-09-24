package com.kim9212.stackswip;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MainActivity2 extends AppCompatActivity {


    private TextView tv_result; // 닉네임 text
    private ImageView iv_profile; // 이미지 뷰
    TextView tv;
    ImageView iv;
    Button btn,btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv = findViewById(R.id.tv);
        iv = findViewById(R.id.iv);
        btn = findViewById(R.id.btn);
        btn = findViewById(R.id.btn1);
        btn = findViewById(R.id.btn2);
        Animation ani = AnimationUtils.loadAnimation(this, R.anim.appear_logo);

        iv.startAnimation(ani);


        Intent intent = getIntent();
        String nickName = intent.getStringExtra("nickName"); // MainActivity로 부터 닉네임 전달받음.
        String photoUrl = intent.getStringExtra("photoUrl"); // MainActivity로 부터 프로필사진 Url 전달받음.
        tv_result = findViewById(R.id.tv_result);
        tv_result.setText(nickName); // 닉네임 text를 텍스트 뷰에 세팅.
        iv_profile = findViewById(R.id.iv_profile);
        Glide.with(this).load(photoUrl).into(iv_profile); // 프로필 url을 이미지 뷰에 세팅.
        ani.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                findViewById(R.id.btn).setVisibility(View.VISIBLE);
                findViewById(R.id.btn1).setVisibility(View.VISIBLE);
                findViewById(R.id.btn2).setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        tv.startAnimation(AnimationUtils.loadAnimation(this, R.anim.appear));


    }

    public void clickBtn(View view) {
        startActivity(new Intent(this, MainActivity2.class));
    }

    public void clickBtn1(View view) {
    }

    public void clickBtn2(View view) {
    }
}