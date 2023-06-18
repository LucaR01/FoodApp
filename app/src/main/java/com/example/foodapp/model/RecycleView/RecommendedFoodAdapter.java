package com.example.foodapp.model.RecycleView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Activities.FoodDetailsActivity;
import com.example.foodapp.R;
import com.example.foodapp.model.Food.RecommendedFood;

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
        return new RecommendedFoodViewHolder(LayoutInflater.from(context).inflate(R.layout.food_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedFoodViewHolder holder, int position) {
        holder.getItemImage().setImageResource(recommendedFoodList.get(position).getRecommendedFood().getImageUrl());
        holder.getItemName().setText(recommendedFoodList.get(position).getRecommendedFood().getName());
        holder.getItemCurrency().setText(recommendedFoodList.get(position).getRecommendedFood().getCurrency());
        holder.getItemPrice().setText(recommendedFoodList.get(position).getRecommendedFood().getPrice());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, FoodDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //TODO: | FLAG_ACTIVITY_CLEAR_TASK?
            intent.putExtra("foodDetailNameTextView", this.recommendedFoodList.get(holder.getAdapterPosition()).getRecommendedFood().getName()); //TODO: item_name
            intent.putExtra("foodDetailCurrencyTextView", this.recommendedFoodList.get(holder.getAdapterPosition()).getRecommendedFood().getCurrency());
            intent.putExtra("foodDetailPriceTextView", this.recommendedFoodList.get(holder.getAdapterPosition()).getRecommendedFood().getPrice()); //TODO: item_price
            intent.putExtra("foodDetailImageView", this.recommendedFoodList.get(holder.getAdapterPosition()).getRecommendedFood().getImageUrl()); //TODO: item_image
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return this.recommendedFoodList.size();
    }
}
