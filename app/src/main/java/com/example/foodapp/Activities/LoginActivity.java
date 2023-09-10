package com.example.foodapp.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.R;
import com.example.foodapp.model.Databases.UserDatabase.UserDatabase;
import com.example.foodapp.model.Users.User;

import java.util.List;

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
            final String enteredUsername = this.usernameEditText.getText().toString();
            final String enteredPassword = this.passwordEditText.getText().toString();

            if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
                showAlertDialog("Failure", "Empty Fields!");
            } else {
                UserDatabase db = UserDatabase.getDatabaseInstance(getApplicationContext());
                List<User> dbUserList = db.userDAO().getUsers();

                for (User user : dbUserList) {
                    final String dbUsername = user.getUsername();
                    final String dbEmail = user.getEmail();
                    final String dbPassword = user.getPassword();

                    if (enteredUsername.equals(dbUsername) || enteredUsername.equals(dbEmail)) {
                        if (enteredPassword.equals(dbPassword)) {
                            Log.d("[USERS]", "username | email: " + enteredUsername + " password: " + enteredPassword); //TODO: remove?

                            final Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("username", dbUsername);
                            intent.putExtra("balance", String.valueOf(user.getBalance()));
                            startActivity(intent);
                            return; // Prevent showing the alert if the user is found.
                        }
                    }
                }

                showAlertDialog("Failure", "Username or password incorrect!");
            }
        });
    }

    private void showAlertDialog(final String title, final String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Ok",
                (dialog, id) -> dialog.dismiss());

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void onSignUpLinkClickListener() {
        this.signupLinkTextView.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, SignupActivity.class)));
    }


}