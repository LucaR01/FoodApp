package com.example.foodapp.model.RecycleView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Activities.FoodDetailsActivity;
import com.example.foodapp.R;
import com.example.foodapp.model.Category.Category;
import com.example.foodapp.model.Databases.FavoriteFoodDatabase.FavoriteFoodDatabase;
import com.example.foodapp.model.Food.FavoriteFood;

import java.util.List;

public class FavoriteFoodAdapter extends RecyclerView.Adapter<FavoriteFoodViewHolder> {
    private final Context context;
    private final List<FavoriteFood> favoriteFoodList;

    public FavoriteFoodAdapter(Context context, List<FavoriteFood> favoriteFoodList) {
        this.context = context;
        this.favoriteFoodList = favoriteFoodList;
    }

    @NonNull
    @Override
    public FavoriteFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavoriteFoodViewHolder(LayoutInflater.from(this.context).inflate(R.layout.food_item, parent, false)); //TODO: update
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteFoodViewHolder holder, int position) {
        holder.getItemImage().setImageResource(this.favoriteFoodList.get(position).getImageResourceId());
        holder.getItemCurrency().setText(this.favoriteFoodList.get(position).getCurrency());
        holder.getItemName().setText(this.favoriteFoodList.get(position).getName());
        holder.getItemPrice().setText(this.favoriteFoodList.get(position).getPrice());

        holder.getItemImage().setTag(this.favoriteFoodList.get(position).getImageResourceId());

        final FavoriteFood favoriteFood = new FavoriteFood(holder.getItemName().getText().toString(), Category.NONE, 1, holder.getItemCurrency().getText().toString(),
                holder.getItemPrice().getText().toString(), false, (int)holder.getItemImage().getTag(), "{ingredients}"); //TODO: category e quantity; prima era getItemimage().getId()

        // Questo casomai fosse stato già favorito in precedenza.
        if (this.favoriteFoodList.get(position).isFavorite()) {
            holder.getItemFavorite().setImageResource(R.drawable.red_heart2);
            favoriteFood.setFavorite(true);
        } else {
            holder.getItemFavorite().setImageResource(R.drawable.heart);
            favoriteFood.setFavorite(false);
        }

        holder.getItemFavorite().setOnClickListener(view -> {
            if (holder.getItemFavorite().getTag() == null || holder.getItemFavorite().getTag().equals("initial_image")) {
                holder.getItemFavorite().setImageResource(R.drawable.red_heart2);
                holder.getItemFavorite().setTag("new_image");
                this.favoriteFoodList.get(position).setFavorite(true);
                FavoriteFoodDatabase.getDatabaseInstance(this.context).favoriteFoodDAO().insertFavoriteFood(favoriteFood);
            } else {
                holder.getItemFavorite().setImageResource(R.drawable.heart);
                holder.getItemFavorite().setTag("initial_image");
                this.favoriteFoodList.get(position).setFavorite(false);
                FavoriteFoodDatabase.getDatabaseInstance(this.context).favoriteFoodDAO().deleteFavoriteFood(favoriteFood.foodId);
            }
        });

        //setItemFavorite(holder, position, favoriteFood); //TODO: remove?/uncomment?

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(this.context, FoodDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //TODO: | FLAG_ACTIVITY_CLEAR_TASK?
            intent.putExtra("foodDetailNameTextView", this.favoriteFoodList.get(holder.getAdapterPosition()).getName()); //TODO: item_name?
            intent.putExtra("foodDetailCurrencyTextView", this.favoriteFoodList.get(holder.getAdapterPosition()).getCurrency());
            intent.putExtra("foodDetailFavorite", this.favoriteFoodList.get(holder.getAdapterPosition()).isFavorite());
            intent.putExtra("foodDetailPriceTextView", this.favoriteFoodList.get(holder.getAdapterPosition()).getPrice()); //TODO: item_price?
            intent.putExtra("foodDetailImageView", this.favoriteFoodList.get(holder.getAdapterPosition()).getImageResourceId()); //TODO: item_image?
            intent.putExtra("foodDetailIngredients", this.favoriteFoodList.get(holder.getAdapterPosition()).getIngredients());
            this.context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return this.favoriteFoodList.size();
    }

    //TODO: remove?
    private void setItemFavorite(@NonNull FavoriteFoodViewHolder holder, int position, final FavoriteFood favoriteFood) {
        // Questo casomai fosse stato già favorito in precedenza.
        if (this.favoriteFoodList.get(position).isFavorite()) {
            holder.getItemFavorite().setImageResource(R.drawable.red_heart2);
            //FavoriteFoodDatabase.getDatabaseInstance(this.context).favoriteFoodDAO().insertFavoriteFood(favoriteFood); //TODO: comment/remove?
        } else {
            holder.getItemFavorite().setImageResource(R.drawable.heart);
        }

        holder.getItemFavorite().setOnClickListener(view -> {
            if (holder.getItemFavorite().getTag() == null || holder.getItemFavorite().getTag().equals("initial_image")) {
                holder.getItemFavorite().setImageResource(R.drawable.red_heart2);
                holder.getItemFavorite().setTag("new_image");
                this.favoriteFoodList.get(position).setFavorite(true);
                FavoriteFoodDatabase.getDatabaseInstance(this.context).favoriteFoodDAO().insertFavoriteFood(favoriteFood);
            } else {
                holder.getItemFavorite().setImageResource(R.drawable.heart);
                holder.getItemFavorite().setTag("initial_image");
                this.favoriteFoodList.get(position).setFavorite(false);
                FavoriteFoodDatabase.getDatabaseInstance(this.context).favoriteFoodDAO().deleteFavoriteFood(favoriteFood.foodId);
            }
        });
    }
}
