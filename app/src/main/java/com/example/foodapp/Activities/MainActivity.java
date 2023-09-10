package com.example.foodapp.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.Category.Category;
import com.example.foodapp.model.Currency.Currency;
import com.example.foodapp.model.Databases.FavoriteFoodDatabase.FavoriteFoodDatabase;
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
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

//TODO: fragment for bottom navigation bar e forse anche per il navigation view.

//TODO: extends Fragment?
//TODO: implements NavigationView.OnNavigationItemSelectedListener
public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView navMenuImageView;

    private RecyclerView recommendedFoodsRecyclerView;
    private RecommendedFoodAdapter recommendedFoodAdapter;

    private RecyclerView favoriteFoodsRecyclerView;
    private FavoriteFoodAdapter favoriteFoodAdapter;

    private BottomNavigationView bottomNavigationView;

    private TextView username;
    private TextView navDrawerUsername;
    private TextView navDrawerCurrency;
    private TextView navDrawerBalance;
    private ImageView shoppingCart;

    private ImageView pokeImageView;
    private ImageView fruitImageView;
    private ImageView vegetablesImageView;
    private ImageView saladImageView;

    //@RequiresApi(api = Build.VERSION_CODES.N) //TODO: remove
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        navigationDrawerListener();

        onCategoryClickedListener();

        //setupNavigationDrawer(); //TODO: uncomment/remove?

        onCartClickedListener();

        initRecommendedAndFavoriteFoods();

        initUserDatabase(); //TODO: remove

        initBottomNavigationBar();
    }

    private void initView() {
        this.drawerLayout = findViewById(R.id.main_activity_drawer_layout);
        this.navigationView = findViewById(R.id.navigation_drawer);
        this.navigationView.bringToFront(); // Per evitare che si chiudi ad ogni click.
        this.navMenuImageView = findViewById(R.id.nav_menu_imageView);

        final String usernameText =  getIntent().getStringExtra("username");

        this.navDrawerUsername = findViewById(R.id.navDrawerHeaderUsername);
        this.navDrawerUsername.setText(usernameText);

        this.navDrawerCurrency = findViewById(R.id.navDrawerHeaderCurrency);
        this.navDrawerCurrency.setText(Currency.DEFAULT_CURRENCY.toString());

        this.navDrawerBalance = findViewById(R.id.navDrawerHeaderBalance);
        this.navDrawerBalance.setText(getIntent().getStringExtra("balance"));

        this.username = findViewById(R.id.username);
        this.username.setText(usernameText);

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
        this.shoppingCart.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, CartActivity.class)));
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

    //TODO: remove?
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.replace(R.id.frame_layout, fragment); //TODO: uncomment and fix.
        fragmentTransaction.commit();
    }

    //TODO: remove; just for testing
    private void initUserDatabase() {
        UserDatabase db = UserDatabase.getDatabaseInstance(getApplicationContext());

        //TODO: remove
        User bob = new Client("bob", "bob@example.com", "1234", 0.0); //Optional.empty(); new User
        User veganRestaurant = new Restaurant("restaurant", "vegan@example.com", "a56789b", 0.0); // new Restaurant

        db.userDAO().insertList(bob, veganRestaurant);

        List<User> userList = db.userDAO().getUsers(); //TODO: remove
        for(final User user : userList) {
            Log.d("users", user.getUsername() + " " + user.getEmail() + " " + user.getPassword());
        }

        db.userDAO().deleteAll();
    }

    private void initRecommendedAndFavoriteFoods() {
        List<RecommendedFood> recommendedFoodList = new ArrayList<>();
        recommendedFoodList.add(new RecommendedFood(new Food("Chicken Poké", Category.POKE, 1, "$", "5.60", false, R.drawable.southfin_bowls_chicken,
                "Pollo, salsa di soia o tamari per marinare, olio di sesamo, aceto di riso, zenzero, aglio, cipollotti, semi di sesamo tostati, e coriandolo fresco per guarnire.")));
        recommendedFoodList.add(new RecommendedFood(new Food("Greek Salad", Category.SALAD, 1, "$", "4.50", false, R.drawable.greek_salad, "{ingredients}")));
        recommendedFoodList.add(new RecommendedFood(new Food("Caprese Salad", Category.VEGETABLES, 1, "$", "4.50", false, R.drawable.caprese_salad_no_background, "{ingredients}")));
        recommendedFoodList.add(new RecommendedFood(new Food("Grilled Chicken Caeser", Category.SALAD, 1, "$", "7.50", false, R.drawable.grilled_chicken_caesar_salad, "{ingredients}")));

        setRecommendedFoodsRecyclerView(recommendedFoodList);

        List<FavoriteFood> favoriteFoodList = new ArrayList<>();
        favoriteFoodList.add(new FavoriteFood("Strawberry Pairfait", Category.FRUIT, 1, "$", "7.50", true, R.drawable.strawberry_parfait_ice_cream, "{ingredients}"));
        favoriteFoodList.add(new FavoriteFood("Avocado Lachs Poké Bowl", Category.POKE, 1, "$", "5.50", true, R.drawable.avocado_lachs_poke_bowl_no_background, "{ingredients}"));
        favoriteFoodList.add(new FavoriteFood("Ratatouille", Category.VEGETABLES, 1, "$", "5.99", true, R.drawable.ratatouille_no_background, "{ingredients}"));
        favoriteFoodList.add(new FavoriteFood("Fruit Salsa Cinnamon", Category.FRUIT, 1, "$", "8.50", true, R.drawable.tangy_fruit_salsa_with_cinnamon_chips_no_background, "{ingredients}"));

        for(var fav : favoriteFoodList) {
            FavoriteFoodDatabase.getDatabaseInstance(getApplicationContext()).favoriteFoodDAO().insertFavoriteFood(fav);
        }

        setFavoriteFoodsRecyclerView(favoriteFoodList);
    }

    @SuppressLint("NonConstantResourceId")
    private void initBottomNavigationBar() {
        this.bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        this.bottomNavigationView.setSelectedItemId(R.id.bottom_home);

        this.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId()) {
                case R.id.bottom_home:
                    return true;
                case R.id.bottom_settings:
                    startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                    break;
                case R.id.bottom_foods:
                    startActivity(new Intent(MainActivity.this, FoodsActivity.class));
                    break;
            }
            return true;
        });
    }

    @SuppressLint("NonConstantResourceId")
    private void navigationDrawerListener() {
        this.navMenuImageView.setOnClickListener(v -> {
            if (this.drawerLayout.isDrawerOpen(this.navigationView)) {
                this.drawerLayout.closeDrawer(this.navigationView);
            } else {
                this.drawerLayout.openDrawer(this.navigationView);
            }
        });

        this.navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    switch (menuItem.getItemId()) {
                        case R.id.nav_drawer_home:
                            Log.d("Navigation", "Home");
                            return true;
                        case R.id.nav_drawer_favorite_foods:
                            Log.d("Navigation", "FavoriteFoods");
                            startActivity(new Intent(MainActivity.this, FavoriteFoodsActivity.class));
                            break;
                        case R.id.nav_drawer_about:
                            Log.d("Navigation", "About");
                            startActivity(new Intent(MainActivity.this, AboutActivity.class));
                            break;
                        case R.id.nav_drawer_settings:
                            Log.d("Navigation", "Settings");
                            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                            break;
                        case R.id.nav_drawer_privacy:
                            Log.d("Navigation", "Privacy Policy");
                            startActivity(new Intent(MainActivity.this, PrivacyPolicyActivity.class));
                            break;
                        case R.id.nav_drawer_terms_and_conditions:
                            Log.d("Navigation", "Terms & Conditions");
                            startActivity(new Intent(MainActivity.this, TermsAndConditionsActivity.class));
                            break;
                    }

                    // Close the drawer when an item is clicked
                    this.drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                });
    }
}