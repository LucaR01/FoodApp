package com.example.foodapp;

import android.app.AlertDialog;
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
    private static final double NO_BALANCE = 0.0; //TODO: move/remove

    private TextView loginLinkTextView;
    private Button signupButton;

    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;

    //private RadioGroup radioGroup; //TODO: remove
    //private RadioButton clientRadioButton; //TODO: remove
    //private RadioButton restaurantRadioButton; //TODO: remove

    private RadioButton selectedRadioButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initView(); //TODO: uncomment?

        //initView2(); //TODO: remove

        signup(); //TODO: uncomment

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

        //this.radioGroup = findViewById(R.id.signup_radioButton);
        /*this.clientRadioButton = findViewById(R.id.client_radioButton);
        this.restaurantRadioButton = findViewById(R.id.restaurant_radioButton);*/
    }

    //TODO: remove
    private void initView2() {
        this.loginLinkTextView = findViewById(R.id.signin_link_textView2);
        this.signupButton = findViewById(R.id.signup_button2);
        this.usernameEditText = findViewById(R.id.signup_username_editText2);
        this.emailEditText = findViewById(R.id.signup_email_editText2);
        this.passwordEditText = findViewById(R.id.signup_password_editText2);
        this.confirmPasswordEditText = findViewById(R.id.signup_confirm_password_editText2);
    }

    //TODO: remove
    private void signup2() {
        this.signupButton.setOnClickListener(view -> {

            if(!this.passwordEditText.getText().toString().equals(this.confirmPasswordEditText.getText().toString())) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                builder.setTitle("Failure");
                builder.setMessage("Passwords don't match!");
                builder.setCancelable(true);

                builder.setPositiveButton(
                        "Ok",
                        (dialog, id) -> dialog.dismiss());

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

            if(this.usernameEditText.getText().toString().equals("") || this.emailEditText.getText().toString().equals("") ||
            this.passwordEditText.getText().toString().equals("") || this.confirmPasswordEditText.getText().toString().equals("")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                builder.setTitle("Failure");
                builder.setMessage("Empty fields!");
                builder.setCancelable(true);

                builder.setPositiveButton(
                        "Ok",
                        (dialog, id) -> dialog.dismiss());

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

            if(!(this.usernameEditText.getText().toString().equals("") && this.emailEditText.getText().toString().equals("")
                    && this.passwordEditText.getText().toString().equals("") && this.confirmPasswordEditText.getText().toString().equals(""))) {
                if(this.confirmPasswordEditText.getText().equals(this.passwordEditText.getText())) {

                    /*if(!emailEditText.getText().toString().contains("@")) { //TODO: uncomment and test.
                        return;
                    }*/

                    //int selectedId = radioGroup.getCheckedRadioButtonId();
                    //this.selectedRadioButton = findViewById(selectedId);

                    if(!this.emailEditText.getText().toString().contains("@")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                        builder.setTitle("Failure");
                        builder.setMessage("Email must contain a @ character!");
                        builder.setCancelable(true);

                        builder.setPositiveButton(
                                "Ok",
                                (dialog, id) -> dialog.dismiss());

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }

                    final UserDatabase db = UserDatabase.getDatabaseInstance(getApplicationContext());
                    Client client = new Client(this.usernameEditText.getText().toString(), this.emailEditText.getText().toString(), this.passwordEditText.getText().toString(), 0.0);
                    db.userDAO().insertUser(client);

                    startActivity(new Intent(SignupActivity.this, MainActivity.class));

                    /*if(selectedId != 1) {
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
                    }*/
                }
            }
        });
    }

    private void signup() {
        this.signupButton.setOnClickListener(view -> {
            final String username = this.usernameEditText.getText().toString();
            final String email = this.emailEditText.getText().toString();
            final String password = this.passwordEditText.getText().toString();
            final String confirmPassword = this.confirmPasswordEditText.getText().toString();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                showAlertDialog("Failure", "Empty fields!");
            } else if (!email.contains("@")) {
                showAlertDialog("Failure", "Email must contain '@' character!");
            } else if (!password.equals(confirmPassword)) {
                showAlertDialog("Failure", "Passwords don't match!");
            } else {
                // Passwords match, fields are not empty, and email contains '@'.
                // Proceed with user insertion and activity transition.
                final UserDatabase db = UserDatabase.getDatabaseInstance(getApplicationContext());
                final Client client = new Client(username, email, password, 0.0);
                db.userDAO().insertUser(client);

                final Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                intent.putExtra("username", client.getUsername());
                intent.putExtra("balance", String.valueOf(client.getBalance()));
                startActivity(intent);
            }
        });
    }

    private void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);

        builder.setPositiveButton("Ok", (dialog, id) -> dialog.dismiss());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void onLoginLinkClickListener() {
        this.loginLinkTextView.setOnClickListener(view -> {
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
        });
    }
}
