package com.example.foodapp.model.RecycleView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;

public class FavoriteFoodViewHolder extends RecyclerView.ViewHolder {

    private ImageView itemFavorite;
    private ImageView itemImage;
    private TextView itemCurrency;
    private TextView itemPrice;
    private TextView itemName;

    private ImageView itemShoppingBasket; //TODO: remove?

    public FavoriteFoodViewHolder(@NonNull View itemView) {
        super(itemView);

        this.itemFavorite = itemView.findViewById(R.id.item_favorite);
        this.itemImage = itemView.findViewById(R.id.item_image);
        this.itemCurrency = itemView.findViewById(R.id.item_currency);
        this.itemPrice = itemView.findViewById(R.id.item_price);
        this.itemName = itemView.findViewById(R.id.item_name);
    }

    public ImageView getItemFavorite() {
        return this.itemFavorite;
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

    public void setItemFavoriteImage(final int resId) {
        this.itemFavorite.setImageResource(resId);
    }

    public void setItemCurrency(final TextView itemCurrency) {
        this.itemCurrency = itemCurrency;
    }

    public void setItemPrice(final TextView itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemName(final TextView itemName) {
        this.itemName = itemName;
    }

    public void setItemFavorite(final ImageView itemFavorite) {
        this.itemFavorite = itemFavorite;
    }

    public void setItemImage(int resId) { //TODO: remove
        this.itemImage.setImageResource(resId);
    }
}
