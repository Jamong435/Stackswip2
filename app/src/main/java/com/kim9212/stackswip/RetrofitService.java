package com.kim9212.stackswip;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface RetrofitService {
    @Multipart
    @POST("/Question/insertDB.php")
    Call<String> postDataToQuestion(@PartMap Map<String, String> dataPart,
                                    @Part MultipartBody.Part filePart);
    @Multipart
    @POST("/Question/insertDB.php")
    Call<String> postDataToQuestion(@PartMap Map<String, String> dataPart);

    @Multipart
    @POST("/Team/insertDB.php")
    Call<String> postDataToTeam(@PartMap Map<String, String> dataPart);



    @GET("/Question/loadDB.php")
    Call<ArrayList<QuestionItem>> loadDataFromQuestion();

    @GET("/Team/loadDB.php")
    Call<ArrayList<TeamItem>> loadDataFromTeam();

//    @Part
//    @POST("Team/insertDB.php")
//    Call<String> postDataToTeam(@Part Map<String,String> dataPart);
}
