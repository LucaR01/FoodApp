package com.example.foodapp.model.RecycleView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Activities.FoodDetailsActivity;
import com.example.foodapp.R;
import com.example.foodapp.model.Food.Food;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private Context context;
    private List<Food> categoryFoodsList;

    public CategoryAdapter(Context context, final List<Food> categoryFoodsList) {
        this.context = context;
        this.categoryFoodsList = categoryFoodsList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.getItemImage().setImageResource(this.categoryFoodsList.get(position).getImageUrl());
        holder.getItemName().setText(this.categoryFoodsList.get(position).getName());
        holder.getItemQuantity().setText(String.valueOf(this.categoryFoodsList.get(position).getQuantity()));
        holder.getItemCurrency().setText(this.categoryFoodsList.get(position).getCurrency());
        holder.getItemPrice().setText(this.categoryFoodsList.get(position).getPrice());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, FoodDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //TODO: | FLAG_ACTIVITY_CLEAR_TASK?
            intent.putExtra("foodDetailNameTextView", this.categoryFoodsList.get(holder.getAdapterPosition()).getName());
            intent.putExtra("foodDetailCurrencyTextView", this.categoryFoodsList.get(holder.getAdapterPosition()).getCurrency());
            intent.putExtra("foodDetailPriceTextView", this.categoryFoodsList.get(holder.getAdapterPosition()).getPrice());
            intent.putExtra("foodDetailImageView", this.categoryFoodsList.get(holder.getAdapterPosition()).getImageUrl());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return this.categoryFoodsList.size();
    }
}
