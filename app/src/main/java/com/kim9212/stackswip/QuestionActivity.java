package com.kim9212.stackswip;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuestionActivity extends AppCompatActivity {
    //질문 리사이클러뷰
    RecyclerView recyclerView;
    ArrayList<QuestionItem> questionItems=new ArrayList<>();
    QuestionAdapter questionAdapter;

    //질문 하기 위해 플로팅버튼 만들고 온클릭속성
    FloatingActionButton flBtn;

    ImageView swipGesture;
    int n=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        recyclerView=findViewById(R.id.que_Recycler);
        questionAdapter=new QuestionAdapter(this,questionItems);
        recyclerView.setAdapter(questionAdapter);

//        제스쳐 대용
        swipGesture=findViewById(R.id.swipeGesture);

// 버튼 클릭시 사용되는 리스너를 구현합니다.
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView_main_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        // 어떤 메뉴 아이템이 터치되었는지 확인합니다.
                        switch (item.getItemId()) {
                            case R.id.menuitem_bottombar_up:
                                Intent intent = new Intent(QuestionActivity.this,HomeActivity.class);
                                startActivity(intent);
                                return true;

                            case R.id.menuitem_bottombar_down:
                                Intent intent1 = new Intent(QuestionActivity.this,QuestionActivity.class);
                                startActivity(intent1);
                                return true;

                            case R.id.menuitem_bottombar_search:
                                Intent intent3 = new Intent(QuestionActivity.this,HomeActivity.class);
                                startActivity(intent3);
                                return true;

                            case R.id.menuitem_bottombar_ses:
                                Intent intent4 = new Intent(QuestionActivity.this,HomeActivity.class);
                                startActivity(intent4);
                                return true;
                        }
                        return false;
                    }
                });


    }

    public void AddQuestion(View view) {
        Intent intent= new Intent(this, QuestionUpActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    void loadData(){
        Retrofit retrofit= RetrofitHelper.getInstance();
        RetrofitService retrofitService=retrofit.create(RetrofitService.class);
        Call<ArrayList<QuestionItem>> call=retrofitService.loadDataFromQuestion();



        call.enqueue(new Callback<ArrayList<QuestionItem>>() {
            @Override
            public void onResponse(Call<ArrayList<QuestionItem>> call, Response<ArrayList<QuestionItem>> response) {
                ArrayList<QuestionItem> items=response.body();



                swipGesture.setOnTouchListener(new OnSwipeTouchListener(QuestionActivity.this){
                    public void onSwipeRight() {

                    }
                    public void onSwipeLeft() {
                        questionItems.clear();
                        questionAdapter.notifyDataSetChanged();
                        questionItems.add( items.get(n));
                        questionAdapter.notifyItemInserted(n);
                        n++;
                    }
                });
            }
            @Override
            public void onFailure(Call<ArrayList<QuestionItem>> call, Throwable t) {
            }
        });


    }// 로드 데이터


    public class OnSwipeTouchListener implements View.OnTouchListener {

        private final GestureDetector gestureDetector;

        public OnSwipeTouchListener (Context context){
            gestureDetector = new GestureDetector(context, new GestureListener());
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                boolean result = false;
                try {
                    float diffY = e2.getY() - e1.getY();
                    float diffX = e2.getX() - e1.getX();
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeRight();
                            } else {
                                onSwipeLeft();
                            }
                        }
                        result = true;
                    }
                    else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeBottom();
                        } else {
                            onSwipeTop();
                        }
                    }
                    result = true;

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return result;
            }
        }

        public void onSwipeRight() {
        }

        public void onSwipeLeft() {
        }

        public void onSwipeTop() {
        }

        public void onSwipeBottom() {
        }
    }


}
