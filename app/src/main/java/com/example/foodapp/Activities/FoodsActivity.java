package com.example.foodapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.foodapp.R;
import com.example.foodapp.model.Category.Category;
import com.example.foodapp.model.Food.Food;
import com.example.foodapp.model.RecycleView.CategoryAdapter;
import com.example.foodapp.model.RecycleView.FoodsAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FoodsActivity extends AppCompatActivity {

    private FoodsAdapter foodsAdapter;

    private ImageView backArrow;
    private RecyclerView foodsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods);

        initView();

        setOnBackPressedArrow();

        initList();
    }

    private void initView() {
        this.foodsRecyclerView = findViewById(R.id.foodsRecyclerView);
        this.backArrow = findViewById(R.id.foodsGoBackArrowImageView);
    }

    private void setOnBackPressedArrow() {
        this.backArrow.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void initList() {

        List<Food> foodList = new ArrayList<>(Arrays.asList(
                new Food("Chicken Poké", Category.POKE, 1, "$", "5.60", false, R.drawable.southfin_bowls_chicken, "{ingredients}"),
                new Food("Grilled Fruit Kabos", Category.FRUIT, 1, "$", "5.50", false, R.drawable.grilled_fruit_kabobs_no_background, "{ingredients}"),
                new Food("Nicoise Salad", Category.SALAD, 1, "$", "6.50", false, R.drawable.nicoise_salad_no_background, "{ingredients}"),
                new Food("Grilled Vegetable Yogurt Mint Sauce", Category.VEGETABLES, 1, "$", "5.50", false, R.drawable.grilled_vegetable_platter_with_yogurt_mint_sauce_no_background, "{ingredients}"),
                new Food("Juice Fruit Salad", Category.FRUIT, 1, "$", "4.50", false, R.drawable.juice_fruit_salad, "{ingredients}")
        ));


        setFoodsRecyclerView(foodList);
    }

    private void setFoodsRecyclerView(final List<Food> foodsList) {
        this.foodsRecyclerView = findViewById(R.id.foodsRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        this.foodsRecyclerView.setLayoutManager(layoutManager);
        this.foodsAdapter = new FoodsAdapter(this, foodsList);
        this.foodsRecyclerView.setAdapter(this.foodsAdapter);
    }
}