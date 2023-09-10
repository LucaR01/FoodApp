package com.example.foodapp.model.Food;

import com.example.foodapp.model.Category.Category;

//TODO: rename in CartItem?
public class CartFood extends Food {

    //private CartFood cartFood; //TODO: remove

    public CartFood(final String name, final Category category, final int quantity, final String currency,
                    final String price, final boolean isFavorite, final int imageUrl, final String ingredients) {
        super(name, category, quantity, currency, price, isFavorite, imageUrl, ingredients);
    }

    public CartFood getCartFood() {
        return this; //TODO: prima return this.cartFood
    }
}
