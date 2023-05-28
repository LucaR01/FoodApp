package com.example.foodapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodapp.R;
import com.example.foodapp.model.Category.Category;
import com.example.foodapp.model.Food.CartFood;
import com.example.foodapp.model.RecycleView.CartFoodAdapter;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView cartRecyclerView;
    private TextView totalPrice;
    private Button checkoutButton;

    private CartFoodAdapter cartFoodAdapter;

    private List<CartFood> cartFoodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initView();

    }

    private void initView() {
        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        totalPrice = findViewById(R.id.totalPriceTextView);
        checkoutButton = findViewById(R.id.checkoutButton);
    }

    private void setCartRecyclerView(final List<CartFood> cartFoodList) {
        this.cartRecyclerView = findViewById(R.id.cartRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        this.cartRecyclerView.setLayoutManager(layoutManager);
        this.cartFoodAdapter = new CartFoodAdapter(cartFoodList);
        this.cartRecyclerView.setAdapter(cartFoodAdapter);
    }

    private void initCartList() {
        cartFoodList = new ArrayList<>();
        cartFoodList.add(new CartFood("Chicken Bowl", Category.SALAD, 3, "$", "8.5", false, R.drawable.southfin_bowls_chicken)); //TODO: remove
    }
}