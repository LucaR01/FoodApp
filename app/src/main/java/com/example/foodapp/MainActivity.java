package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodapp.Activities.CartActivity;
import com.example.foodapp.Activities.SettingsActivity;
import com.example.foodapp.Fragments.SettingsFragment;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//TODO: fragment for bottom navigation bar e forse anche per il navigation view.

//TODO: extends Fragment?
public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;
    private ImageView navDrawerMenuImageView;
    private ConstraintLayout constraintLayout;

    private RecyclerView recommendedFoodsRecyclerView;
    private RecommendedFoodAdapter recommendedFoodAdapter;

    private RecyclerView favoriteFoodsRecyclerView;
    private FavoriteFoodAdapter favoriteFoodAdapter;

    private BottomNavigationView bottomNavigationView;

    private ImageView shoppingCart;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //@RequiresApi(api = Build.VERSION_CODES.N) //TODO: remove
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DRAWER LAYOUT
        drawerLayout = findViewById(R.id.main_activity_drawer_layout);
        constraintLayout = findViewById(R.id.main_activity_constraint_layout);

        //NAVIGATION DRAWER
        navigationView = findViewById(R.id.navigation_drawer);
        //TODO: uncomment
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); //TODO: uncomment maybe.

        //TODO: fix
        navDrawerMenuImageView = findViewById(R.id.nav_menu_imageView);
        navDrawerMenuImageView.setOnClickListener(listener -> {
            drawerLayout.addDrawerListener(drawerToggle);
            drawerToggle.syncState(); //TODO: remove?

            if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
                //drawerLayout.closeDrawer(GravityCompat.START, false);
            } else {
                drawerLayout.openDrawer(GravityCompat.START, false);
            }
        });

        navigationView.setNavigationItemSelectedListener(item -> {
            switch(item.getItemId()) {
                case R.id.nav_drawer_home:
                    Toast.makeText(MainActivity.this, "Home selected", Toast.LENGTH_LONG).show();
                    drawerLayout.closeDrawer(GravityCompat.START, false); //TODO: remove
                    //TODO: spostare nella pagina selezionata.
                    break;
                case R.id.nav_drawer_foods:
                    Toast.makeText(MainActivity.this, "Foods Page selected", Toast.LENGTH_LONG).show();
                    drawerLayout.closeDrawers(); //TODO: remove
                    //TODO: spostare nella pagina selezionata.
                    break;
                case R.id.settings:
                    Toast.makeText(MainActivity.this, "Settings selected", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                    //TODO: spostare nella pagina selezionata.
                    break;
                case R.id.nav_drawer_privacy:
                    Toast.makeText(MainActivity.this, "Privacy selected", Toast.LENGTH_LONG).show();
                    //TODO: spostare nella pagina selezionata.
                    break;
                case R.id.nav_drawer_terms_and_conditions:
                    Toast.makeText(MainActivity.this, "Terms&Conditions selected", Toast.LENGTH_LONG).show();
                    //TODO: spostare nella pagina selezionata.
                    break;
            }

            return false;
        });

        shoppingCart = findViewById(R.id.shopping_cart);
        shoppingCart.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, CartActivity.class));
        });

        //TODO: fix/remove
        /*drawerLayout.setOnClickListener(view -> {
            if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
                //drawerLayout.closeDrawer(GravityCompat.START, false); //TODO: uncomment
                drawerLayout.closeDrawers();
            }
        });

        constraintLayout.setOnClickListener(view -> {
            if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START, false);
            }
            //drawerLayout.closeDrawer(GravityCompat.START);
        });*/


        // Qui aggiungo i cibi raccomandati.
        List<RecommendedFood> recommendedFoodList = new ArrayList<>();
        recommendedFoodList.add(new RecommendedFood(new Food("Poke", Category.POKE, "$5.00", false, R.drawable.recommended_food_card_food)));
        recommendedFoodList.add(new RecommendedFood(new Food("Salad", Category.SALAD, "$8.00", false, R.drawable.recommended_food_card_food))); //TODO: cambiare drawable.

        setRecommendedFoodsRecyclerView(recommendedFoodList);

        List<FavoriteFood> favoriteFoodList = new ArrayList<>();
        favoriteFoodList.add(new FavoriteFood("Nuts", Category.NUTS, "$4.5", R.drawable.recommended_food_card_food)); //TODO: update drawable
        favoriteFoodList.add(new FavoriteFood("Cereals", Category.CEREALS, "$3.0", R.drawable.recommended_food_card_food)); //TODO: update drawable

        setFavoriteFoodsRecyclerView(favoriteFoodList); //TODO: uncomment

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

        //TODO: remove
        User bob = new Client("bob", "email@example.com", "1234"); //Optional.empty(); new User
        User veganRestaurant = new Restaurant("restaurant", "vegan@example.com", "a56789b"); // new Restaurant

        db.userDAO().insertList(bob, veganRestaurant);

        List<User> userList = db.userDAO().getUsers(); //TODO: remove
        for(final User user : userList) {
            Log.d("users", user.getUsername() + " " + user.getEmail() + " " + user.getPassword());
        }

        // Bottom Navigation Bar
        bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId()) {
                //TODO: aggiungere l'ultimo item.
                case R.id.home:
                    //replaceFragment(new HomeFragment()); //TODO: uncomment when I will create the fragment.
                    break;
                case R.id.settings:
                    //replaceFragment(new SettingsFragment()); //TODO: uncomment when I will create the fragment.
                    startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                    break;
            }

            return true;
        });
    }

    /*public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { //TODO: remove
        View view = inflater.inflate(R.layout.fragment_foods, container, false);

        // Qui aggiungo i cibi raccomandati.
        List<RecommendedFood> recommendedFoodList = new ArrayList<>();
        recommendedFoodList.add(new RecommendedFood(new Food("Poke", Category.POKE, "$5.00", false, R.drawable.recommended_food_card_food)));
        recommendedFoodList.add(new RecommendedFood(new Food("Salad", Category.SALAD, "$8.00", false, R.drawable.recommended_food_card_food))); //TODO: cambiare drawable.

        setRecommendedFoodsRecyclerView(recommendedFoodList);

        List<FavoriteFood> favoriteFoodList = new ArrayList<>();
        favoriteFoodList.add(new FavoriteFood("Nuts", Category.NUTS, "$4.5", R.drawable.recommended_food_card_food)); //TODO: update drawable
        favoriteFoodList.add(new FavoriteFood("Cereals", Category.CEREALS, "$3.0", R.drawable.recommended_food_card_food)); //TODO: update drawable

        setFavoriteFoodsRecyclerView(favoriteFoodList); //TODO: uncomment
        return view;
    }*/

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
        this.favoriteFoodsRecyclerView = findViewById(R.id.favorite_foods_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        this.favoriteFoodsRecyclerView.setLayoutManager(layoutManager);
        this.favoriteFoodAdapter = new FavoriteFoodAdapter(this, favoriteFoodList);
        this.favoriteFoodsRecyclerView.setAdapter(favoriteFoodAdapter);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.replace(R.id.frame_layout, fragment); //TODO: uncomment and fix.
        fragmentTransaction.commit();
    }
}