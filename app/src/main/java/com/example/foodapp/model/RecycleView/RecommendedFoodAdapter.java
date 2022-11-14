package com.example.foodapp.model.RecycleView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;

import java.util.List;

public class RecommendedFoodAdapter extends RecyclerView.Adapter<RecommendedFoodViewHolder> { //TODO: RecommendedFoodAdapter.RecommendedFoodViewHolder; public?

    /*public static final class RecommendedFoodViewHolder extends RecyclerView.ViewHolder {

        public RecommendedFoodViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }*/

    private Context context;
    private List<RecommendedFood> recommendedFoodList;

    public RecommendedFoodAdapter(Context context, List<RecommendedFood> recommendedFoodList) {
        this.context = context;
        this.recommendedFoodList = recommendedFoodList;
    }

    @NonNull
    @Override
    public RecommendedFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecommendedFoodViewHolder(LayoutInflater.from(context).inflate(R.layout.recommended_food_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedFoodViewHolder holder, int position) {
        holder.getItemImage().setImageResource(recommendedFoodList.get(position).getRecommendedFood().getImageUrl());
        holder.getItemName().setText(recommendedFoodList.get(position).getRecommendedFood().getName());
        holder.getItemPrice().setText((int)recommendedFoodList.get(position).getRecommendedFood().getPrice());
    }

    @Override
    public int getItemCount() {
        return this.recommendedFoodList.size();
    }
}
