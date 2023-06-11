package com.example.foodapp.model.RecycleView;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
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
        //holder.getItemImage().setImageResource(cartFoodList.get(position).getImageUrl());
        holder.getItemName().setText(cartFoodList.get(position).getName());
        holder.getItemQuantity().setText(String.valueOf(cartFoodList.get(position).getQuantity()));
        holder.getItemCurrency().setText(cartFoodList.get(position).getCurrency());
        holder.getItemPrice().setText(cartFoodList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return cartFoodList.size();
    }
}
