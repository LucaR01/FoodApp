package com.example.foodapp.model.RecycleView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Activities.FoodDetailsActivity;
import com.example.foodapp.R;
import com.example.foodapp.model.Databases.FoodDatabase.FoodDatabase;
import com.example.foodapp.model.Food.FavoriteFood;

import java.util.List;

public class FavoriteFoodsListAdapter extends RecyclerView.Adapter<FavoriteFoodsListViewHolder>{

    private final Context context;
    private final List<FavoriteFood> favoriteFoodsList;

    public FavoriteFoodsListAdapter(final Context context, final List<FavoriteFood> favoriteFoodsList) {
        this.context = context;
        this.favoriteFoodsList = favoriteFoodsList;
    }

    @NonNull
    @Override
    public FavoriteFoodsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavoriteFoodsListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_foods_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteFoodsListViewHolder holder, int position) {
        holder.getItemImage().setImageResource(this.favoriteFoodsList.get(position).getImageUrl());
        holder.getItemName().setText(this.favoriteFoodsList.get(position).getName());
        //holder.getItemCategory().setText(this.favoriteFoodsList.get(position).getCategory().toString()); //TODO: uncomment when fixed.
        holder.getItemQuantity().setText(String.valueOf(this.favoriteFoodsList.get(position).getQuantity()));
        holder.getItemCurrency().setText(this.favoriteFoodsList.get(position).getCurrency());
        holder.getItemPrice().setText(this.favoriteFoodsList.get(position).getPrice());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(this.context, FoodDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //TODO: | FLAG_ACTIVITY_CLEAR_TASK?
            intent.putExtra("foodDetailNameTextView", this.favoriteFoodsList.get(holder.getAdapterPosition()).getName());
            intent.putExtra("foodDetailCurrencyTextView", this.favoriteFoodsList.get(holder.getAdapterPosition()).getCurrency());
            intent.putExtra("foodDetailFavorite", this.favoriteFoodsList.get(holder.getAdapterPosition()).isFavorite()); //TODO: uncomment
            intent.putExtra("foodDetailPriceTextView", this.favoriteFoodsList.get(holder.getAdapterPosition()).getPrice());
            intent.putExtra("foodDetailImageView", this.favoriteFoodsList.get(holder.getAdapterPosition()).getImageUrl());
            this.context.startActivity(intent);
        });

        holder.itemView.findViewById(R.id.favoriteFoodsDeleteImageView).setOnClickListener(view -> {
            //FoodDatabase.getDatabaseInstance(holder.itemView.getContext()).foodDAO().deleteFood(this.favoriteFoodsList.get(position).foodId); //TODO: change database
            this.favoriteFoodsList.remove(position);
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return this.favoriteFoodsList.size();
    }
}
