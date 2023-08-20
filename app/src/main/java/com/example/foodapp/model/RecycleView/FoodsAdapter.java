package com.example.foodapp.model.RecycleView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Activities.FoodDetailsActivity;
import com.example.foodapp.R;
import com.example.foodapp.model.Food.Food;

import java.util.List;

public class FoodsAdapter extends RecyclerView.Adapter<FoodsViewHolder> {

    private Context context;
    private List<Food> foodsList;

    public FoodsAdapter(Context context, final List<Food> foodsList) {
        this.context = context;
        this.foodsList = foodsList;
    }

    @NonNull
    @Override
    public FoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.foods_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodsViewHolder holder, int position) {
        holder.getItemImage().setImageResource(this.foodsList.get(position).getImageUrl());
        holder.getItemName().setText(this.foodsList.get(position).getName());
        holder.getItemCurrency().setText(this.foodsList.get(position).getCategory().toString());
        holder.getItemQuantity().setText(String.valueOf(this.foodsList.get(position).getQuantity()));
        holder.getItemCurrency().setText(this.foodsList.get(position).getCurrency());
        holder.getItemPrice().setText(this.foodsList.get(position).getPrice());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, FoodDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //TODO: | FLAG_ACTIVITY_CLEAR_TASK?
            intent.putExtra("foodDetailNameTextView", this.foodsList.get(holder.getAdapterPosition()).getName());
            intent.putExtra("foodDetailCurrencyTextView", this.foodsList.get(holder.getAdapterPosition()).getCurrency());
            intent.putExtra("foodDetailPriceTextView", this.foodsList.get(holder.getAdapterPosition()).getPrice());
            intent.putExtra("foodDetailImageView", this.foodsList.get(holder.getAdapterPosition()).getImageUrl());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return this.foodsList.size();
    }
}
