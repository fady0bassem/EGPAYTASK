package com.fady.egpaytask.data.remote;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("/")
    @FormUrlEncoded
    Call<String> Data_MAP_CALL(@FieldMap Map<String, String> dataMap);
}
