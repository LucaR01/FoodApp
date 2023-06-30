package com.example.foodapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodapp.R;

public class CategoryActivity extends AppCompatActivity {

    private ImageView categoryImageView;
    private TextView categoryName;
    private RecyclerView categoryRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        initView();
    }

    private void initView() {
        this.categoryImageView = findViewById(R.id.categoryImageView);
        this.categoryName = findViewById(R.id.categoryTextView);
        this.categoryRecyclerView = findViewById(R.id.categoryRecyclerView);
    }
}