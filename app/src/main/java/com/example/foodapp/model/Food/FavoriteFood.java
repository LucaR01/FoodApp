package com.example.foodapp.model.Food;

import com.example.foodapp.model.Category.Category;

//TODO: remove?

public class FavoriteFood extends Food{

    public FavoriteFood(final String name, final Category category, final int quantity, final String currency, final String price, final boolean isFavorite, final int imageUrl) {
        super(name, category, quantity, currency,  price, isFavorite, imageUrl);
    }

    FavoriteFood getFavoriteFood() { //TODO: remove?
        return this;
    }
}
