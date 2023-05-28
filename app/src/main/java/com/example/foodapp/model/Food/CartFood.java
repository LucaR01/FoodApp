package com.example.foodapp.model.Food;

import com.example.foodapp.model.Category.Category;

//TODO: rename in CartItem?
public class CartFood extends Food {

    private String currency;
    private int quantity;

    public CartFood(final String name, final Category category, final int quantity, final String currency, final String price, final boolean isFavorite, final int imageUrl) {
        super(name, category, price, isFavorite, imageUrl);
        this.currency = currency;
        this.quantity = quantity;
    }

    public String getCurrency() {
        return this.currency;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }
}
