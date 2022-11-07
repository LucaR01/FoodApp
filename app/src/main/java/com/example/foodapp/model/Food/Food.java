package com.example.foodapp.model.Food;

import com.example.foodapp.model.Category.Category;

public class Food {

    private final String name;
    private final Category category; //TODO: Category enum or class?

    private float price;
    private boolean isFavorite;

    public Food(final String name, final Category category, final float price, final boolean isFavorite) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.isFavorite = isFavorite;
    }

    public final String getName() {
        return this.name;
    }

    public final Category getCategory() {
        return this.category;
    }

    public final float getPrice() {
        return this.price;
    }

    public final boolean isFavorite() {
        return this.isFavorite;
    }
}
