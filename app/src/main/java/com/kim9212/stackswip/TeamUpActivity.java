package com.kim9212.stackswip;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TeamUpActivity extends AppCompatActivity {

    Spinner spinner;
    ArrayAdapter spinnerAdapter;

    EditText teamTitle;
    EditText teamMsg;
    EditText teamEmail;
    EditText teamMember;
    EditText teamPeriod;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_up);

        teamTitle=findViewById(R.id.TUP_member);
        teamMsg=findViewById(R.id.TUP_msg);
        teamEmail=findViewById(R.id.TUP_call);
        teamMember=findViewById(R.id.TUP_member);
        teamPeriod=findViewById(R.id.TUP_period);

        spinner=findViewById(R.id.TUP_areaSpinner);
        spinnerAdapter=ArrayAdapter.createFromResource(this,R.array.area_data,R.layout.team_spinner_selected);

//        드롭다운 모양
        spinnerAdapter.setDropDownViewResource(R.layout.team_spinner_dropdown);

        spinner.setAdapter(spinnerAdapter);
    }

    public void clickComplete(View view) {

        String title=teamTitle.getText().toString();
        String msg=teamMsg.getText().toString();
        String email=teamEmail.getText().toString();
        String member=teamMember.getText().toString();
        String period=teamPeriod.getText().toString();
        String area=spinner.getSelectedItem().toString();

        Retrofit retrofit=RetrofitHelper.getInstance2();
        RetrofitService retrofitService=retrofit.create(RetrofitService.class);

        Map<String,String> dataPart=new HashMap<>();
        dataPart.put("title",title);
        dataPart.put("msg",msg);
        dataPart.put("email",email);
        dataPart.put("member",member);
        dataPart.put("period",period);
        dataPart.put("area",area);

        Call<String> calls=retrofitService.postDataToTeam(dataPart);
        calls.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(TeamUpActivity.this, "데이터전송T", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }
}
