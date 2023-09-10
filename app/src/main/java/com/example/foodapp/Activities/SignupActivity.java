package com.example.foodapp.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.R;
import com.example.foodapp.model.Databases.UserDatabase.UserDatabase;
import com.example.foodapp.model.Users.Client.Client;

public class SignupActivity extends AppCompatActivity {

    private TextView loginLinkTextView;
    private Button signupButton;

    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;

    //private RadioGroup radioGroup; //TODO: remove
    //private RadioButton clientRadioButton; //TODO: remove
    //private RadioButton restaurantRadioButton; //TODO: remove
    //private RadioButton selectedRadioButton; //TODO: remove

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initView();

        signup();

        onLoginLinkClickListener();

    }

    private void initView() {
        this.loginLinkTextView = findViewById(R.id.signin_link_textView);
        this.signupButton = findViewById(R.id.signup_button);
        this.usernameEditText = findViewById(R.id.signup_username_editText);
        this.emailEditText = findViewById(R.id.signup_email_editText);
        this.passwordEditText = findViewById(R.id.signup_password_editText);
        this.confirmPasswordEditText = findViewById(R.id.signup_confirm_password_editText);

        //this.radioGroup = findViewById(R.id.signup_radioButton); //TODO: remove
        /*this.clientRadioButton = findViewById(R.id.client_radioButton);
        this.restaurantRadioButton = findViewById(R.id.restaurant_radioButton);*/
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

    private void showAlertDialog(final String title, final String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);

        builder.setPositiveButton("Ok", (dialog, id) -> dialog.dismiss());

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void onLoginLinkClickListener() {
        this.loginLinkTextView.setOnClickListener(view -> startActivity(new Intent(SignupActivity.this, LoginActivity.class)));
    }
}
