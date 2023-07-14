package com.example.foodapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodapp.R;
import com.example.foodapp.model.Category.Category;
import com.example.foodapp.model.Food.Food;
import com.example.foodapp.model.Food.RecommendedFood;
import com.example.foodapp.model.RecycleView.CategoryAdapter;
import com.example.foodapp.model.RecycleView.RecommendedFoodAdapter;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private CategoryAdapter categoryAdapter;

    private ImageView backArrowImageView;
    private ImageView categoryImageView;
    private TextView categoryName;
    private RecyclerView categoryRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        initView();

        setOnBackPressedArrow();

        initList();
    }

    private void initView() {
        this.backArrowImageView = findViewById(R.id.categoryGoBackArrowImageView);
        this.categoryImageView = findViewById(R.id.categoryImageView);
        this.categoryName = findViewById(R.id.categoryTextView);
        //this.categoryRecyclerView = findViewById(R.id.categoryRecyclerView); //TODO: remove? lo faccio sotto.
    }

    private void setOnBackPressedArrow() {
        this.backArrowImageView.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void initList() {
        List<Food> categoryFoodsList = new ArrayList<>();
        final String category = getIntent().getStringExtra("category");
        this.categoryName.setText(category);

        //TODO: volendo aggiungere altri cibi
        switch(category) {
            case "Poké":
                this.categoryImageView.setImageResource(R.drawable.poke_background1);
                categoryFoodsList.add(new Food("Chicken Poké", Category.POKE, 1, "$", "5.60", false, R.drawable.southfin_bowls_chicken));
                categoryFoodsList.add(new Food("Chicken Poké 2", Category.POKE, 1, "$", "7.50", false, R.drawable.southfin_bowls_chicken));
                break;
            case "Fruit":
                this.categoryImageView.setImageResource(R.drawable.fruit_background1);
                categoryFoodsList.add(new Food("Strawberries", Category.FRUIT, 1, "$", "3.50", false, R.drawable.southfin_bowls_chicken));
                break;
            case "Vegetables":
                this.categoryImageView.setImageResource(R.drawable.vegetables_background1);
                categoryFoodsList.add(new Food("Zucchini", Category.VEGETABLES, 1, "$", "3.60", false, R.drawable.southfin_bowls_chicken));
                break;
            case "Salad":
                this.categoryImageView.setImageResource(R.drawable.salad_background1);
                categoryFoodsList.add(new Food("Salad", Category.SALAD, 1, "$", "4.50", false, R.drawable.southfin_bowls_chicken));
                break;
        }

        setCategoryRecyclerView(categoryFoodsList);
    }

    private void setCategoryRecyclerView(final List<Food> categoryFoodsList) {
        this.categoryRecyclerView = findViewById(R.id.categoryRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        this.categoryRecyclerView.setLayoutManager(layoutManager);
        this.categoryAdapter = new CategoryAdapter(this, categoryFoodsList);
        this.categoryRecyclerView.setAdapter(this.categoryAdapter);
    }


}