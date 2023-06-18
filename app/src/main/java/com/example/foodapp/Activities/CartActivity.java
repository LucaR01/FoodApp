package com.example.foodapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodapp.R;
import com.example.foodapp.model.Databases.FoodDatabase.FoodDatabase;
import com.example.foodapp.model.Food.Food;
import com.example.foodapp.model.RecycleView.CartFoodAdapter;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private ImageView onBackPressedArrow;

    private RecyclerView cartRecyclerView;
    private TextView currency;
    private TextView totalPrice;
    private Button checkoutButton;

    private CartFoodAdapter cartFoodAdapter;

    private List<Food> cartFoodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initView();

        setOnBackPressedArrow();

        initCartList();

        setCartRecyclerView(this.cartFoodList);

        setTotalPrice();

        completeOrder();

        //cartFoodAdapter.notifyDataSetChanged(); //TODO: remove

    }

    private void setOnBackPressedArrow() {
        this.onBackPressedArrow.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void initView() {
        this.onBackPressedArrow = findViewById(R.id.cartOnBackPressedImageView);
        this.cartRecyclerView = findViewById(R.id.cartRecyclerView);
        this.currency = findViewById(R.id.totalPriceCurrencyTextView);
        this.totalPrice = findViewById(R.id.totalPriceTextView);
        this.checkoutButton = findViewById(R.id.checkoutButton);
    }

    private void setCartRecyclerView(final List<Food> cartFoodList) {
        this.cartRecyclerView = findViewById(R.id.cartRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        this.cartRecyclerView.setLayoutManager(layoutManager);
        this.cartFoodAdapter = new CartFoodAdapter(cartFoodList);
        this.cartRecyclerView.setAdapter(cartFoodAdapter);
    }

    //TODO: rendere pubblica e far passare un CartFood o una lista di CartFood.
    private void initCartList() {
        this.cartFoodList = new ArrayList<>();
        /*this.cartFoodList.add(new CartFood("Chicken Bowl", Category.SALAD, 3, "$", "8.5", false, R.drawable.southfin_bowls_chicken)); //TODO: remove; only for test.
        this.cartFoodList.add(new CartFood("Chicken Bowl", Category.SALAD, 7, "$", "5.99", false, R.drawable.southfin_bowls_chicken)); //TODO: remove*/

        this.cartFoodList = getFoodDatabase().foodDAO().getFoods();

        //TODO: remove; only for test.
        for(var c : this.cartFoodList) {
            Log.d("cartFood name: ", c.getName() + " quantity: " + c.getQuantity() + " price: " + c.getPrice() + " category: " + c.getCategory().toString()
                    + " img: " + c.getImageUrl() + " currency: " + c.getCurrency());
        }
    }

    private String getTotalPrice() {
        float sum = 0.0f;

        for(var c : this.cartFoodList) {
            sum += (Float.parseFloat(c.getPrice()) * c.getQuantity());
        }

        return String.valueOf(sum);
    }

    private void setTotalPrice() {
        //final String totalPriceString = getTotalPrice(); //TODO: add possibility to have multiple currencies.; remove
        //currency.setText(this.currency.toString()); //TODO: remove
        currency.setText("$"); //TODO: update
        //totalPrice.setText(totalPriceString); //TODO: remove
        totalPrice.setText(getTotalPrice());
    }

    //TODO: rename in buyOrder?
    private void completeOrder() {
        //TODO: o alla schermata del pagamento oppure semplicemente azzero il carrello.

        this.checkoutButton.setOnClickListener(view -> {
            this.cartFoodList.clear();
            this.cartRecyclerView.setAdapter(cartFoodAdapter);
            getFoodDatabase().foodDAO().deleteAllFoods();

            //final String totalPriceString = this.currency.toString() + " " + "0.00";
            //this.currency.setText(this.currency.toString()); //TODO: remove
            this.currency.setText("$"); //TODO: update
            this.totalPrice.setText("0.00"); //TODO:
        });
    }

    private FoodDatabase getFoodDatabase() {
        return FoodDatabase.getDatabaseInstance(getApplicationContext());
    }
}