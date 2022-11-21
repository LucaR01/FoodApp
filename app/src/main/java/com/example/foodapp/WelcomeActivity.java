package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    //TODO: final
    private Button loginButton;
    private ImageView welcomeScreenImageView;
    private TextView signupTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        /*requestWindowFeature(Window.FEATURE_NO_TITLE); //TODO:
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        this.loginButton = findViewById(R.id.welcome_login_button);
        this.welcomeScreenImageView = findViewById(R.id.welcome_screen_imageView);
        this.signupTextView = findViewById(R.id.signup_textview);

        //TODO: questo è lo stesso che c'è nel metodo onLogin
        loginButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
            }
        });

        //TODO: stesso codice che c'è nel metodo onSignUp
        signupTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this, SignupActivity.class));
            }
        });

        //this.welcomeScreenImageView.animate().translationY(-1500).setDuration(1000).setStartDelay(4000); //TODO: uncomment
        //TODO: l'animate anche per le textviews e il pulsante
    }

    public void onLogin(View view){
        loginButton.setOnClickListener(b -> { //TODO: b era view
            //TODO: login
            startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
        });
    }

    public void onSignUp(View view){
        signupTextView.setOnClickListener(view1 -> {
            //TODO: signup
            startActivity(new Intent(WelcomeActivity.this, SignupActivity.class));
            //TODO: potrei farli comparire
        });
    }
}