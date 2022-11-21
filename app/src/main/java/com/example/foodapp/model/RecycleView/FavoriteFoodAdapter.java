package com.example.foodapp.model.RecycleView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.Food.FavoriteFood;
import com.example.foodapp.model.Food.RecommendedFood;

import java.util.List;

public class FavoriteFoodAdapter extends RecyclerView.Adapter<RecommendedFoodViewHolder> {
    private Context context;
    private List<FavoriteFood> favoriteFoodList;

    public FavoriteFoodAdapter(Context context, List<FavoriteFood> favoriteFoodList) {
        this.context = context;
        this.favoriteFoodList = favoriteFoodList;
    }

    @NonNull
    @Override
    public RecommendedFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecommendedFoodViewHolder(LayoutInflater.from(context).inflate(R.layout.recommended_food_item, parent, false)); //TODO: update
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedFoodViewHolder holder, int position) {
        holder.getItemImage().setImageResource(favoriteFoodList.get(position).getImageUrl());
        holder.getItemName().setText(favoriteFoodList.get(position).getName());
        holder.getItemPrice().setText((int)favoriteFoodList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return this.favoriteFoodList.size();
    }
}
