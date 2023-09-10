package com.example.foodapp.Activities;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.Databases.FavoriteFoodDatabase.FavoriteFoodDatabase;
import com.example.foodapp.model.Food.FavoriteFood;
import com.example.foodapp.model.RecycleView.FavoriteFoodsListAdapter;

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
        this.backArrow.setOnClickListener(view -> onBackPressed());
    }

    private void initList() {
        final List<FavoriteFood> favoriteFoodsList = retrieveFavoriteFoodsDatabase().favoriteFoodDAO().getFavoriteFoods();
        setFavoriteFoodsRecyclerView(favoriteFoodsList);
    }

    private void setFavoriteFoodsRecyclerView(final List<FavoriteFood> favoriteFoodsList) {
        this.favoriteFoodsRecyclerView = findViewById(R.id.favoriteFoodsRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        this.favoriteFoodsRecyclerView.setLayoutManager(layoutManager);
        this.favoriteFoodsListAdapter = new FavoriteFoodsListAdapter(this, favoriteFoodsList);
        this.favoriteFoodsRecyclerView.setAdapter(this.favoriteFoodsListAdapter);
    }

    private FavoriteFoodDatabase retrieveFavoriteFoodsDatabase() {
        return FavoriteFoodDatabase.getDatabaseInstance(getApplicationContext());
    }
}