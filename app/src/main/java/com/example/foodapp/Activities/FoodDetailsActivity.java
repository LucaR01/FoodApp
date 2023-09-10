package com.example.foodapp.Activities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.R;
import com.example.foodapp.model.Category.Category;
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

    private ImageView foodFavorite;
    private ImageView backArrowImageView;
    private ImageView foodImageView;

    private int imageResourceId;

    private Food food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        initView();

        setOnBackPressedArrow();

        this.foodName.setText(getIntent().getStringExtra("foodDetailNameTextView")); //TODO: item_name?
        this.foodPrice.setText(getIntent().getStringExtra("foodDetailPriceTextView")); //TODO: item_price?
        this.foodCurrency.setText(getIntent().getStringExtra("foodDetailCurrencyTextView"));
        this.foodIngredients.setText(getIntent().getStringExtra("foodDetailIngredients")); //TODO: Update
        //this.foodImageView.setImageResource(getIntent().getIntExtra("foodDetailImageView", R.drawable.southfin_bowls_chicken)); //TODO: update R.drawable?; //TODO: item_image?
        int imageResource = getIntent().getIntExtra("foodDetailImageView", R.drawable.southfin_bowls_chicken);
        this.imageResourceId = imageResource; //TODO: remove?
        Log.d("Debug", "Image Resource: " + imageResource); // Log the image resource //TODO: remove

        //TODO: remove, just for testing;
        /*Resources res = getResources();
        @SuppressLint("ResourceType") String resourceName = res.getResourceEntryName(2131230919); // This will give you the resource name.
        @SuppressLint("ResourceType") String resourceType = res.getResourceTypeName(2131230919); // This will give you the resource type (e.g., "drawable").
        Log.d("Resource Info", "Name: " + resourceName + ", Type: " + resourceType);*/


        this.foodImageView.setImageResource(imageResource);

        this.counterTextView.setText(String.valueOf(this.counter)); //TODO: remove?

        this.addFloatingActionButton.setOnClickListener(view -> {
            this.counterTextView.setText(String.valueOf(++this.counter));
        });

        this.removeFloatingActionButton.setOnClickListener(view -> {
            this.counterTextView.setText(this.counter > 1 ? String.valueOf(--this.counter) : String.valueOf(this.counter));
        });

        // Qui per verificare se era già stato favorito.
        //TODO: mettere in una funzione.
        if(getIntent().getBooleanExtra("foodDetailFavorite", false)) {
            this.foodFavorite.setImageResource(R.drawable.red_heart2);
            this.foodFavorite.setTag("new_image");
            //TODO: aggiungere al database.
        } else {
            this.foodFavorite.setImageResource(R.drawable.heart);
            this.foodFavorite.setTag("initial_image");
            //TODO: rimuovere  dal database.
        }

        //this.food = retrieveFood();

        // Questo per poterlo cliccare nel FoodDetail
        //TODO: uncomment/remove? perché poi va sincronizzato quando torni indietro.
        /*this.foodFavorite.setOnClickListener(view -> {
            if (this.foodFavorite.getTag() == null || this.foodFavorite.getTag().equals("initial_image")) {
                this.foodFavorite.setImageResource(R.drawable.red_heart2);
                this.foodFavorite.setTag("new_image");
                this.food.setFavorite(true);
                //TODO: aggiungere al database.
            } else {
                this.foodFavorite.setImageResource(R.drawable.heart);
                this.foodFavorite.setTag("initial_image");
                this.food.setFavorite(false);
                //TODO: rimuovere  dal database.
            }
        });*/

        addToCart();
    }

    private void setOnBackPressedArrow() {
        this.backArrowImageView.setOnClickListener(view -> onBackPressed());
    }

    private void addToCart() {
        this.addToCartButton.setOnClickListener(view -> {
            Log.d("Counter", ": " + counter); //TODO: just for testing.
            getFoodDatabase().foodDAO().insertFood(retrieveFood()); //TODO: prima era retrieveFood(); this.food

            final AlertDialog.Builder builder = new AlertDialog.Builder(FoodDetailsActivity.this);
            builder.setTitle("Success");
            builder.setMessage("Item added to cart successfully!");
            builder.setCancelable(true);

            builder.setPositiveButton(
                    "Ok",
                    (dialog, id) -> dialog.dismiss());

            final AlertDialog alertDialog = builder.create();
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

        this.foodFavorite = findViewById(R.id.foodDetailFavoriteImageView);
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
                this.foodCurrency.getText().toString(), this.foodPrice.getText().toString(),
                !this.foodFavorite.getTag().equals("new_image"), this.imageResourceId, "{ingredients}"); //TODO: prima era this.foodImageView.getId() al posto di this.imageResourceId
        return food;
    }

    //TODO:
    private void setFoodFavorite() {

    }

}