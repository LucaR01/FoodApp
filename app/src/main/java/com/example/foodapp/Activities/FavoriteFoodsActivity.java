package com.example.foodapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.foodapp.R;
import com.example.foodapp.model.Category.Category;
import com.example.foodapp.model.Food.FavoriteFood;
import com.example.foodapp.model.Food.Food;
import com.example.foodapp.model.RecycleView.FavoriteFoodsListAdapter;
import com.example.foodapp.model.RecycleView.FoodsAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FavoriteFoodsActivity extends AppCompatActivity {

    private FavoriteFoodsListAdapter favoriteFoodsListAdapter;

    private ImageView backArrow;
    private RecyclerView favoriteFoodsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_foods);

        initView();

        setOnBackPressedArrow();

        initList();

    }

    private void initView() {
        this.favoriteFoodsRecyclerView = findViewById(R.id.favoriteFoodsRecyclerView);
        this.backArrow = findViewById(R.id.favoriteFoodsGoBackArrowImageView);
    }

    private void setOnBackPressedArrow() {
        this.backArrow.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void initList() {
        List<FavoriteFood> favoriteFoodsList = new ArrayList<>(Arrays.asList(
                new FavoriteFood("Salad", Category.SALAD, 1, "$", "7.00", true, R.drawable.salad_background),
                new FavoriteFood("Salad 2", Category.SALAD, 2, "$", "6.50", true, R.drawable.salad_background)
        ));

        setFavoriteFoodsRecyclerView(favoriteFoodsList);
    }

    private void setFavoriteFoodsRecyclerView(final List<FavoriteFood> favoriteFoodsList) {
        this.favoriteFoodsRecyclerView = findViewById(R.id.favoriteFoodsRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        this.favoriteFoodsRecyclerView.setLayoutManager(layoutManager);
        this.favoriteFoodsListAdapter = new FavoriteFoodsListAdapter(this, favoriteFoodsList);
        this.favoriteFoodsRecyclerView.setAdapter(this.favoriteFoodsListAdapter);
    }
}