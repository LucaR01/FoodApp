package com.example.foodapp.model.RecycleView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;

public class FavoriteFoodsListViewHolder extends RecyclerView.ViewHolder{

    private ImageView itemImage;
    private TextView itemName;
    private TextView itemCategory;
    private TextView itemQuantity;
    private TextView itemCurrency;
    private TextView itemPrice;

    public FavoriteFoodsListViewHolder(@NonNull View itemView) {
        super(itemView);

        this.itemImage = itemView.findViewById(R.id.favoriteFoodsItemImageView);
        this.itemName = itemView.findViewById(R.id.favoriteFoodsItemFoodNameTextView);
        this.itemCategory = itemView.findViewById(R.id.favoriteFoodsItemCategoryTextView);
        this.itemQuantity = itemView.findViewById(R.id.favoriteFoodsItemQuantityTextView);
        this.itemCurrency = itemView.findViewById(R.id.favoriteFoodsItemCurrencyTextView);
        this.itemPrice = itemView.findViewById(R.id.favoriteFoodsItemPriceTextView);
    }

    public ImageView getItemImage() {
        return this.itemImage;
    }

    public TextView getItemName() {
        return this.itemName;
    }

    public TextView getItemCategory() {
        return this.itemCategory;
    }

    public TextView getItemQuantity() {
        return this.itemQuantity;
    }

    public TextView getItemCurrency() {
        return this.itemCurrency;
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

    public void setItemCategory(final TextView itemCategory) {
        this.itemCategory = itemCategory;
    }

    public void setItemQuantity(final TextView itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public void setItemCurrency(final TextView itemCurrency) {
        this.itemCurrency = itemCurrency;
    }

    public void setItemPrice(final TextView itemPrice) {
        this.itemPrice = itemPrice;
    }
}
