package com.example.foodapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    private final TextView loginLinkTextView = findViewById(R.id.signin_link_textView);
    private final Button signupButton = findViewById(R.id.signup_button);

    private final EditText usernameEditText = findViewById(R.id.signup_username_editText);
    private final EditText emailEditText = findViewById(R.id.signup_email_editText);
    private final EditText passwordEditText = findViewById(R.id.signup_password_editText);
    private final EditText confirmPasswordEditText = findViewById(R.id.signup_confirm_password_editText);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //TODO: potrei far comparire gli elemnti con una translation.
    }
}
