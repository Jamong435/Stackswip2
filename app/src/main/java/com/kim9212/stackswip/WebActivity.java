package com.kim9212.stackswip;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);


        // 버튼 클릭시 사용되는 리스너를 구현합니다.
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView_main_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        // 어떤 메뉴 아이템이 터치되었는지 확인합니다.
                        switch (item.getItemId()) {
                            case R.id.menuitem_bottombar_up:
                                Intent intent = new Intent(WebActivity.this,HomeActivity.class);
                                startActivity(intent);
                                return true;

                            case R.id.menuitem_bottombar_down:
                                Intent intent1 = new Intent(WebActivity.this,QuestionActivity.class);
                                startActivity(intent1);
                                return true;

                            case R.id.menuitem_bottombar_search:
                                Intent intent3 = new Intent(WebActivity.this,WebActivity.class);
                                startActivity(intent3);
                                return true;

                            case R.id.menuitem_bottombar_ses:
                                Intent intent4 = new Intent(WebActivity.this,TeamActivity.class);
                                startActivity(intent4);
                                return true;
                        }
                        return false;
                    }
                });
    }
}