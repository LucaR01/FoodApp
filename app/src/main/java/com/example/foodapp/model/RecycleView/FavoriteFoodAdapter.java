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
        return new FavoriteFoodViewHolder(LayoutInflater.from(context).inflate(R.layout.food_item, parent, false)); //TODO: update
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteFoodViewHolder holder, int position) {
        holder.getItemImage().setImageResource(this.favoriteFoodList.get(position).getImageUrl());
        holder.getItemCurrency().setText(this.favoriteFoodList.get(position).getCurrency());
        holder.getItemName().setText(this.favoriteFoodList.get(position).getName());
        holder.getItemPrice().setText(this.favoriteFoodList.get(position).getPrice());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(this.context, FoodDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //TODO: | FLAG_ACTIVITY_CLEAR_TASK?
            intent.putExtra("foodDetailNameTextView", this.favoriteFoodList.get(holder.getAdapterPosition()).getName()); //TODO: item_name?
            intent.putExtra("foodDetailCurrencyTextView", this.favoriteFoodList.get(holder.getAdapterPosition()).getCurrency());
            intent.putExtra("foodDetailPriceTextView", this.favoriteFoodList.get(holder.getAdapterPosition()).getPrice()); //TODO: item_price?
            intent.putExtra("foodDetailImageView", this.favoriteFoodList.get(holder.getAdapterPosition()).getImageUrl()); //TODO: item_image?
            this.context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return this.favoriteFoodList.size();
    }
}
