package com.example.foodapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodapp.R;
import com.example.foodapp.model.Category.Category;
import com.example.foodapp.model.Currency.Currency;
import com.example.foodapp.model.Databases.FoodDatabase.FoodDatabase;
import com.example.foodapp.model.Food.Food;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FoodDetailsActivity extends AppCompatActivity {

    private int counter = 1;
    private TextView counterTextView;
    private TextView foodName;
    private TextView foodCurrency;
    private TextView foodPrice;
    private TextView foodIngredients;

    private FloatingActionButton addFloatingActionButton;
    private FloatingActionButton removeFloatingActionButton;

    private Button addToCartButton;

    private ImageView backArrowImageView;
    private ImageView foodImageView;

    private Food food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_food_details);

        //FoodDatabase foodDatabase = FoodDatabase.getDatabaseInstance(getApplicationContext());
        //retrieveDatabase();

        initView();

        setOnBackPressedArrow();

        foodName.setText(getIntent().getStringExtra("foodDetailNameTextView")); //TODO: item_name?
        foodPrice.setText(getIntent().getStringExtra("foodDetailPriceTextView")); //TODO: item_price?
        foodCurrency.setText(getIntent().getStringExtra("foodDetailCurrencyTextView"));
        foodIngredients.setText("SAMPLE TEXT"); //TODO: Update
        foodImageView.setImageResource(getIntent().getIntExtra("foodDetailImageView", R.drawable.southfin_bowls_chicken)); //TODO: update R.drawable?; //TODO: item_image?

        //TODO: do the rest.

        counterTextView.setText(String.valueOf(counter)); //TODO: remove?

        addFloatingActionButton.setOnClickListener(view -> {
            counterTextView.setText(String.valueOf(++counter));
        });

        removeFloatingActionButton.setOnClickListener(view -> {
            counterTextView.setText(counter > 1 ? String.valueOf(--counter) : String.valueOf(counter));
        });

        addToCart();
    }

    private void setOnBackPressedArrow() {
        this.backArrowImageView.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void addToCart() {
        addToCartButton.setOnClickListener(view -> {
            System.out.println("Counter: " + counter); //TODO: remove; use logger
            getFoodDatabase().foodDAO().insertFood(retrieveFood());

            AlertDialog.Builder builder = new AlertDialog.Builder(FoodDetailsActivity.this);
            builder.setTitle("Success");
            builder.setMessage("Item added to cart successfully!");
            builder.setCancelable(true);

            builder.setPositiveButton(
                    "Ok",
                    (dialog, id) -> dialog.dismiss());

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
    }

    private void initView() {
        this.counterTextView = findViewById(R.id.foodDetailCounterTextView);
        this.foodName = findViewById(R.id.foodDetailNameTextView);
        this.foodCurrency = findViewById(R.id.foodDetailCurrencyTextView);
        this.foodPrice = findViewById(R.id.foodDetailPriceTextView);
        this.foodIngredients = findViewById(R.id.foodDetailIngredientsTextView);

        this.addFloatingActionButton = findViewById(R.id.foodDetailAddFAB);
        this.removeFloatingActionButton = findViewById(R.id.foodDetailRemoveFAB);

        this.addToCartButton = findViewById(R.id.foodDetailAddToCartButton);

        this.backArrowImageView = findViewById(R.id.foodDetailBackImageView);
        this.foodImageView = findViewById(R.id.foodDetailImageView);
    }

    private FoodDatabase getFoodDatabase() {
        return FoodDatabase.getDatabaseInstance(getApplicationContext());
    }

    private Food retrieveFood() {
        //TODO: recuperare isFavorite in base all'id dell'immagine.
        //TODO: recuperare la categoria.
        this.food = new Food(this.foodName.getText().toString(), Category.UNSPECIFIED, Integer.parseInt(this.counterTextView.getText().toString()),
                this.foodCurrency.getText().toString(), this.foodPrice.getText().toString(), false, this.foodImageView.getId());
        return food;
    }


}