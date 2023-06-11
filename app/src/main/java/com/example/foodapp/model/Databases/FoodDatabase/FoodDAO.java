package com.example.foodapp.model.Databases.FoodDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.foodapp.model.Food.Food;

import java.util.List;

@Dao
public interface FoodDAO {

    @Insert
    void insertList(Food... foods);

    @Insert
    void insertFood(Food food);

    @Query("DELETE FROM food WHERE foodId = :id")
    void deleteFood(int id);

    @Query("UPDATE food SET name = :name, category = :category, quantity = :quantity, currency = :currency, price = :price, is_favorite = :isFavorite, image_url = :imageUrl WHERE foodId = :foodId")
    void updateFood(final int foodId, final String name, final String category, final int quantity, final String currency, final String price, final boolean isFavorite, final int imageUrl);

    @Delete
    void deleteFoods(Food... foods);

    @Query("DELETE FROM food") //TODO: remove?
    void deleteAllFoods();

    @Query("SELECT * FROM food")
    List<Food> getFoods();

}
