package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.foodapp.model.Category.Category;
import com.example.foodapp.model.Databases.UserDatabase.UserDatabase;
import com.example.foodapp.model.Food.FavoriteFood;
import com.example.foodapp.model.Food.Food;
import com.example.foodapp.model.Food.RecommendedFood;
import com.example.foodapp.model.RecycleView.FavoriteFoodAdapter;
import com.example.foodapp.model.RecycleView.RecommendedFoodAdapter;
import com.example.foodapp.model.Users.Client.Client;
import com.example.foodapp.model.Users.Restaurant.Restaurant;
import com.example.foodapp.model.Users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recommendedFoodsRecyclerView;
    private RecommendedFoodAdapter recommendedFoodAdapter;

    private RecyclerView favoriteFoodsRecyclerView;
    private FavoriteFoodAdapter favoriteFoodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Qui aggiungo i cibi raccomandati.
        List<RecommendedFood> recommendedFoodList = new ArrayList<>();
        recommendedFoodList.add(new RecommendedFood(new Food("Poke", Category.POKE, "$5.00", false, R.drawable.recommended_food_card_food)));
        recommendedFoodList.add(new RecommendedFood(new Food("Salad", Category.SALAD, "$8.00", false, R.drawable.recommended_food_card_food))); //TODO: cambiare drawable.

        setRecommendedFoodsRecyclerView(recommendedFoodList);

        List<FavoriteFood> favoriteFoodList = new ArrayList<>();
        favoriteFoodList.add(new FavoriteFood("", Category.NUTS, "$4.5", R.drawable.recommended_food_card_food)); //TODO: update drawable
        favoriteFoodList.add(new FavoriteFood("Cereals", Category.CEREALS, "$3.0", R.drawable.recommended_food_card_food)); //TODO: update drawable

        //setFavoriteFoodsRecyclerView(favoriteFoodList); //TODO: uncomment

        // Database
        UserDatabase db = UserDatabase.getDatabaseInstance(getApplicationContext());
        /*if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) { //TODO: uncomment or remove?
            User bob = new User("bob", "email@com", "1234", Optional.of(""), Optional.of("")); //Optional.empty()
            User veganRestaurant = new User("restaurant", "vegan@com", "a56789b", Optional.of(""), Optional.of(""));

            db.userDAO().insertList(bob, veganRestaurant);

            List<User> userList = db.userDAO().getUsers();
            for(User user : userList) {
                Log.d("users", user.getUsername() + " " + user.getEmail() + " " + user.getPassword());
            }
        }*/

        User bob = new User("bob", "email@example.com", "1234"); //Optional.empty(); new User
        User veganRestaurant = new User("restaurant", "vegan@example.com", "a56789b"); // new Restaurant

        db.userDAO().insertList(bob, veganRestaurant);

        List<User> userList = db.userDAO().getUsers(); //TODO: remove
        for(User user : userList) {
            Log.d("users", user.getUsername() + " " + user.getEmail() + " " + user.getPassword());
        }
    }

    private void setRecommendedFoodsRecyclerView(final List<RecommendedFood> recommendedFoodList) {
        this.recommendedFoodsRecyclerView = findViewById(R.id.recommended_foods_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        this.recommendedFoodsRecyclerView.setLayoutManager(layoutManager);
        this.recommendedFoodAdapter = new RecommendedFoodAdapter(this, recommendedFoodList);
        this.recommendedFoodsRecyclerView.setAdapter(recommendedFoodAdapter);
    }

    private void setFavoriteFoodsRecyclerView(final List<FavoriteFood> favoriteFoodList) {
        this.favoriteFoodsRecyclerView = findViewById(R.id.recommended_foods_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        this.favoriteFoodsRecyclerView.setLayoutManager(layoutManager);
        this.favoriteFoodAdapter = new FavoriteFoodAdapter(this, favoriteFoodList);
        this.favoriteFoodsRecyclerView.setAdapter(recommendedFoodAdapter);
    }
}