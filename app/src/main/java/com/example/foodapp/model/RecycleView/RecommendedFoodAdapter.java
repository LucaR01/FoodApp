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
import com.example.foodapp.model.Category.Category;
import com.example.foodapp.model.Databases.FavoriteFoodDatabase.FavoriteFoodDatabase;
import com.example.foodapp.model.Food.FavoriteFood;
import com.example.foodapp.model.Food.Food;
import com.example.foodapp.model.Food.RecommendedFood;

import java.util.List;

public class RecommendedFoodAdapter extends RecyclerView.Adapter<RecommendedFoodViewHolder> {

    private Context context;
    private List<RecommendedFood> recommendedFoodList;

    public RecommendedFoodAdapter(Context context, List<RecommendedFood> recommendedFoodList) {
        this.context = context;
        this.recommendedFoodList = recommendedFoodList;
    }

    @NonNull
    @Override
    public RecommendedFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecommendedFoodViewHolder(LayoutInflater.from(this.context).inflate(R.layout.food_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedFoodViewHolder holder, int position) {
        holder.getItemImage().setImageResource(this.recommendedFoodList.get(position).getRecommendedFood().getImageResourceId());
        holder.getItemName().setText(this.recommendedFoodList.get(position).getRecommendedFood().getName());
        holder.getItemCurrency().setText(this.recommendedFoodList.get(position).getRecommendedFood().getCurrency());
        holder.getItemPrice().setText(this.recommendedFoodList.get(position).getRecommendedFood().getPrice());

        final FavoriteFood favoriteFood = new FavoriteFood(holder.getItemName().getText().toString(), Category.NONE, 1, holder.getItemCurrency().getText().toString(),
                holder.getItemPrice().getText().toString(), false, holder.getItemImage().getId()); //TODO: category e quantity.

        // Questo casomai fosse stato già favorito in precedenza.
        if (this.recommendedFoodList.get(position).getRecommendedFood().isFavorite()) {
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
                this.recommendedFoodList.get(position).getRecommendedFood().setFavorite(true);
                retrieveFavoriteFoodsDatabase().favoriteFoodDAO().insertFavoriteFood(favoriteFood);
            } else {
                holder.getItemFavorite().setImageResource(R.drawable.heart);
                holder.getItemFavorite().setTag("initial_image");
                this.recommendedFoodList.get(position).getRecommendedFood().setFavorite(false);
                retrieveFavoriteFoodsDatabase().favoriteFoodDAO().deleteFavoriteFood(favoriteFood.foodId);
            }
        });

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(this.context, FoodDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //TODO: | FLAG_ACTIVITY_CLEAR_TASK?
            intent.putExtra("foodDetailNameTextView", this.recommendedFoodList.get(holder.getAdapterPosition()).getRecommendedFood().getName()); //TODO: item_name
            intent.putExtra("foodDetailCurrencyTextView", this.recommendedFoodList.get(holder.getAdapterPosition()).getRecommendedFood().getCurrency());
            intent.putExtra("foodDetailFavorite", this.recommendedFoodList.get(holder.getAdapterPosition()).getRecommendedFood().isFavorite()); //TODO: uncomment
            intent.putExtra("foodDetailPriceTextView", this.recommendedFoodList.get(holder.getAdapterPosition()).getRecommendedFood().getPrice()); //TODO: item_price
            intent.putExtra("foodDetailImageView", this.recommendedFoodList.get(holder.getAdapterPosition()).getRecommendedFood().getImageResourceId()); //TODO: item_image
            this.context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return this.recommendedFoodList.size();
    }

    private FavoriteFoodDatabase retrieveFavoriteFoodsDatabase() {
        return FavoriteFoodDatabase.getDatabaseInstance(this.context);
    }
}
