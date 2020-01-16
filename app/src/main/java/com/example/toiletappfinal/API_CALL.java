package com.example.toiletappfinal;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API_CALL {
    private final static API_CALL api = null;
    //at first api is null to create new instance of api class
    private Retrofit retrofit;


    public static final String baseUrl = "http://192.168.0.108";

    private API_CALL() {
        //here a new instance of Retrofit is created and Gson converter is used for parsing json object
        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl).
                        addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    // this returns the object of retrofit
    public Retrofit getRetrofit() {
        return retrofit;
    }

    // this is the singleton Design Pattern, With this only one instance of API_CAll is created
    public static API_CALL getAPI_Instance(){
        if(api == null){
            return new API_CALL();
        }
        return api;
    }
}
