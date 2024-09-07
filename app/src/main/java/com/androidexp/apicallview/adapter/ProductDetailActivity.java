package com.androidexp.apicallview.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.androidexp.apicallview.R;
import com.androidexp.apicallview.models.Product;
import com.androidexp.apicallview.view.MainActivity;
import com.bumptech.glide.Glide;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }

        Product product = (Product) bundle.get("product_item");

        if(product == null){
            return;
        }

        TextView tvProductTitle1 = findViewById(R.id.tvProductTitle1);
        TextView tvProductPrice1 = findViewById(R.id.tvProductPrice1);
        TextView tvProductDescription1 = findViewById(R.id.tvProductDescription1);
        ImageView imgView = findViewById(R.id.imgView);
        Button backBtn = findViewById(R.id.backBtn);

        tvProductTitle1.setText(product.getTitle());
        tvProductPrice1.setText("$" + product.getPrice());
        tvProductDescription1.setText(product.getDescription());

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        String imgUrl = product.getImages().get(0);
        Glide.with(this).load(imgUrl).into(imgView);
    }
}