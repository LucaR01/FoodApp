package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.foodapp.Activities.SettingsActivity;
import com.example.foodapp.model.Databases.UserDatabase.UserDatabase;
import com.example.foodapp.model.Users.User;

import java.util.List;

//TODO: extends Fragment
public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private TextView signupLinkTextView;

    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.login_button);
        signupLinkTextView = findViewById(R.id.signup_link_textView);
        usernameEditText = findViewById(R.id.login_username_editText);
        passwordEditText = findViewById(R.id.login_password_editText);

        this.loginButton.setOnClickListener(view -> {
            //TODO: notificare l'utente.
            //TODO: uncomment when checked and fixed.
            /*if(usernameEditText.getText().toString().equals("") || passwordEditText.getText().toString().equals("")) {
                Log.d("[USERS]", "Empty fields!");
            }

            UserDatabase db = UserDatabase.getDatabaseInstance(getApplicationContext());
            List<User> dbUserList = db.userDAO().getUsers();

            for(final User user : dbUserList) {
                if(user.getEmail().contains(usernameEditText.getText()) || user.getUsername().contains(usernameEditText.getText())
                        && user.getPassword().contains(passwordEditText.getText())) {
                    Log.d("[USERS]", "username | email: " + usernameEditText.getText().toString() + " password: " + passwordEditText.getText().toString());
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }*/

            //startActivity(new Intent(LoginActivity.this, SettingsActivity.class)); //TODO: remove; è qui solo per testing.
            startActivity(new Intent(LoginActivity.this, MainActivity.class)); //TODO: remove
        });

        this.signupLinkTextView.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        });
    }


}