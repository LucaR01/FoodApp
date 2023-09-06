package com.example.foodapp.model.Databases.FavoriteFoodDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.foodapp.model.Food.FavoriteFood;
import com.example.foodapp.model.Food.Food;

import java.util.List;

@Dao
public interface FavoriteFoodDAO {

    //TODO: Al posto di food, favoriteFoods?

    @Insert
    void insertList(Food... favoriteFoods);

    @Insert
    void insertLists(List<Food> favoriteFoodList);

    @Insert
    void insertFavoriteFood(Food favoriteFood);

    @Query("DELETE FROM food WHERE foodId = :id")
    void deleteFavoriteFood(int id);

    @Query("UPDATE food SET name = :name, category = :category, quantity = :quantity, currency = :currency, price = :price, is_favorite = :isFavorite, image_resource_id = :imageResourceId WHERE foodId = :foodId")
    void updateFavoriteFood(final int foodId, final String name, final String category, final int quantity, final String currency, final String price, final boolean isFavorite, final int imageResourceId);

    @Delete
    void deleteFavoriteFoods(Food... favoriteFoods);

    @Query("DELETE FROM food") //TODO: remove?
    void deleteAllFavoriteFoods();

    @Query("SELECT * FROM food")
    List<FavoriteFood> getFavoriteFoods();
}
