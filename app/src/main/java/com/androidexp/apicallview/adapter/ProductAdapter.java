package com.androidexp.apicallview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.androidexp.apicallview.R;
import com.androidexp.apicallview.models.Product;
import com.androidexp.apicallview.myproductclick.IOProductItemClick;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHoder> {

    private ArrayList<Product> mListData;
    private Context mContext;

    private IOProductItemClick ioProductItemClick;


    public ProductAdapter(ArrayList<Product> mListData, IOProductItemClick listener) {
        this.mListData = mListData;
        this.ioProductItemClick = listener;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item_product, parent, false);
        return new ProductViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHoder holder, int position) {
        Product product = mListData.get(position);
        holder.tvProductTitle.setText(product.getTitle());
        holder.tvProductPrice.setText("$" + product.getPrice());
        holder.tvProductSale.setText(product.getDiscountPercentage() + "%");

        String imageUrl = product.getImages().get(0);
        Glide.with(mContext).load(imageUrl).into(holder.imageView);

        holder.layout_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ioProductItemClick.onClickItemProduct(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public class ProductViewHoder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvProductTitle, tvProductPrice, tvProductSale;

        ConstraintLayout layout_product;


        public ProductViewHoder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgView);
            tvProductTitle = itemView.findViewById(R.id.tvProductTitle);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            tvProductSale = itemView.findViewById(R.id.tvProductSale);

            layout_product = itemView.findViewById(R.id.layout_product);

        }
    }

    public void updateProductList(List<Product> newListProduct){
        this.mListData.clear();
        this.mListData.addAll(newListProduct);
        notifyDataSetChanged();
    }
}
