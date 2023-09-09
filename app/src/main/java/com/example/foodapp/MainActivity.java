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

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.Activities.AboutActivity;
import com.example.foodapp.Activities.CartActivity;
import com.example.foodapp.Activities.CategoryActivity;
import com.example.foodapp.Activities.FavoriteFoodsActivity;
import com.example.foodapp.Activities.FoodsActivity;
import com.example.foodapp.Activities.PrivacyPolicyActivity;
import com.example.foodapp.Activities.SettingsActivity;
import com.example.foodapp.Activities.TermsAndConditionsActivity;
import com.example.foodapp.Fragments.FoodsFragment;
import com.example.foodapp.Fragments.HomeFragment;
import com.example.foodapp.Fragments.SettingsFragment;
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
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        //DRAWER LAYOUT
        /*this.drawerLayout = findViewById(R.id.main_activity_drawer_layout);
        this.constraintLayout = findViewById(R.id.main_activity_constraint_layout);

        //NAVIGATION DRAWER
        this.navigationView = findViewById(R.id.navigation_drawer);
        this.navDrawerMenuImageView = findViewById(R.id.nav_menu_imageView);*/

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

    //TODO: rename in initNavigationDrawer()
    /*private void setupNavigationDrawer() {

        //this.navigationView.setNavigationItemSelectedListener(this);

        //TODO: uncomment
        this.drawerToggle = new ActionBarDrawerToggle(this, this.drawerLayout, R.string.open, R.string.close);
        this.drawerLayout.addDrawerListener(this.drawerToggle);
        this.drawerToggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); //TODO: uncomment maybe.

        this.navigationView.setNavigationItemSelectedListener(this);*/

        //TODO: fix
        /*navDrawerMenuImageView.setOnClickListener(listener -> {
            //drawerLayout.addDrawerListener(drawerToggle);
            this.drawerToggle.syncState(); //TODO: remove?

            if(this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                //drawerLayout.closeDrawer(GravityCompat.START, false);
            } else {
                this.drawerLayout.openDrawer(GravityCompat.START, false);
            }
        });*/

        //TODO: uncomment? prima era uncommentato.
        /*this.navDrawerMenuImageView.setOnClickListener(view -> {
            if (!this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                this.drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        View rootView = findViewById(android.R.id.content); // Get the root view of your activity

        rootView.setOnClickListener(view -> {
            if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                this.drawerLayout.closeDrawer(GravityCompat.START);
            }
        });*/



        //TODO: remove, c'è la funzione sotto.
        /*this.navigationView.setNavigationItemSelectedListener(item -> {
            switch(item.getItemId()) {
                case R.id.nav_drawer_home:
                    Toast.makeText(MainActivity.this, "Home selected", Toast.LENGTH_LONG).show();
                    System.out.println("Home!"); //TODO: remove
                    //drawerLayout.closeDrawer(GravityCompat.START, false); //TODO: remove
                    //TODO: spostare nella pagina selezionata.
                    break;
                case R.id.nav_drawer_favorite_foods:
                    Toast.makeText(MainActivity.this, "Foods Page selected", Toast.LENGTH_LONG).show();
                    System.out.println("Foods!"); //TODO: remove
                    //drawerLayout.closeDrawers(); //TODO: remove
                    //TODO: spostare nella pagina selezionata.
                    break;
                case R.id.nav_drawer_settings:
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

            this.drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });*/

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
    //}

    /*@Override
    public void onBackPressed() {
        if(this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }*/

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

    //TODO: remove
    private void setCategoryFoodsRecyclerView(final List<Food> categoryFoodsList) {

    }

    //TODO: remove?
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.replace(R.id.frame_layout, fragment); //TODO: uncomment and fix.
        fragmentTransaction.commit();
    }

    //TODO: remove
    private void initUserDatabase() {
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
                            startActivity(new Intent(MainActivity.this, FoodsActivity.class));
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
                    //drawerLayout.closeDrawer(navigationView); //TODO: remove?
                    /*if(menuItem.getItemId() != R.id.nav_menu_imageView) {
                        drawerLayout.closeDrawer(navigationView);
                    }*/
                    return true;
                });
    }

    /*@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }*/

    /*@SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.nav_drawer_home:
                Log.d("Navigation", "Home clicked");
                //getSupportFragmentManager().beginTransaction().replace(R.id.homeFragment, new HomeFragment()).commit();
                //TODO: startActivity(new Intent(MainActivity.this, MainActivity.class)); ?
                //TODO: return true?
                break;

            case R.id.nav_drawer_favorite_foods:
                Log.d("Navigation", "Favorite Foods");
                //getSupportFragmentManager().beginTransaction().replace(R.id.foodsFragment, new FoodsFragment()).commit(); //TODO: uncomment || remove
                startActivity(new Intent(MainActivity.this, FavoriteFoodsActivity.class)); //TODO: prima era FoodsActivity
                break;

            case R.id.nav_drawer_about:
                Log.d("Navigation", "About");
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                break;

            case R.id.nav_drawer_settings:
                Log.d("Navigation", "Settings");
                //TODO: startActivity(new Intent(MainActivity.this, SettingsActivity.class)); ?
                getSupportFragmentManager().beginTransaction().replace(R.id.settingsFragment, new SettingsFragment()).commit();
                break;
            case R.id.nav_drawer_privacy:
                Log.d("Navigation", "Privacy Policy");
                //getSupportFragmentManager().beginTransaction().replace(R.id.settingsFragment, new PrivacyPolicyFragment()).commit(); //TODO: uncomment when ready.
                startActivity(new Intent(MainActivity.this, PrivacyPolicyActivity.class));
                break;
            case R.id.nav_drawer_terms_and_conditions:
                Log.d("Navigation", "Terms & Conditions");
                //getSupportFragmentManager().beginTransaction().replace(R.id.settingsFragment, new TermsAndConditionsFragment()).commit(); //TODO: uncomment when ready.
                startActivity(new Intent(MainActivity.this, TermsAndConditionsActivity.class));
                break;
        }

        //this.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }*/

    /*@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {*/
        //TODO: remove?
        /*if(drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);*/

        /*if(this.drawerToggle.onOptionsItemSelected(item)) {
            return true;
        } else if(item.getItemId() == R.id.nav_menu_imageView) {
            if(!this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                this.drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                this.drawerLayout.openDrawer(GravityCompat.START);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        this.drawerToggle.syncState();
    }*/
}