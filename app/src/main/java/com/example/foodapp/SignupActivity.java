package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.model.Databases.UserDatabase.UserDatabase;
import com.example.foodapp.model.Users.Client.Client;
import com.example.foodapp.model.Users.Restaurant.Restaurant;
import com.example.foodapp.model.Users.User;

import java.util.ArrayList;
import java.util.List;

//TODO: extends Fragment
public class SignupActivity extends AppCompatActivity {

    private static final String EMPTY_STRING = ""; //TODO: move/remove
    private static final String CLIENT = "Client"; //TODO: move/remove

    private TextView loginLinkTextView;
    private Button signupButton;

    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;

    private RadioGroup radioGroup;
    //private RadioButton clientRadioButton;
    //private RadioButton restaurantRadioButton;

    private RadioButton selectedRadioButton;

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

        radioGroup = findViewById(R.id.signup_radioButton);
        /*this.clientRadioButton = findViewById(R.id.client_radioButton);
        this.restaurantRadioButton = findViewById(R.id.restaurant_radioButton);*/

        signupButton.setOnClickListener(view -> {
            if(!(usernameEditText.getText().toString().equals("") && emailEditText.getText().toString().equals("")
                    && passwordEditText.getText().toString().equals("") && confirmPasswordEditText.getText().toString().equals(""))) {
                if(confirmPasswordEditText.getText().equals(passwordEditText.getText())) {

                    /*if(!emailEditText.getText().toString().contains("@")) { //TODO: uncomment and test.
                        return;
                    }*/

                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    selectedRadioButton = findViewById(selectedId);

                    if(selectedId != 1) {
                        Log.d("[RADIOS]", "selected index: " + selectedId + " radioSelected: " + selectedRadioButton.getId());
                        Log.d("[RADIOS]", "selectedRadioButton.getText(): " + selectedRadioButton.getText().toString());
                        final UserDatabase db = UserDatabase.getDatabaseInstance(getApplicationContext());

                        if(selectedRadioButton.getText().equals("Client")) {
                            Client client = new Client(usernameEditText.getText().toString(), emailEditText.getText().toString(), passwordEditText.getText().toString());
                            db.userDAO().insertList(client);
                        } else {
                            Restaurant restaurant = new Restaurant(usernameEditText.getText().toString(), emailEditText.getText().toString(), passwordEditText.getText().toString());
                            db.userDAO().insertList(restaurant);
                        }
                        startActivity(new Intent(SignupActivity.this, MainActivity.class));
                    }
                }
            }
        });

        this.loginLinkTextView.setOnClickListener(view -> {
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
        });
    }
}
