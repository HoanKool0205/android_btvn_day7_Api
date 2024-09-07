package com.androidexp.apicallview.networks;

import com.androidexp.apicallview.constants.ConstantApi;
import com.androidexp.apicallview.models.AllProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DummyService {
    @GET(ConstantApi.GET_ALL_PRODUCT)
    Call<AllProductResponse> getAllProduct();
}
