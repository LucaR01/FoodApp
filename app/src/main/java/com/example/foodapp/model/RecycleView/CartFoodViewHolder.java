package com.example.foodapp.model.RecycleView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;

public class CartFoodViewHolder extends RecyclerView.ViewHolder {

    private ImageView itemImage;
    private TextView itemName;
    private TextView itemQuantity;
    private TextView itemCurrency;
    private TextView itemPrice;

    public CartFoodViewHolder(@NonNull View itemView) {
        super(itemView);

        this.itemImage = itemView.findViewById(R.id.cardItemImageView);
        this.itemName = itemView.findViewById(R.id.cardItemFoodNameTextView);
        this.itemQuantity = itemView.findViewById(R.id.cardItemQuantityTextView);
        this.itemCurrency = itemView.findViewById(R.id.cardItemCurrencyTextView);
        this.itemPrice = itemView.findViewById(R.id.cardItemPriceTextView);
    }

    public ImageView getItemImage() {
        return itemImage;
    }

    public TextView getItemName() {
        return this.itemName;
    }

    public TextView getItemQuantity() {
        return this.itemQuantity;
    }

    public TextView getItemCurrency() {
        return itemCurrency;
    }

    public TextView getItemPrice() {
        return this.itemPrice;
    }

    public void setItemImage(final ImageView itemImage) {
        this.itemImage = itemImage;
    }

    public void setItemName(final TextView itemName) {
        this.itemName = itemName;
    }

    public void setItemQuantity(final TextView itemCQuantity) {
        this.itemQuantity = itemCQuantity;
    }

    public void setItemCurrency(final TextView itemCurrency) {
        this.itemCurrency = itemCurrency;
    }

    public void setItemPrice(final TextView itemPrice) {
        this.itemPrice = itemPrice;
    }
}
