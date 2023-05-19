package com.example.foodapp.model.RecycleView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Activities.FoodDetailsActivity;
import com.example.foodapp.R;
import com.example.foodapp.model.Food.FavoriteFood;

import java.util.List;

public class FavoriteFoodAdapter extends RecyclerView.Adapter<FavoriteFoodViewHolder> {
    private Context context;
    private List<FavoriteFood> favoriteFoodList;

    public FavoriteFoodAdapter(Context context, List<FavoriteFood> favoriteFoodList) {
        this.context = context;
        this.favoriteFoodList = favoriteFoodList;
    }

    @NonNull
    @Override
    public FavoriteFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavoriteFoodViewHolder(LayoutInflater.from(context).inflate(R.layout.recommended_food_item, parent, false)); //TODO: update
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteFoodViewHolder holder, int position) {
        holder.getItemImage().setImageResource(favoriteFoodList.get(position).getImageUrl());
        holder.getItemName().setText(favoriteFoodList.get(position).getName());
        holder.getItemPrice().setText(favoriteFoodList.get(position).getPrice());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, FoodDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //TODO: | FLAG_ACTIVITY_CLEAR_TASK?
            intent.putExtra("foodDetailNameTextView", favoriteFoodList.get(holder.getAdapterPosition()).getName()); //TODO: item_name?
            intent.putExtra("foodDetailPriceTextView", favoriteFoodList.get(holder.getAdapterPosition()).getPrice()); //TODO: item_price?
            intent.putExtra("foodDetailImageView", favoriteFoodList.get(holder.getAdapterPosition()).getImageUrl()); //TODO: item_image?
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return this.favoriteFoodList.size();
    }
}
