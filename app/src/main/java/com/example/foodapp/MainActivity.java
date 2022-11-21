package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.foodapp.model.Category.Category;
import com.example.foodapp.model.Food.FavoriteFood;
import com.example.foodapp.model.Food.Food;
import com.example.foodapp.model.Food.RecommendedFood;
import com.example.foodapp.model.RecycleView.FavoriteFoodAdapter;
import com.example.foodapp.model.RecycleView.RecommendedFoodAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recommendedFoodsRecyclerView;
    private RecommendedFoodAdapter recommendedFoodAdapter;

    private RecyclerView favoriteFoodsRecyclerView;
    private FavoriteFoodAdapter favoriteFoodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Qui aggiungo i cibi raccomandati.
        List<RecommendedFood> recommendedFoodList = new ArrayList<>();
        recommendedFoodList.add(new RecommendedFood(new Food("Poke", Category.POKE, 5.00f, false, R.drawable.recommended_food_card_food)));
        recommendedFoodList.add(new RecommendedFood(new Food("Salad", Category.SALAD, 8.00f, false, R.drawable.recommended_food_card_food))); //TODO: cambiare drawable.

        setRecommendedFoodsRecyclerView(recommendedFoodList);

        List<FavoriteFood> favoriteFoodList = new ArrayList<>();
        favoriteFoodList.add(new FavoriteFood("", Category.NUTS, 4.5f, R.drawable.recommended_food_card_food)); //TODO: update drawable
        favoriteFoodList.add(new FavoriteFood("Cereals", Category.CEREALS, 3.0f, R.drawable.recommended_food_card_food)); //TODO: update drawable

        //setFavoriteFoodsRecyclerView(favoriteFoodList); //TODO: uncomment
    }

    private void setRecommendedFoodsRecyclerView(final List<RecommendedFood> recommendedFoodList) {
        this.recommendedFoodsRecyclerView = findViewById(R.id.recommended_foods_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        this.recommendedFoodsRecyclerView.setLayoutManager(layoutManager);
        this.recommendedFoodAdapter = new RecommendedFoodAdapter(this, recommendedFoodList);
        this.recommendedFoodsRecyclerView.setAdapter(recommendedFoodAdapter);
    }

    private void setFavoriteFoodsRecyclerView(final List<FavoriteFood> favoriteFoodList) {
        this.favoriteFoodsRecyclerView = findViewById(R.id.recommended_foods_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        this.favoriteFoodsRecyclerView.setLayoutManager(layoutManager);
        this.favoriteFoodAdapter = new FavoriteFoodAdapter(this, favoriteFoodList);
        this.favoriteFoodsRecyclerView.setAdapter(recommendedFoodAdapter);
    }
}