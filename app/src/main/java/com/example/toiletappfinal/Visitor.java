package com.example.toiletappfinal;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Visitor {
    @POST("/toilet_map/insert.php")
    Call<Void> updateVisitor();
}
