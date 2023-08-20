package com.example.foodapp.model.RecycleView;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.Databases.FoodDatabase.FoodDatabase;
import com.example.foodapp.model.Food.CartFood;
import com.example.foodapp.model.Food.Food;

import java.util.List;

public class CartFoodAdapter extends RecyclerView.Adapter<CartFoodViewHolder> {

    private List<Food> cartFoodList; //TODO: ArrayList?

    public CartFoodAdapter(final List<Food> cartFoodList) {
        this.cartFoodList = cartFoodList;
    }

    @NonNull
    @Override
    public CartFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartFoodViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false)); //TODO: new ViewHolder?
    }

    @Override
    public void onBindViewHolder(@NonNull CartFoodViewHolder holder, int position) {
        //holder.getItemImage().setImageResource(this.cartFoodList.get(position).getImageUrl()); //FIXME? or just remove? (per passare
        holder.getItemName().setText(this.cartFoodList.get(position).getName());
        holder.getItemQuantity().setText(String.valueOf(this.cartFoodList.get(position).getQuantity()));
        holder.getItemCurrency().setText(this.cartFoodList.get(position).getCurrency());
        holder.getItemPrice().setText(this.cartFoodList.get(position).getPrice());

        holder.itemView.findViewById(R.id.deleteImageView).setOnClickListener(view -> {
            FoodDatabase.getDatabaseInstance(holder.itemView.getContext()).foodDAO().deleteFood(this.cartFoodList.get(position).foodId);
        });
    }

    @Override
    public int getItemCount() {
        return cartFoodList.size();
    }
}
