package com.androidexp.apicallview.presenters;

import com.androidexp.apicallview.interactors.DummyInteractor;
import com.androidexp.apicallview.interfaces.DummyPresenterImpl;
import com.androidexp.apicallview.interfaces.GetAllProductView;
import com.androidexp.apicallview.models.AllProductResponse;
import com.androidexp.apicallview.models.Product;
import com.androidexp.apicallview.view.MainActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DummyPresenter implements DummyPresenterImpl {

    private DummyInteractor dummyInteractor;
    private GetAllProductView getAllProductViewCallback;

    public void setGetAllProductViewCallback(GetAllProductView getAllProductViewCallback) {
        this.getAllProductViewCallback = getAllProductViewCallback;
    }

    public DummyPresenter() {
        dummyInteractor = DummyInteractor.getInstance();
    }

    @Override
    public void getAllProduct() {
        dummyInteractor.setGetAllProductResponseCallback(allProductResponseCallback);
        dummyInteractor.getAllProduct();
    }


    private Callback<AllProductResponse> allProductResponseCallback = new Callback<AllProductResponse>() {
        @Override
        public void onResponse(Call<AllProductResponse> call, Response<AllProductResponse> response) {
            if(response.isSuccessful()){
                getAllProductViewCallback.onGetAllProductSuccess(response.body());
            } else{
                getAllProductViewCallback.onGetAllProductFailed("Error code"+ response.code());
            }
        }

        @Override
        public void onFailure(Call<AllProductResponse> call, Throwable throwable) {
            getAllProductViewCallback.onGetAllProductFailed("Error"+throwable.getMessage());
        }
    };

    public void sortByAllProductPrice(AllProductResponse allProductResponse){
        if(allProductResponse != null && allProductResponse.getProducts() != null){
            List<Product> productList = allProductResponse.getProducts();
            Collections.sort(productList, new Comparator<Product>() {
                @Override
                public int compare(Product product1, Product product2) {
                    return Double.compare(product1.getPrice(), product2.getPrice());
                }
            });
            getAllProductViewCallback.onGetAllProductSuccess(allProductResponse);
        } else{
            getAllProductViewCallback.onGetAllProductFailed("No sort Product");
        }
    }

    public void sortDecreateProductPrice(AllProductResponse allProductResponse){
        if(allProductResponse != null && allProductResponse.getProducts() != null){
            List<Product> productList = allProductResponse.getProducts();
            Collections.sort(productList, new Comparator<Product>() {
                @Override
                public int compare(Product product1, Product product2) {
                    return Double.compare(product2.getPrice(), product1.getPrice());
                }
            });
            getAllProductViewCallback.onGetAllProductSuccess(allProductResponse);
        } else {
            getAllProductViewCallback.onGetAllProductFailed("No Product");
        }
    }
}
