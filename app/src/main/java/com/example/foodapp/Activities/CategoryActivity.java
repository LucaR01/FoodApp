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
        this.categoryRecyclerView = findViewById(R.id.categoryRecyclerView);
    }

    private void setOnBackPressedArrow() {
        this.backArrowImageView.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void initList() {
        /*final String category = getIntent().getStringExtra("poké"); //TODO: remove
        final String category2 = getIntent().getStringExtra("fruit");
        final String category3 = getIntent().getStringExtra("vegetables");
        final String category4 = getIntent().getStringExtra("salad");*/

        List<Food> categoryFoodsList = new ArrayList<>();
        final String category = getIntent().getStringExtra("category");

        //TODO: aggiornare le immagini e aggiungere altri cibi
        switch(category) {
            case "poké":
                categoryFoodsList.add(new Food("Chicken Poké", Category.POKE, 1, "$", "5.60", false, R.drawable.southfin_bowls_chicken));
                break;
            case "fruit":
                categoryFoodsList.add(new Food("Strawberries", Category.FRUIT, 1, "$", "3.50", false, R.drawable.southfin_bowls_chicken));
                break;
            case "vegetables":
                categoryFoodsList.add(new Food("Zucchini", Category.VEGETABLES, 1, "$", "3.60", false, R.drawable.southfin_bowls_chicken));
                break;
            case "salad":
                categoryFoodsList.add(new Food("Salad", Category.SALAD, 1, "$", "4.50", false, R.drawable.southfin_bowls_chicken));
                break;
        }

        setCategoryRecyclerView(categoryFoodsList);
    }

    private void setCategoryRecyclerView(List<Food> categoryFoodsList) {
        categoryFoodsList = findViewById(R.id.categoryRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        this.categoryRecyclerView.setLayoutManager(layoutManager);
        this.categoryAdapter = new CategoryAdapter(this, categoryFoodsList);
        this.categoryRecyclerView.setAdapter(categoryAdapter);
    }


}