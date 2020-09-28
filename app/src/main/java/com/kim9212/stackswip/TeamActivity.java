package com.kim9212.stackswip;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TeamActivity extends AppCompatActivity {
    //질문 리사이클러뷰
    RecyclerView recyclerView;
    ArrayList<TeamItem> teamItems=new ArrayList<>();
    TeamAdapter teamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        recyclerView=findViewById(R.id.team_Recycler);
        teamAdapter=new TeamAdapter(this,teamItems);
        recyclerView.setAdapter(teamAdapter);


        // 버튼 클릭시 사용되는 리스너를 구현합니다.
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView_main_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        // 어떤 메뉴 아이템이 터치되었는지 확인합니다.
                        switch (item.getItemId()) {
                            case R.id.menuitem_bottombar_up:
                                Intent intent = new Intent(TeamActivity.this,HomeActivity.class);
                                startActivity(intent);
                                return true;

                            case R.id.menuitem_bottombar_down:
                                Intent intent1 = new Intent(TeamActivity.this,QuestionActivity.class);
                                startActivity(intent1);
                                return true;

                            case R.id.menuitem_bottombar_search:
                                Intent intent3 = new Intent(TeamActivity.this,WebActivity.class);
                                startActivity(intent3);
                                return true;

                            case R.id.menuitem_bottombar_ses:
                                Intent intent4 = new Intent(TeamActivity.this,TeamActivity.class);
                                startActivity(intent4);
                                return true;
                        }
                        return false;
                    }
                });

//        teamItems.add(new TeamItem("안드로이드 해볼사람","안드로이드","초보자인데 같이 한번 만들어보사람 구해봅니다","3명","dd@dd","2주"));
//        teamItems.add(new TeamItem("웹앱 해볼사람","웹","초보자인데 같이 한번 만들어보사람 구해봅니다","3명","dd@dd","2주"));
//        teamItems.add(new TeamItem("하이브리드 해볼사람","react-native","초보자인데 같이 한번 만들어보사람 구해봅니다","3명","dd@dd","2주"));


    }

    public void AddTeam(View view) {
        Intent intent=new Intent(this, TeamUpActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    void loadData(){
        Retrofit retrofit=RetrofitHelper.getInstance();
        RetrofitService retrofitService=retrofit.create(RetrofitService.class);
        Call<ArrayList<TeamItem>> call= retrofitService.loadDataFromTeam();
        call.enqueue(new Callback<ArrayList<TeamItem>>() {
            @Override
            public void onResponse(Call<ArrayList<TeamItem>> call, Response<ArrayList<TeamItem>> response) {
                ArrayList<TeamItem> items=response.body();
                teamItems.clear();
                teamAdapter.notifyDataSetChanged();

                for (TeamItem item:items){
                    teamItems.add(0,item);
                    teamAdapter.notifyItemInserted(0);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TeamItem>> call, Throwable t) {
                Toast.makeText(TeamActivity.this, t+"", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
