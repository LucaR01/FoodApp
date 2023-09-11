package com.example.foodapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.R;
import com.example.foodapp.model.Databases.FavoriteFoodDatabase.FavoriteFoodDatabase;
import com.example.foodapp.model.Databases.FoodDatabase.FoodDatabase;
import com.example.foodapp.model.Databases.UserDatabase.UserDatabase;
import com.example.foodapp.model.Users.Client.Client;
import com.example.foodapp.model.Users.Restaurant.Restaurant;
import com.example.foodapp.model.Users.User;

import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    //private Button joinUsButton; //TODO: uncomment only if i use layout.activity_welcome2
    private Button loginButton;
    private TextView signupTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initView();

        //initDatabase(); //TODO: remove/comment

        //onJoinUs(); //TODO: uncomment only if i use layout.activity_welcome2

        onLogin();

        onSignUp();

        //this.welcomeScreenImageView.animate().translationY(-1500).setDuration(1000).setStartDelay(4000); //TODO: uncomment
    }

    private void initView() {
        //this.joinUsButton = findViewById(R.id.joinUsButton); //TODO: uncomment only if i use layout.activity_welcome2
        this.loginButton = findViewById(R.id.welcome_login_button);
        this.signupTextView = findViewById(R.id.signup_textview);
    }

    private void onLogin(){
        this.loginButton.setOnClickListener(view -> startActivity(new Intent(WelcomeActivity.this, LoginActivity.class)));
    }

    private void onSignUp(){
        this.signupTextView.setOnClickListener(view -> startActivity(new Intent(WelcomeActivity.this, SignupActivity.class)));
    }

    //TODO: uncomment only if i use layout.activity_welcome2
    /*private void onJoinUs() {
        this.joinUsButton.setOnClickListener(view -> startActivity(new Intent(WelcomeActivity.this, LoginActivity.class)));
    }*/

    //TODO: remove; solo per testing.
    private void initDatabase() {
        UserDatabase db = UserDatabase.getDatabaseInstance(getApplicationContext());
        db.userDAO().deleteAll(); //TODO: remove, just for testing.

        FoodDatabase.getDatabaseInstance(getApplicationContext()).foodDAO().deleteAllFoods(); //TODO: remove, just for testing.

        //TODO: remove
        User bob = new Client("bob", "email@example.com", "1234", 0.0); //Optional.empty(); new User
        User veganRestaurant = new Restaurant("restaurant", "vegan@example.com", "a56789b", 0.0); // new Restaurant

        db.userDAO().insertList(bob, veganRestaurant);

        List<User> userList = db.userDAO().getUsers(); //TODO: remove
        for(final User user : userList) {
            Log.d("users", user.getUsername() + " " + user.getEmail() + " " + user.getPassword());
        }

        // FAVORITE FOODS DATABASE //TODO: remove
        FavoriteFoodDatabase.getDatabaseInstance(getApplicationContext()).favoriteFoodDAO().deleteAllFavoriteFoods(); //TODO: remove; solo per testing;
    }
}