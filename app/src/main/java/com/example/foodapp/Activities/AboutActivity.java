package com.example.foodapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.foodapp.R;

import android.content.Intent;
import android.net.Uri;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    private TextView privacyPolicy;
    private Button sendFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        initView();

        openPrivacyPolicy();

        handleSendFeedback();

        //TODO: remove
        // Initialize UI elements
        /*Button feedbackButton = findViewById(R.id.aboutFeedbackButton);

        // Set onClick listener for the feedback button
        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFeedback();
            }
        });*/
    }

    private void initView() {
        this.privacyPolicy = findViewById(R.id.aboutPrivacyPolicyTextView);
        this.sendFeedback = findViewById(R.id.aboutFeedbackButton);
    }

    //TODO: remove
    // Open the privacy policy page
    /*public void openPrivacyPolicy(View view) {
        //Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.example.com/privacy-policy")); //TODO:remove
        Intent privacyPolicyIntent = new Intent(AboutActivity.this, PrivacyPolicyActivity.class);
        startActivity(privacyPolicyIntent);
    }

    //TODO: remove
    // Handle feedback button click
    public void sendFeedback() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:contact@example.com"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback for Food App");
        startActivity(emailIntent);
    }*/

    private void openPrivacyPolicy() {
        this.privacyPolicy.setOnClickListener(view -> {
            Intent privacyPolicyIntent = new Intent(AboutActivity.this, PrivacyPolicyActivity.class);
            startActivity(privacyPolicyIntent);
        });
    }

    private void handleSendFeedback() {
        this.sendFeedback.setOnClickListener(view -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:contact@example.com"));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback for Food App");
            startActivity(emailIntent);
        });
    }
}
