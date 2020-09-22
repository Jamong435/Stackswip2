package com.kim9212.stackswip;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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
