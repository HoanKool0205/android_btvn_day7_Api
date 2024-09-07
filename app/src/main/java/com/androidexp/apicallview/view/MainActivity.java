package com.androidexp.apicallview.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidexp.apicallview.R;
import com.androidexp.apicallview.adapter.ProductAdapter;
import com.androidexp.apicallview.adapter.ProductDetailActivity;
import com.androidexp.apicallview.interfaces.GetAllProductView;
import com.androidexp.apicallview.models.AllProductResponse;
import com.androidexp.apicallview.models.Product;
import com.androidexp.apicallview.myproductclick.IOProductItemClick;
import com.androidexp.apicallview.presenters.DummyPresenter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetAllProductView {

    private DummyPresenter dummyPresenter;
    private RecyclerView rvProduct;
    private ProductAdapter productAdapter;

    private ArrayList<Product> mListData;

    private  AllProductResponse allProductResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dummyPresenter = new DummyPresenter();
        dummyPresenter.setGetAllProductViewCallback(this);

        initData();
        initView();

        initIncreaseSort();
        initDecreateSort();

        dummyPresenter.getAllProduct();
    }

    private void initData() {
        mListData = new ArrayList<>();
    }

    private void initView() {
        rvProduct = findViewById(R.id.rvProduct);
        productAdapter = new ProductAdapter(mListData, new IOProductItemClick() {
            @Override
            public void onClickItemProduct(Product product) {
                onProductDetailClick(product);
            }
        });

        rvProduct.setAdapter(productAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvProduct.setLayoutManager(gridLayoutManager);

    }

    @Override
    public void onGetAllProductSuccess(AllProductResponse response) {
        if(response != null && response.getProducts() != null){
            this.allProductResponse = response;
            mListData.clear();
            mListData.addAll(response.getProducts());
            productAdapter.notifyDataSetChanged();

            productAdapter.updateProductList(response.getProducts());
        } else {
            Log.d("TAG", "onGetAllProductSuccess: " + response.getProducts().get(0).toString());
        }
    }

    @Override
    public void onGetAllProductFailed(String message) {
        Log.d("TAG", "onGetAllProductFailed: "+message);
        Toast.makeText(this, "Failed load Products", Toast.LENGTH_SHORT).show();
    }

    public void onProductDetailClick(Product product){
        Intent intent = new Intent(this, ProductDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("product_item", product);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void initIncreaseSort(){
        Button increaseBtn = findViewById(R.id.increaseBtn);

        increaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dummyPresenter.sortByAllProductPrice(allProductResponse);
            }
        });
    }

    public void initDecreateSort(){
        Button decreaseBtn = findViewById(R.id.decreaseBtn);
        decreaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dummyPresenter.sortDecreateProductPrice(allProductResponse);
            }
        });
    }

}