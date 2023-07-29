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
            //TODO: notificare l'utente.
            //TODO: uncomment when checked and fixed.

            //TODO: entra anche se sono vuoti i campi.
            if(this.usernameEditText.getText().toString().equals("") || this.passwordEditText.getText().toString().equals("")) { //TODO: usare la funzione?
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

            //TODO: usare funzione?
            UserDatabase db = UserDatabase.getDatabaseInstance(getApplicationContext());
            List<User> dbUserList = db.userDAO().getUsers();

            for(final User user : dbUserList) { //TODO: remove
                System.out.println("user email: " + user.getEmail() + " user username: " + user.getUsername() + " user password: " + user.getPassword() );
            }

            for(final User user : dbUserList) {
                //FIXME
                if((user.getEmail().contains(usernameEditText.getText()) || user.getUsername().contains(usernameEditText.getText()))
                        && user.getPassword().contains(passwordEditText.getText())) {
                    Log.d("[USERS]", "username | email: " + usernameEditText.getText().toString() + " password: " + passwordEditText.getText().toString());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("username", user.getUsername());
                    startActivity(intent);
                } /*else { //FIXME
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("Failure");
                    builder.setMessage("Username or password incorrect!");
                    builder.setCancelable(true);

                    builder.setPositiveButton(
                            "Ok",
                            (dialog, id) -> dialog.dismiss());

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }*/

                //TODO: remove
                /*if(!((user.getEmail().contains(usernameEditText.getText()) || user.getUsername().contains(usernameEditText.getText()))
                        && user.getPassword().contains(passwordEditText.getText()))) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("Failure");
                    builder.setMessage("Username or password incorrect!");
                    builder.setCancelable(true);

                    builder.setPositiveButton(
                            "Ok",
                            (dialog, id) -> dialog.dismiss());

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    Log.d("[USERS]", "username | email: " + usernameEditText.getText().toString() + " password: " + passwordEditText.getText().toString());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("username", user.getUsername());
                    startActivity(intent);
                }*/
            }

            //startActivity(new Intent(LoginActivity.this, SettingsActivity.class)); //TODO: remove; è qui solo per testing.
            //startActivity(new Intent(LoginActivity.this, MainActivity.class)); //TODO: remove
        });
    }

    private void checkEmptyFields() {
        if(this.usernameEditText.getText().toString().equals("") || this.passwordEditText.getText().toString().equals("")) {
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
            if((user.getEmail().contains(usernameEditText.getText()) || user.getUsername().contains(usernameEditText.getText()))
                    && user.getPassword().contains(passwordEditText.getText())) {
                Log.d("[USERS]", "username | email: " + usernameEditText.getText().toString() + " password: " + passwordEditText.getText().toString());
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("username", user.getUsername());
                startActivity(intent);
            }
        }
    }

    private void onSignUpLinkClickListener() {
        this.signupLinkTextView.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        });
    }


}