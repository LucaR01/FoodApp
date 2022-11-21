package com.example.foodapp.model.Food;

import com.example.foodapp.model.Category.Category;

public class FavoriteFood extends Food{

    public FavoriteFood(final String name, final Category category, final float price, final int imageUrl) {
        super(name, category, price, true, imageUrl);
    }

    FavoriteFood getFavoriteFood() {
        return this;
    }
}
