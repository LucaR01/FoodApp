package com.example.foodapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    private TextView loginLinkTextView;
    private Button signupButton;

    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //TODO: potrei far comparire gli elemnti con una translation.
        loginLinkTextView = findViewById(R.id.signin_link_textView);
        signupButton = findViewById(R.id.signup_button);
        usernameEditText = findViewById(R.id.signup_username_editText);
        emailEditText = findViewById(R.id.signup_email_editText);
        passwordEditText = findViewById(R.id.signup_password_editText);
        confirmPasswordEditText = findViewById(R.id.signup_confirm_password_editText);
    }
}
