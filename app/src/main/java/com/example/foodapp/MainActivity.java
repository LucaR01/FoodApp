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
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.Activities.CartActivity;
import com.example.foodapp.Activities.CategoryActivity;
import com.example.foodapp.Activities.SettingsActivity;
import com.example.foodapp.Fragments.FoodsFragment;
import com.example.foodapp.Fragments.HomeFragment;
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
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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

    private TextView username;
    private ImageView shoppingCart;

    private ImageView pokeImageView;
    private ImageView fruitImageView;
    private ImageView vegetablesImageView;
    private ImageView saladImageView;

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

        initView();

        onCategoryClickedListener();

        setupNavigationDrawer();

        onCartClickedListener();

        initRecommendedAndFavoriteFoods();

        initDatabase(); //TODO: remove

        initBottomNavigationBar();
    }

    private void initView() {
        //DRAWER LAYOUT
        this.drawerLayout = findViewById(R.id.main_activity_drawer_layout);
        this.constraintLayout = findViewById(R.id.main_activity_constraint_layout);

        //NAVIGATION DRAWER
        this.navigationView = findViewById(R.id.navigation_drawer);
        this.navDrawerMenuImageView = findViewById(R.id.nav_menu_imageView);

        this.username = findViewById(R.id.username);
        this.username.setText(getIntent().getStringExtra("username"));

        this.shoppingCart = findViewById(R.id.shopping_cart);

        this.pokeImageView = findViewById(R.id.pokeImageView);
        this.fruitImageView = findViewById(R.id.fruitImageView);
        this.vegetablesImageView = findViewById(R.id.vegetablesImageView);
        this.saladImageView = findViewById(R.id.saladImageView);
    }

    private void onCategoryClickedListener() {

        this.pokeImageView.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), CategoryActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("category", Category.POKE.toString()); //TODO: prima era poké
            getApplicationContext().startActivity(intent);
        });

        this.fruitImageView.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), CategoryActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("category", Category.FRUIT.toString()); //TODO: prima era fruit
            //startActivity(intent); //TODO: remove/uncomment
            getApplicationContext().startActivity(intent);
        });

        this.vegetablesImageView.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), CategoryActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("category", Category.VEGETABLES.toString()); //TODO: prima era vegetables
            //startActivity(intent); //TODO: remove/uncomment
            getApplicationContext().startActivity(intent);
        });

        this.saladImageView.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), CategoryActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("category", Category.SALAD.toString()); //TODO: prima era salad
            //startActivity(intent); //TODO: remove/uncomment
            getApplicationContext().startActivity(intent);
        });
    }

    private void onCartClickedListener() {
        this.shoppingCart.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, CartActivity.class));
        });
    }

    //TODO: rename in initNavigationDrawer()
    private void setupNavigationDrawer() {

        this.navigationView.setNavigationItemSelectedListener(this);

        //TODO: uncomment
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); //TODO: uncomment maybe.

        //TODO: fix
        navDrawerMenuImageView.setOnClickListener(listener -> {
            drawerLayout.addDrawerListener(drawerToggle);
            drawerToggle.syncState(); //TODO: remove?

            if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
                //drawerLayout.closeDrawer(GravityCompat.START, false);
            } else {
                drawerLayout.openDrawer(GravityCompat.START, false);
            }
        });

        this.navigationView.setNavigationItemSelectedListener(item -> {
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
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*private <T> void setRecyclerView(final List<T> list, final RecyclerView recyclerView) { //TODO: remove/uncomment
        LinearLayoutManager layoutManager(this, )
    }*/

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

    private void setCategoryFoodsRecyclerView(final List<Food> categoryFoodsList) {

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.replace(R.id.frame_layout, fragment); //TODO: uncomment and fix.
        fragmentTransaction.commit();
    }

    //TODO: remove
    private void initDatabase() {
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

        db.userDAO().deleteAll();
    }

    private void initRecommendedAndFavoriteFoods() {
        List<RecommendedFood> recommendedFoodList = new ArrayList<>();
        recommendedFoodList.add(new RecommendedFood(new Food("Poke", Category.POKE, 2, "$","5.00", false, R.drawable.southfin_bowls_chicken)));
        recommendedFoodList.add(new RecommendedFood(new Food("Salad", Category.SALAD, 3, "$","8.00", false, R.drawable.salad_background))); //TODO: cambiare drawable.
        recommendedFoodList.add(new RecommendedFood(new Food("Spinach", Category.VEGETABLES, 1, "$","3.50", false, R.drawable.vegetables_background)));

        setRecommendedFoodsRecyclerView(recommendedFoodList);

        List<FavoriteFood> favoriteFoodList = new ArrayList<>();
        favoriteFoodList.add(new FavoriteFood("Nuts", Category.NUTS, 3, "$", "4.5", R.drawable.fruit_background)); //TODO: update drawable
        favoriteFoodList.add(new FavoriteFood("Cereals", Category.CEREALS, 2, "$", "3.0", R.drawable.poke_background)); //TODO: update drawable

        setFavoriteFoodsRecyclerView(favoriteFoodList); //TODO: uncomment
    }

    private void initBottomNavigationBar() {
        // Bottom Navigation Bar
        this.bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        this.bottomNavigationView.setOnItemSelectedListener(item -> {
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.nav_drawer_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.homeFragment, new HomeFragment()).commit();
                break;

            case R.id.nav_drawer_foods:
                getSupportFragmentManager().beginTransaction().replace(R.id.foodsFragment, new FoodsFragment()).commit();
                break;

            case R.id.settingsFragment:
                getSupportFragmentManager().beginTransaction().replace(R.id.settingsFragment, new SettingsFragment()).commit();
                break;
        }

        this.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}