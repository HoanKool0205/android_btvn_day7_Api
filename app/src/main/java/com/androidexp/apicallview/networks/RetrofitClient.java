package com.androidexp.apicallview.networks;

import com.androidexp.apicallview.constants.ConstantApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit instances;

    public static Retrofit getInstances(){
        if(instances == null){
            instances = new Retrofit.Builder()
                    .baseUrl(ConstantApi.BASE_URL).addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instances;
    }

    public static DummyService getDummyService(){
        return getInstances().create(DummyService.class);
    }

}
