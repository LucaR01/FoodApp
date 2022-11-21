package com.example.foodapp.model.Food;

import com.example.foodapp.model.Food.Food;

import java.util.List;

public class RecommendedFood {

    //private final List<Food> recommendedFoods; //TODO: uncomment?

    private final Food recommendedFood;

    public RecommendedFood(final Food recommendedFood) {
        this.recommendedFood = recommendedFood;
    }

    public final Food getRecommendedFood() {
        return this.recommendedFood;
    }
}
