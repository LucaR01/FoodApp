package com.example.foodapp.model.RecycleView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;

public class RecommendedFoodViewHolder extends RecyclerView.ViewHolder {

    private ImageView itemImage;
    private TextView itemCurrency;
    private TextView itemPrice;
    private TextView itemName;

    private ImageView itemFavorite; //TODO: remove?
    private ImageView itemShoppingBasket; //TODO: remove?

    public RecommendedFoodViewHolder(@NonNull View itemView) {
        super(itemView);

        this.itemImage = itemView.findViewById(R.id.item_image);
        this.itemCurrency = itemView.findViewById(R.id.item_currency);
        this.itemPrice = itemView.findViewById(R.id.item_price);
        this.itemName = itemView.findViewById(R.id.item_name);
    }

    public ImageView getItemImage() {
        return this.itemImage;
    }

    public TextView getItemCurrency() {
        return this.itemCurrency;
    }

    public TextView getItemPrice() {
        return this.itemPrice;
    }

    public TextView getItemName() {
        return this.itemName;
    }

    public void setItemImage(int resId) { //TODO: remove
        this.itemImage.setImageResource(resId);
    }
}
