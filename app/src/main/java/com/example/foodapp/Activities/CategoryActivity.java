package com.example.foodapp.Activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.Category.Category;
import com.example.foodapp.model.Food.Food;
import com.example.foodapp.model.RecycleView.CategoryAdapter;

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
        this.backArrowImageView.setOnClickListener(view -> onBackPressed());
    }

    private void initList() {
        List<Food> categoryFoodsList = new ArrayList<>();
        final String category = getIntent().getStringExtra("category");
        this.categoryName.setText(category);

        //TODO: volendo aggiungere altri cibi
        switch(category) {
            case "Poké":
                this.categoryImageView.setImageResource(R.drawable.poke_background1);
                categoryFoodsList.add(new Food("Chicken Poké", Category.POKE, 1, "$", "5.60", false, R.drawable.southfin_bowls_chicken, "{ingredients}"));
                categoryFoodsList.add(new Food("Tofu Poké Bowl", Category.POKE, 1, "$", "7.50", false, R.drawable.tofu_poke_bowl_no_background, "{ingredients}"));
                categoryFoodsList.add(new Food("Poké Shrimps Vegetables", Category.POKE, 1, "$", "9.00", false, R.drawable.poke_bowl_shrimps_and_vegetables_no_background, "{ingredients}")); // Poké Bowl Shripms & Vegetables
                categoryFoodsList.add(new Food("Miso Mushroom Poké", Category.POKE, 1, "$", "6.50", false, R.drawable.somerset_foodie_miso_mushroom_poke_bowl_no_background, "{ingredients}")); // Miso Mushroom Poké Bowl
                categoryFoodsList.add(new Food("Quinoa Poké Bowl", Category.POKE, 1, "$", "8.50", false, R.drawable.quinoa_poke_bowls_cheap_no_background, "{ingredients}"));
                categoryFoodsList.add(new Food("Avocado Lachs Poké Bowl", Category.POKE, 1, "$", "5.50", false, R.drawable.avocado_lachs_poke_bowl_no_background, "{ingredients}"));
                break;
            case "Fruit":
                this.categoryImageView.setImageResource(R.drawable.fruit_background1);
                categoryFoodsList.add(new Food("Dragonfruit Smoothie", Category.FRUIT, 1, "$", "6.50", false, R.drawable.dragonfruit_smoothie_bowl, "{ingredients}")); //Dragonfruit Smoothie Bowl
                categoryFoodsList.add(new Food("Juice Fruit Salad", Category.FRUIT, 1, "$", "4.50", false, R.drawable.juice_fruit_salad, "{ingredients}"));
                categoryFoodsList.add(new Food("Strawberry Pairfait", Category.FRUIT, 1, "$", "7.50", false, R.drawable.strawberry_parfait_ice_cream, "{ingredients}")); // Strawberry Pairfait Ice Cream
                categoryFoodsList.add(new Food("Grilled Fruit Kabos", Category.FRUIT, 1, "$", "5.50", false, R.drawable.grilled_fruit_kabobs_no_background, "{ingredients}"));
                categoryFoodsList.add(new Food("Fruit Salsa Cinnamon", Category.FRUIT, 1, "$", "8.50", false, R.drawable.tangy_fruit_salsa_with_cinnamon_chips_no_background, "{ingredients}")); // Tangy Fruit Salsa With Cinnamon
                categoryFoodsList.add(new Food("Fruit Pudding Parfait", Category.FRUIT, 1, "$", "4.99", false, R.drawable.ministop_fruits_pudding_parfait_no_background, "{ingredients}"));
                break;
            case "Vegetables":
                this.categoryImageView.setImageResource(R.drawable.vegetables_background1);
                categoryFoodsList.add(new Food("Vegetable Curry", Category.VEGETABLES, 1, "$", "3.60", false, R.drawable.cu7_vegetable_curry, "{ingredients}"));
                categoryFoodsList.add(new Food("Caprese Salad", Category.VEGETABLES, 1, "$", "4.50", false, R.drawable.caprese_salad_no_background, "{ingredients}"));
                categoryFoodsList.add(new Food("Grilled Vegetable Yogurt Mint Sauce", Category.VEGETABLES, 1, "$", "5.50", false, R.drawable.grilled_vegetable_platter_with_yogurt_mint_sauce_no_background, "{ingredients}")); // Grilled Vegetable Platter With Yogurt Mint Sauce
                categoryFoodsList.add(new Food("Ratatouille", Category.VEGETABLES, 1, "$", "5.99", false, R.drawable.ratatouille_no_background, "{ingredients}"));
                categoryFoodsList.add(new Food("Stir Fry Vegetables", Category.VEGETABLES, 1, "$", "6.99", false, R.drawable.stir_fry_vegetables_no_background, "{ingredients}"));
                break;
            case "Salad":
                this.categoryImageView.setImageResource(R.drawable.salad_background1);
                categoryFoodsList.add(new Food("Greek Salad", Category.SALAD, 1, "$", "4.50", false, R.drawable.greek_salad, "{ingredients}"));
                categoryFoodsList.add(new Food("Grilled Chicken Caeser", Category.SALAD, 1, "$", "7.50", false, R.drawable.grilled_chicken_caesar_salad, "{ingredients}")); //TODO: non è vegetariano! // Grilled Chicken Caeser Salad
                categoryFoodsList.add(new Food("Waldorf Salad", Category.SALAD, 1, "$", "6.99", false, R.drawable.waldorf_salad_2, "{ingredients}"));
                categoryFoodsList.add(new Food("Nicoise Salad", Category.SALAD, 1, "$", "6.50", false, R.drawable.nicoise_salad_no_background, "{ingredients}"));
                categoryFoodsList.add(new Food("Cobb Salad", Category.SALAD, 1, "$", "4.99", false, R.drawable.cobb_salad_no_background, "{ingredients}"));
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