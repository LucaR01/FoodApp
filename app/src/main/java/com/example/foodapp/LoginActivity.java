package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
            //TODO: fare un controllo che i campi siano corretti.
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        });

        this.signupLinkTextView.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        });
    }


}