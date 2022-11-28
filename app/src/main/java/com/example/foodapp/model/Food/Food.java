package com.example.foodapp.model.Food;

import com.example.foodapp.model.Category.Category;

public class Food {

    private final String name;
    private final Category category; //TODO: Category enum or class?

    private String price; //TODO: forse usare una stringa oppure float o int
    private boolean isFavorite;
    private int imageUrl;

    public Food(final String name, final Category category, final String price, final boolean isFavorite, final int imageUrl) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.isFavorite = isFavorite;
        this.imageUrl = imageUrl;
    }

    public final String getName() {
        return this.name;
    }

    public final Category getCategory() {
        return this.category;
    }

    public final String getPrice() {
        return this.price;
    }

    public final boolean isFavorite() {
        return this.isFavorite;
    }

    public final int getImageUrl() { return this.imageUrl; }
}
