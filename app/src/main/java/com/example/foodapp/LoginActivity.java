package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.foodapp.Activities.FoodDetailsActivity;
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

        initView();

        login();

        onSignUpLinkClickListener();
    }

    private void initView() {
        this.loginButton = findViewById(R.id.login_button);
        this.signupLinkTextView = findViewById(R.id.signup_link_textView);
        this.usernameEditText = findViewById(R.id.login_username_editText);
        this.passwordEditText = findViewById(R.id.login_password_editText);
    }

    private void login() {
        this.loginButton.setOnClickListener(view -> {
            if(this.usernameEditText.getText().toString().contentEquals("") || this.passwordEditText.getText().toString().contentEquals("")) { //TODO: usare la funzione?
                Log.d("[USERS]", "Empty fields!");
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("Failure");
                builder.setMessage("Empty Fields!");
                builder.setCancelable(true);

                builder.setPositiveButton(
                        "Ok",
                        (dialog, id) -> dialog.dismiss());

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            } else {
                //TODO: usare funzione?
                UserDatabase db = UserDatabase.getDatabaseInstance(getApplicationContext());
                List<User> dbUserList = db.userDAO().getUsers();

                for(final User user : dbUserList) { //TODO: remove
                    System.out.println("user email: " + user.getEmail() + " user username: " + user.getUsername() + " user password: " + user.getPassword() );
                }

                for(final User user : dbUserList) {
                    if(user.getEmail().contentEquals(this.usernameEditText.getText()) || user.getUsername().contentEquals(this.usernameEditText.getText())) {
                        if(user.getPassword().contentEquals(passwordEditText.getText())) {
                            Log.d("[USERS]", "username | email: " + usernameEditText.getText().toString() + " password: " + passwordEditText.getText().toString());
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("username", user.getUsername());
                            intent.putExtra("balance", String.valueOf(user.getBalance()));
                            startActivity(intent);
                            return; // prima c'era un break; Col return evitiamo che anche se l'utente viene trovato, non mostra l'alert.
                        }
                    }
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("Failure");
                builder.setMessage("Username or password incorrect!");
                builder.setCancelable(true);

                builder.setPositiveButton(
                        "Ok",
                        (dialog, id) -> dialog.dismiss());

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    private void checkEmptyFields() {
        if(this.usernameEditText.getText().toString().contentEquals("") || this.passwordEditText.getText().toString().contentEquals("")) {
            Log.d("[USERS]", "Empty fields!");
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setTitle("Failure");
            builder.setMessage("Empty Fields!");
            builder.setCancelable(true);

            builder.setPositiveButton(
                    "Ok",
                    (dialog, id) -> dialog.dismiss());

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    private void checkUserInDB() {
        UserDatabase db = UserDatabase.getDatabaseInstance(getApplicationContext());
        List<User> dbUserList = db.userDAO().getUsers();

        for(final User user : dbUserList) {
            if(user.getEmail().contentEquals(this.usernameEditText.getText()) || user.getUsername().contentEquals(this.usernameEditText.getText())) {
                if(user.getPassword().contentEquals(passwordEditText.getText())) {
                    Log.d("[USERS]", "username | email: " + usernameEditText.getText().toString() + " password: " + passwordEditText.getText().toString());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("username", user.getUsername());
                    startActivity(intent);
                    return; // prima c'era un break; Col return evitiamo che anche se l'utente viene trovato, non mostra l'alert.
                }
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("Failure");
        builder.setMessage("Username or password incorrect!");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Ok",
                (dialog, id) -> dialog.dismiss());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void onSignUpLinkClickListener() {
        this.signupLinkTextView.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        });
    }


}