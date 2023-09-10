package com.example.foodapp.model.Food;

public class RecommendedFood {

    private final Food recommendedFood;

    public RecommendedFood(final Food recommendedFood) {
        this.recommendedFood = recommendedFood;
    }

    public final Food getRecommendedFood() {
        return this.recommendedFood;
    }
}
