package com.example.foodapp.model.Food;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.foodapp.model.Category.Category;

@Entity(tableName = "food")
public class Food {

    @PrimaryKey(autoGenerate = true)
    public int foodId;

    //TODO: Creare un FoodFactory con un enum FoodType: FavoriteFood, RecommendedFood, Food, CartFood, ecc.

    @ColumnInfo(name = "name")
    private final String name;

    @ColumnInfo(name = "category")
    private final Category category; //TODO: Category enum or class?

    @ColumnInfo(name = "quantity")
    private int quantity; //TODO: uncomment/remove?

    @ColumnInfo(name = "currency")
    private String currency; //TODO: uncomment/remove?

    @ColumnInfo(name = "price")
    private String price; //TODO: forse usare una stringa oppure float o int

    @ColumnInfo(name = "is_favorite")
    private boolean isFavorite;

    @ColumnInfo(name = "image_url")
    private int imageUrl;

    public Food(final String name, final Category category, final int quantity, final String currency,
                final String price, final boolean isFavorite, final int imageUrl) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.currency = currency;
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

    public final int getQuantity() {
        return this.quantity;
    }

    public final String getCurrency() {
        return this.currency;
    }

    public final String getPrice() {
        return this.price;
    }

    public final boolean isFavorite() {
        return this.isFavorite;
    }

    public final int getImageUrl() { return this.imageUrl; }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public void setPrice(final String price) {
        this.price = price;
    }

    public void setFavorite(final boolean favorite) {
        this.isFavorite = favorite;
    }

    public void setImageUrl(final int imageUrl) {
        this.imageUrl = imageUrl;
    }
}
