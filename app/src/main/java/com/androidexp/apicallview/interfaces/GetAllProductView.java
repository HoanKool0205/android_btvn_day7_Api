package com.androidexp.apicallview.interfaces;

import com.androidexp.apicallview.models.AllProductResponse;

public interface GetAllProductView {

    void onGetAllProductSuccess(AllProductResponse response);

    void onGetAllProductFailed(String message);
}
