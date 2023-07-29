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
    //private RadioButton clientRadioButton; //TODO: remove
    //private RadioButton restaurantRadioButton; //TODO: remove

    private RadioButton selectedRadioButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initView();

        signup();

        onLoginLinkClickListener();

    }

    private void initView() {
        //TODO: potrei far comparire gli elemnti con una translation.
        this.loginLinkTextView = findViewById(R.id.signin_link_textView);
        this.signupButton = findViewById(R.id.signup_button);
        this.usernameEditText = findViewById(R.id.signup_username_editText);
        this.emailEditText = findViewById(R.id.signup_email_editText);
        this.passwordEditText = findViewById(R.id.signup_password_editText);
        this.confirmPasswordEditText = findViewById(R.id.signup_confirm_password_editText);

        this.radioGroup = findViewById(R.id.signup_radioButton);
        /*this.clientRadioButton = findViewById(R.id.client_radioButton);
        this.restaurantRadioButton = findViewById(R.id.restaurant_radioButton);*/
    }

    private void signup() {
        this.signupButton.setOnClickListener(view -> {
            if(!(this.usernameEditText.getText().toString().equals("") && this.emailEditText.getText().toString().equals("")
                    && this.passwordEditText.getText().toString().equals("") && this.confirmPasswordEditText.getText().toString().equals(""))) {
                if(this.confirmPasswordEditText.getText().equals(this.passwordEditText.getText())) {

                    /*if(!emailEditText.getText().toString().contains("@")) { //TODO: uncomment and test.
                        return;
                    }*/

                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    this.selectedRadioButton = findViewById(selectedId);

                    if(selectedId != 1) {
                        Log.d("[RADIOS]", "selected index: " + selectedId + " radioSelected: " + selectedRadioButton.getId());
                        Log.d("[RADIOS]", "selectedRadioButton.getText(): " + selectedRadioButton.getText().toString());
                        final UserDatabase db = UserDatabase.getDatabaseInstance(getApplicationContext());

                        if(this.selectedRadioButton.getText().equals("Client")) {
                            Client client = new Client(this.usernameEditText.getText().toString(), this.emailEditText.getText().toString(), this.passwordEditText.getText().toString());
                            //db.userDAO().insertList(client); //TODO: uncomment/remove?
                            db.userDAO().insertUser(client);
                        } else {
                            Restaurant restaurant = new Restaurant(this.usernameEditText.getText().toString(), this.emailEditText.getText().toString(), this.passwordEditText.getText().toString());
                            //db.userDAO().insertList(restaurant); //TODO: uncomment/remove?
                            db.userDAO().insertUser(restaurant);
                        }
                        startActivity(new Intent(SignupActivity.this, MainActivity.class));
                    }
                }
            }
        });
    }

    private void onLoginLinkClickListener() {
        this.loginLinkTextView.setOnClickListener(view -> {
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
        });
    }
}
