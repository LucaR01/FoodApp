package com.example.foodapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FoodDetailsActivity extends AppCompatActivity {

    private int counter = 0;
    private TextView counterTextView;
    private TextView foodName;
    private TextView foodPrice;
    private TextView foodIngredients;

    private FloatingActionButton addFloatingActionButton;
    private FloatingActionButton removeFloatingActionButton;

    private Button addToCartButton;

    private ImageView backArrowImageView;
    private ImageView foodImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_food_details);

        counterTextView = findViewById(R.id.foodDetailCounterTextView);
        foodName = findViewById(R.id.foodDetailNameTextView);
        foodPrice = findViewById(R.id.foodDetailPriceTextView);
        foodIngredients = findViewById(R.id.foodDetailIngredientsTextView);

        addFloatingActionButton = findViewById(R.id.foodDetailAddFAB);
        removeFloatingActionButton = findViewById(R.id.foodDetailRemoveFAB);

        addToCartButton = findViewById(R.id.foodDetailAddToCartButton);

        backArrowImageView = findViewById(R.id.foodDetailBackImageView);
        foodImageView = findViewById(R.id.foodDetailImageView);


        backArrowImageView.setOnClickListener(view -> {
            onBackPressed();
        });

        foodName.setText(getIntent().getStringExtra("foodDetailNameTextView")); //TODO: item_name?
        foodPrice.setText(getIntent().getStringExtra("foodDetailPriceTextView")); //TODO: item_price?
        foodIngredients.setText("SAMPLE TEXT"); //TODO: Update
        foodImageView.setImageResource(getIntent().getIntExtra("foodDetailImageView", R.drawable.southfin_bowls_chicken)); //TODO: update R.drawable?; //TODO: item_image?

        //TODO: do the rest.

        counterTextView.setText(String.valueOf(counter)); //TODO: remove?

        addFloatingActionButton.setOnClickListener(view -> {
            counterTextView.setText(String.valueOf(++counter));
        });

        removeFloatingActionButton.setOnClickListener(view -> {
            counterTextView.setText(counter > 0 ? String.valueOf(--counter) : String.valueOf(counter));
        });

        addToCartButton.setOnClickListener(view -> {
            System.out.println("Counter: " + counter); //TODO: remove; use logger
            //TODO: implement functionality
        });
    }


}