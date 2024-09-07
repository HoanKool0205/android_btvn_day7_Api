package com.androidexp.apicallview.interactors;

import com.androidexp.apicallview.interfaces.DummyInteractorImpl;
import com.androidexp.apicallview.models.AllProductResponse;
import com.androidexp.apicallview.networks.DummyService;
import com.androidexp.apicallview.networks.RetrofitClient;

import retrofit2.Callback;


public class DummyInteractor implements DummyInteractorImpl {

    private static DummyInteractor instances;
    private static DummyService dummyService;

    private Callback<AllProductResponse> getAllProductResponseCallback;

    public void setGetAllProductResponseCallback(Callback<AllProductResponse> getAllProductResponseCallback) {
        this.getAllProductResponseCallback = getAllProductResponseCallback;
    }

    public static DummyInteractor getInstance(){
        if(instances == null){
            instances = new DummyInteractor();
        }
        dummyService = RetrofitClient.getDummyService();
        return instances;
    }

    @Override
    public void getAllProduct() {
        dummyService.getAllProduct().enqueue(getAllProductResponseCallback);
    }
}
